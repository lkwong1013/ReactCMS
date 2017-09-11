package com.example.object.response;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by LKW on 2016/10/29.
 */

@Data
public class BaseResponseObj<T> {

    private HttpStatus status = HttpStatus.OK;

    private String message = "Success";

    private String remarks = "";

    private String forwardUrl = "";

    private T body;

    public BaseResponseObj() {
       // super();
    }

    public BaseResponseObj(HttpStatus status, T body) {
        this.status = status;
        this.body = body;
        //super(body, status);
    }


    public BaseResponseObj(HttpStatus status, String message) {
        //super(status);
        this.status = status;
        this.message = message;
    }

    public BaseResponseObj(HttpStatus status, String message, String remarks) {
        //super(status);
        this.status = status;
        this.message = message;
        this.remarks = remarks;
    }

    public BaseResponseObj(HttpStatus status, String message, String remarks, String forwardUrl) {
        //super(status);
        this.status = status;
        this.message = message;
        this.remarks = remarks;
        this.forwardUrl = forwardUrl;
    }

}
