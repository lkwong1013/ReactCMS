package com.example.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

/**
 * Created by LKW on 2017/10/5.
 */
public class RecordNotFoundException extends BaseException {

    public RecordNotFoundException() {
        super(HttpStatus.BAD_REQUEST, "Record not Found");
    }

    public RecordNotFoundException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

}
