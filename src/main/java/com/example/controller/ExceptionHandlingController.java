package com.example.controller;

import com.example.exception.BaseException;
import com.example.exception.NotLoggedInException;
import com.example.exception.PermissionDeniedException;
import com.example.object.response.APIResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by LKW on 2016/11/12.
 */

@ControllerAdvice
public class ExceptionHandlingController {

    Logger log = Logger.getLogger(this.getClass());

    @Autowired
    FrontEndController frontEndController;

    @ExceptionHandler(value = {NotLoggedInException.class})
    public ModelAndView test(HttpServletRequest req, Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("baseUrl", req.getContextPath());
        mav.setViewName("fullLoginPage");
        return mav;
    }

    @ExceptionHandler(value = PermissionDeniedException.class)
    public ResponseEntity exception(PermissionDeniedException exception) {
        return new APIResponse().send(HttpStatus.UNAUTHORIZED, "Permission Denied.");
    }

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity exception(BaseException exception) {
        return new APIResponse().send(exception.getStatusCode(), exception.getDeveloperMessage());
    }
}
