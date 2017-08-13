package com.example.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Created by LKW on 2016/10/29.
 */

@Data
public class BaseException extends RuntimeException {

    private HttpStatus statusCode;
    private String code;
    private String developerMessage;


    public BaseException(HttpStatus statusCode, String message){
        this.statusCode = statusCode;
        this.developerMessage = message;

    }

}
