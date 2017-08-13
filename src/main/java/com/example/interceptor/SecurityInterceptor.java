package com.example.interceptor;

import com.example.object.AuthorityInfo;
import com.example.service.UserPermissionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LKW on 2016/12/25.
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private UserPermissionService permissionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean handlerResult = true;
        String requestURI = request.getServletPath();
        String logPrefix = "SecurityInterceptor preHandle(): ";
        HttpSession session = request.getSession();

//        UserPermission requestPermission = new UserPermission();
//
//        requestPermission.setUrl(requestURI);
//        List<UserPermission> permissionList = this.permissionService.findByUrlLike(requestURI);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();

        Boolean permissionGranted = Boolean.FALSE;
        List<AuthorityInfo> authorityInfoList = new ArrayList<AuthorityInfo>();
        if (session.getAttribute("authorityInfo") != null) {
            authorityInfoList = (List<AuthorityInfo>) session.getAttribute("authorityInfo");
        }

        for (AuthorityInfo authorityInfo : authorityInfoList) {
            // Assume URL is regRex
            if (requestURI.matches(authorityInfo.getUrl())) {  // Compare with regular expression
                log.info(logPrefix + requestURI + " - Permission Granted!");
                return handlerResult;
            }
        }

        // No security handling
        if (("/denied").equals(requestURI)
        || ("/fullLogin").equals(requestURI)
        || ("/changePassword").equals(requestURI)
        || ("/changePasswordSubmit").equals(requestURI)) {

            log.info(logPrefix + requestURI/* + " - Permission Granted!"*/);
            return handlerResult;
        }

        // SUPER ADMIN handling
        for (GrantedAuthority authority : authorities) {
            String authorityStr = authority.toString();
            if (authorityStr.equals("ROLE_SUPERADMIN") || authorityStr.equals("ROLE_ANONYMOUS")) {
                // Anonymous Role handled by spring security
                log.info(logPrefix + requestURI + " - Permission Granted!");
                return handlerResult;
            }
        }

        // Normal case handling
        for (AuthorityInfo authorityInfo : authorityInfoList) {
            // Assume URL is regRex
            if (requestURI.matches(authorityInfo.getUrl())) {
                log.info(logPrefix + requestURI + " - Permission Granted!");
            }
        }

        if (!permissionGranted) {
            log.info(logPrefix + requestURI + " - Permission Denied!");
            response.sendRedirect(request.getContextPath() + "/denied"); // Redirect to access denied page
            return false;
        }

        log.info(logPrefix+"triggered");
        return handlerResult;
    }


}
