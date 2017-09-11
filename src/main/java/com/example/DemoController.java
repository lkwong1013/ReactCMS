package com.example;

import com.example.RequestObject.TestRequest;
import com.example.ResponseObject.BaseResponseObj;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DemoController {

    Logger log = Logger.getLogger(this.getClass());

    @RequestMapping(value="/echo")
    public String echo(@RequestParam(value="request", defaultValue="Hello!") String request) {
        return request;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponseObj testAPI(TestRequest obj) {

        log.info(obj.getCountryCode());
        log.info(obj.getCountryName());
        return new BaseResponseObj();
    }

    @RequestMapping(value = "/")
    public String homePage() {
        return "index";
    }

}


