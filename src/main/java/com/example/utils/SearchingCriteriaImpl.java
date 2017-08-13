package com.example.utils;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * Created by LKW on 2017/8/12.
 */

@Service("searchingCriteria")
public class SearchingCriteriaImpl<T> implements SearchingCriteria<T> {

    private static final Logger log = Logger.getLogger(SearchingCriteria.class);

    public T convert (T source) throws IllegalAccessException {

        Class<?> c = source.getClass();
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.get(source) != null) {
                // String class check
                if (field.get(source).getClass() == String.class) {
                    String fieldValue = (String)field.get(source);
                    fieldValue = ".*" + fieldValue + ".*";
                    field.set(source, fieldValue);
                }
            }
        }
        return source;
    }

}
