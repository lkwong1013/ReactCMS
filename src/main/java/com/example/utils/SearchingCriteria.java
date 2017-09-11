package com.example.utils;


/**
 * Created by LKW on 2017/8/9.
 */

public interface SearchingCriteria<T> {

    public T convert(T source) throws IllegalAccessException;

}
