package com.example.controller;

import com.example.exception.BaseException;
import com.example.object.request.LoginRequestObj;
import com.example.object.request.UserEntityRequest;
import com.example.object.response.BaseResponseObj;
import com.example.object.response.LoginResponseObj;
import com.example.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.example.controller.ApiGenericController.ROOT_URL;


/**
 * Created by LKW on 2017/4/22.
 */

@RestController
@RequestMapping(ROOT_URL + ApiAuthController.MODULE)
public class ApiAuthController /*extends ApiGenericController<Long, UserAccount, LoginRequestObj>*/ {

   protected static final String MODULE = "auth";

//    UserAccountService userAccountService;
    @Autowired
    private UserService userService;

//    @Autowired
//    public ApiAuthController(UserAccountService userAccountService) {
//        super(userAccountService);
//        this.userAccountService = userAccountService;
//    }

    Logger log = Logger.getLogger(this.getClass());

   @RequestMapping(value = {"/login"}, method = {RequestMethod.POST})
   public LoginResponseObj login(@RequestBody LoginRequestObj requestObj) throws Exception {

        String logPrefix = "login():";

        LoginResponseObj loginResponseObj = new LoginResponseObj();

        if (StringUtils.isBlank(requestObj.getUserName()) || StringUtils.isBlank(requestObj.getPassword())) {
            throw new BaseException(HttpStatus.BAD_REQUEST, "Parameter missing");
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        BaseResponseObj resp = userService.memberLogin(requestObj);
        if (resp.getStatus().equals(HttpStatus.OK)) {

            String newToken = userService.issueToken(requestObj);
            loginResponseObj.setToken(newToken);

        } else {
            throw new BaseException(HttpStatus.BAD_REQUEST, resp.getMessage());
        }
        return loginResponseObj;
    }


    @RequestMapping(value = {"/register"}, method = {RequestMethod.POST})
    public BaseResponseObj register(@RequestBody UserEntityRequest formData) throws Exception {
        return userService.registration(formData);
    }

    @RequestMapping(value = {"/dummy"}, method = {RequestMethod.POST})
    public BaseResponseObj dummy() {
        return new BaseResponseObj(HttpStatus.OK, null);
    }

}
