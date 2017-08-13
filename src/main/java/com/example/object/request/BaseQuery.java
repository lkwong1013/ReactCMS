package com.example.object.request;

import lombok.Data;

/**
 * Created by LKW on 2016/12/14.
 */
@Data
public class BaseQuery {

    protected static final String LIKE_PREFIX = ".*";

    private Integer offset = 0;

    private Integer pageSize = 15;

}
