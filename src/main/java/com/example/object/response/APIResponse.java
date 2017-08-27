package com.example.object.response;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by LKW on 2017/4/22.
 */

@Data
public class APIResponse {

    private HttpStatus status = HttpStatus.OK;
    private Object data;
    private String error;

    public APIResponse() {
        this(null);
    }

    public APIResponse(Object data) {
        this.data = data;
        this.error = null;
    }

    public ResponseEntity<APIResponse> send(HttpStatus status) {
        this.status = status;
        return new ResponseEntity<APIResponse>(this, status);
    }

    public ResponseEntity<APIResponse> send(HttpStatus status, Object data) {
        this.status = status;
        this.data = data;
        return new ResponseEntity<APIResponse>(this, status);
    }

    public ResponseEntity<APIResponse> send(HttpStatus status, Object data, String error) {
        this.status = status;
        this.error = error;
        this.data = data;
        return new ResponseEntity<APIResponse>(this, status);
    }

}