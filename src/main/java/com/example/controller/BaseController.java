package com.example.controller;

import com.example.object.AuthorityInfo;
import com.example.object.request.EmailRequest;
import com.example.object.response.BaseResponseObj;
import com.example.service.EmailService;
import com.example.service.UserPermissionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 * Created by LKW on 2016/10/28.
 */

@Controller
@RequestMapping("/test")
public class BaseController {

    @Autowired
    private UserPermissionService permissionService;

    @Autowired
    private EmailService emailService;

    Logger log = Logger.getLogger(this.getClass());

    @RequestMapping(value = {"/roleAuthMap"}, method = {RequestMethod.GET})
    public BaseResponseObj getRoleAuthMap() {
        Map<String, List<AuthorityInfo>> roleAuthMap = permissionService.getRoleAuthMap();
        log.info(roleAuthMap.toString());
        return new BaseResponseObj();
    }

    @RequestMapping(value = "/emailTest")
    @ResponseBody
    public BaseResponseObj emailTest() {

        BaseResponseObj resp = new BaseResponseObj();
        try {

            EmailRequest emailRequest = new EmailRequest();
            emailRequest.setSendAddress("pubmaxwong@gmail.com");
            emailRequest.setEmailContent("Testing content");
            emailRequest.setSubject("Testing Subject");

//            emailRequest.setReceiverAddressList("lkwong1013@);
            emailService.sendEmail(emailRequest);

        } catch (Exception e) {
            resp = new BaseResponseObj(HttpStatus.BAD_REQUEST, e.getMessage());
            return resp;
        }
        return new BaseResponseObj();
    }

}
