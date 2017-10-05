package com.example.object.request.user;

import lombok.Data;

/**
 * Created by LKW on 2017/10/5.
 */

@Data
public class UserChangePasswordRequest {

    private String oldPassword;
    private String password;
    private String confirmPassword;


}
