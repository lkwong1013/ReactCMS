package com.example.controller;//package com.project.controller;
//
//import com.project.object.response.BaseResponseObj;
//import com.project.object.request.LoginRequestObj;
//import com.project.service.UserService;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Created by LKW on 2016/10/28.
// */
//
//@Controller
//@RequestMapping("/loginAction")
//public class LoginController extends BaseController {
//
//    @Autowired
//    UserService userAccountService;
//
//    Logger log = Logger.getLogger(this.getClass());
//
//    @RequestMapping(value = { "/" }, method = RequestMethod.POST)
//    @ResponseBody
//    public BaseResponseObj login(LoginRequestObj loginRequestObj, ModelMap model, HttpServletRequest request) throws Exception {
//        String forwardUrl = request.getContextPath()+"/dashboard/";
//        loginRequestObj.setFowardUrl(forwardUrl);
//        return userAccountService.memberLogin(loginRequestObj, request);
//    }
//
//    @RequestMapping(value = { "/login" }, method = {RequestMethod.POST, RequestMethod.GET})
//    public String doLogin(LoginRequestObj loginRequestObj, ModelMap model, HttpServletRequest request) throws Exception {
//        BaseResponseObj loginResult = userAccountService.memberLogin(loginRequestObj, request);
//        if (loginResult.getStatus().equals(200)) {
//            return "home";
//
//        } else {
//
//            return "fullLoginPage";
//        }
//
//    }
//
//
//}
