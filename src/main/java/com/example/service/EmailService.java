package com.example.service;


import com.example.object.request.EmailRequest;

/**
 * Created by LKW on 2017/5/21.
 */
public interface EmailService /*extends BaseService<Long, BaseEntity>*/ {

    void sendEmail(EmailRequest emailRequest) throws Exception;

}
