package com.example.object.request;

import lombok.Data;

import java.util.List;

/**
 * Created by LKW on 2017/8/6.
 */

@Data
public class UserEntityRequest {

    private String userName;

    private String password;

    private String email;

    private List<Long> userRoleIdList;

}
