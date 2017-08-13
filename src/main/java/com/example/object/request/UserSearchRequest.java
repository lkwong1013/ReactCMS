package com.example.object.request;

import lombok.Data;

/**
 * Created by LKW on 2017/8/8.
 */

@Data
public class UserSearchRequest extends BaseQuery {

    private String name = "";

    private String email = "";

}
