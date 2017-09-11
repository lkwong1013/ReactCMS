package com.example.service;


import com.example.object.response.BoxMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by LKW on 2016/12/12.
 */

public interface SystemMessageService {

    String getMessage(String key);

    void setBoxMessageList(HttpServletRequest request, String type, String message, Integer duration);

    List<BoxMessage> getBoxMessage(HttpServletRequest request);

    void clearBoxMessage(HttpServletRequest request);

}
