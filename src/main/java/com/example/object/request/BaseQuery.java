package com.example.object.request;

import lombok.Data;
import org.springframework.data.domain.Sort;

/**
 * Created by LKW on 2016/12/14.
 */
@Data
public class BaseQuery {

    protected static final String LIKE_PREFIX = ".*";

    private Integer page = 0;

    private Integer pageSize = 15;

    private String sortBy = "";

    private Sort.Direction sortDirection;

}
