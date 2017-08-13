package com.example.object.request;

import lombok.Data;

/**
 * Created by LKW on 2016/11/15.
 */

@Data
public class BaseRequestObj {

    private String forwardUrl;

    private String language;

    private Integer offset;

    private Integer listSize;

    private String token;

}
