package com.example.object.response;

import lombok.Data;

/**
 * Created by LKW on 2016/11/12.
 */

@Data
public class BoxMessage /*extends BaseEntity*/ {

    private String type;

    private String message;

    private Integer duration;

    public BoxMessage(String type, String message, Integer duration) {

        this.type = type;
        this.message = message;
        this.duration = duration;

    }

}
