package com.example.object.request;

import lombok.Data;

/**
 * Created by LKW on 2016/10/29.
 */

@Data
public class LoginRequestObj extends BaseRequestObj {

    private String userName;

    private String password;

}
