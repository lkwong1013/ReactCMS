package com.example;

import com.example.ResponseObject.BaseResponseObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DemoController {
    
    @RequestMapping(value="/echo")
    public String echo(@RequestParam(value="request", defaultValue="Hello!") String request) {
        return request;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponseObj testAPI() {
        return new BaseResponseObj();
    }

    @RequestMapping(value = "/")
    public String index() {
        return "static/index.html";
    }

}


