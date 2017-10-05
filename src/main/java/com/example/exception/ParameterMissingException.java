package com.example.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by LKW on 2017/10/5.
 */
public class ParameterMissingException extends BaseException {

    public ParameterMissingException() {
        super(HttpStatus.BAD_REQUEST, "Parameter missing");
    }

    public ParameterMissingException(String parameterName) {
        super(HttpStatus.BAD_REQUEST, "Parameter missing - " + parameterName);
    }
}
