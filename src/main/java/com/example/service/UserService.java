package com.example.service;


import com.example.neo4j.domain.UserEntity;
import com.example.object.request.BaseRequestObj;
import com.example.object.request.LoginRequestObj;
import com.example.object.request.UserEntityRequest;
import com.example.object.request.UserLogoutRequest;
import com.example.object.response.BaseResponseObj;

import java.util.List;

/**
 * Created by LKW on 2017/8/6.
 */
public interface UserService  {

    BaseResponseObj memberLogin(LoginRequestObj loginRequestObj) throws Exception;

    String issueToken(LoginRequestObj requestObj) throws Exception;

    BaseResponseObj registration(UserEntityRequest request) throws Exception;

    void extendSession(UserEntity userAccount);

    List<UserEntity> findByName(String username);

    BaseResponseObj memberLogout();
}
