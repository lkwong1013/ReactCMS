package com.example.controller;

import com.example.object.request.UserLogoutRequest;
import com.example.object.request.user.UserChangePasswordRequest;
import com.example.object.response.BaseResponseObj;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.example.controller.ApiGenericController.ROOT_URL;

/**
 * Created by LKW on 2017/9/29.
 */

@RestController
@RequestMapping(ROOT_URL + UserController.MODULE)
public class UserController extends ApiGenericController {

    @Autowired
    private UserService userService;

    protected static final String MODULE = "user";

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public BaseResponseObj logout() {
        return userService.memberLogout();
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public BaseResponseObj changePassword(@RequestBody UserChangePasswordRequest request) {
        userService.changePassword(request);
        return new BaseResponseObj();
    }

}
