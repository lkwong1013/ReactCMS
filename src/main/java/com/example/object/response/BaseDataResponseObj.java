package com.example.object.response;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

/**
 * Created by LKW on 2017/4/22.
 */
public class BaseDataResponseObj<T> extends BaseResponseObj {

    @JsonView(Views.Public.class)
    private List<T> dataList;

    public BaseDataResponseObj(List<T> dataList) {
        super();
        this.dataList = dataList;
    }

}
