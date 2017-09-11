package com.example.object.response;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

/**
 * Created by LKW on 2017/1/7.
 */

@Data
public class IMessage {

    @JsonView(Views.Public.class)
    private String message;

    public IMessage() {}

    public IMessage(String message) {
        this.message = message;
    }

}
