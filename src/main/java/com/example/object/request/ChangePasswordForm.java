package com.example.object.request;

import lombok.Data;

/**
 * Created by LKW on 2017/1/9.
 */

@Data
public class ChangePasswordForm {

    private String newPassword;

    private String confirmNewPassword;

}
