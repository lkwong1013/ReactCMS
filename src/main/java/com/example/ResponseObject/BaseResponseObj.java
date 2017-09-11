package com.example.ResponseObject;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

/**
 * Created by LKW on 2016/10/29.
 */

@Data
public class BaseResponseObj {

    private Integer status = 200;

    private String message = "Success";

    private String remarks = "";

    private String forwardUrl = "";

    public BaseResponseObj() {
    }

    public BaseResponseObj(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public BaseResponseObj(Integer status, String message, String remarks) {
        this.status = status;
        this.message = message;
        this.remarks = remarks;
    }

    public BaseResponseObj(Integer status, String message, String remarks, String forwardUrl) {
        this.status = status;
        this.message = message;
        this.remarks = remarks;
        this.forwardUrl = forwardUrl;
    }

}
