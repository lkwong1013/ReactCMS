package com.example.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by LKW on 2017/8/13.
 */
public class DuplicateRecordFoundException extends BaseException {

    public DuplicateRecordFoundException() {
        super(HttpStatus.BAD_REQUEST, "Duplicate Record found");
    }

    public DuplicateRecordFoundException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
