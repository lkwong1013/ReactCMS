package com.example.controller;

import com.example.object.request.BaseQuery;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

/**
 * Created by LKW on 2017/4/22.
 */

@Controller
public abstract class ApiGenericController {

    protected final static String ROOT_URL = "/api/";

    String baseUrl;

    String currentUrl;

    BaseQuery baseQuery = new BaseQuery();

    Logger log = Logger.getLogger(this.getClass());

}
