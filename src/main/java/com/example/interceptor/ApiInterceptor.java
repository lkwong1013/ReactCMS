package com.example.interceptor;

import com.example.exception.BaseException;
import com.example.exception.PermissionDeniedException;
import com.example.neo4j.domain.UserEntity;
import com.example.service.UserPermissionService;
import com.example.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by LKW on 2017/4/22.
 */
public class ApiInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = Logger.getLogger(ApiInterceptor.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserPermissionService permissionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean handlerResult = true;

        String logPrefix = "ApiInterceptor preHandle(): ";

        // Check client token
        String username = request.getHeader("username");
        String token = request.getHeader("token");
        String lang = request.getHeader("lang");

        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        localeResolver.setLocale(request, response, new Locale(lang));

        String requestURI = request.getServletPath();

        if (StringUtils.isBlank(username) || StringUtils.isBlank(token)) {
            throw new PermissionDeniedException();
        }

//        Long userId;
        // Check login status
//        try {
//            userId = new Long(username);
//        } catch (Exception e) {
//            throw new BaseException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }

        List<UserEntity> userAccount = new ArrayList<>();
        userAccount = userService.findByName(username);
        if (userAccount == null || userAccount.size() == 0) {
            throw new BaseException(HttpStatus.NOT_FOUND, "User not found");
        }

        if (userAccount.size() > 1) {
            throw new BaseException(HttpStatus.BAD_REQUEST, "Duplicated record found");
        }

        UserEntity user = userAccount.get(0);
        if (StringUtils.isBlank(user.getAccessKey())) {
            throw new BaseException(HttpStatus.UNAUTHORIZED, "Not logged In");
        }

        Date expiryDate = user.getKeyExpiryDate();
        Date sysDate = new Date();
        if (sysDate.after(expiryDate)) {
            throw new BaseException(HttpStatus.UNAUTHORIZED, "Session Expired");  // Session Expired
        }

        if (!user.getAccessKey().equals(token)) {
            throw new BaseException(HttpStatus.UNAUTHORIZED, "Invalid client token");  // Session Expired
        }

        // Check permission
        if (!permissionService.chkAuthByRole(user.getHaveRole(), requestURI)) {
            throw new PermissionDeniedException();
        }

        // Extend current session
        userService.extendSession(user);

        return handlerResult;
    }
}
