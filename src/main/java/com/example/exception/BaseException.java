package com.example.exception;

import com.example.service.SystemMessageService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Created by LKW on 2016/10/29.
 */

@Data
public class BaseException extends RuntimeException {

    private HttpStatus statusCode;
    private String code;
    private String developerMessage;
    private String message;

    public BaseException() {

    }

    public BaseException(HttpStatus statusCode, String message){
        this.statusCode = statusCode;
        //this.developerMessage = message;
        this.message = message;
    }

}
