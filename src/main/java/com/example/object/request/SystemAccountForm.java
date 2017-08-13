package com.example.object.request;

import lombok.Data;

/**
 * Created by LKW on 2016/12/14.
 */

@Data
public class SystemAccountForm {

    private Long id;

    private String lastName;

    private String firstName;

    private String loginName;

    private String password;

    private String userRole;

    private String email;

}
