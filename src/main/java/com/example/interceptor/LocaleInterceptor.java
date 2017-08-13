package com.example.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by LKW on 2016/10/28.
 */
public class LocaleInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = Logger.getLogger(LocaleInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean handlerResult = true;
        String logPrefix = "LocaleInterceptor preHandle(): ";

        log.info(logPrefix+"triggered");
        return handlerResult;
    }

}
