package com.example.object.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by LKW on 2017/3/4.
 */

@Data
public class CountryForm implements Serializable {

    private String countryCode;

    private String countryName;

}
