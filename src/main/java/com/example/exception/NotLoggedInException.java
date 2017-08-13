package com.example.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by LKW on 2016/11/12.
 */
public class NotLoggedInException extends BaseException {

    public NotLoggedInException(HttpStatus statusCode, String message) {
        super(statusCode, message);
    }

}
