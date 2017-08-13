package com.example.object.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by LKW on 2016/10/29.
 */

@Data
public class UserLoginSession implements Serializable {

    private Long userId;

    private Date expiryTime;


}
