package com.example.service.impl;

import com.example.object.response.BoxMessage;
import com.example.service.SystemMessageService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by LKW on 2016/12/12.
 */

@Component("SystemMessageService")
@Transactional
public class SystemMessageServiceImpl implements SystemMessageService {

    @Autowired
    MessageSource messageSource;

    @Autowired
    private HttpServletRequest request;

    public String getMessage(String key) {

        //TODO check user language setting from DB
        Locale currentLocale = LocaleContextHolder.getLocale();
        currentLocale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);

        if (request.getHeader("lang") != null) {
            currentLocale = new Locale(request.getHeader("lang"));
        }

        String test = messageSource.getMessage(key, null, currentLocale);
        return messageSource.getMessage(key, null, currentLocale);

    }

    public void setBoxMessageList(HttpServletRequest request, String type, String message, Integer duration) {

        // Get from session first
        HttpSession session = request.getSession();
        BoxMessage boxMessage;
        List<BoxMessage> boxMessageList = (List<BoxMessage>)session.getAttribute("boxMessage");
        if (boxMessageList != null && boxMessageList.size() > 0) {
            boxMessage = new BoxMessage(type, message, duration);
            boxMessageList.add(boxMessage);
        } else {
            boxMessageList = new ArrayList<BoxMessage>();
            boxMessage = new BoxMessage(type, message, duration);
            boxMessageList.add(boxMessage);
        }

        this.saveBoxMessageSession(request, boxMessageList);

    }

    public List<BoxMessage> getBoxMessage(HttpServletRequest request) {

        HttpSession session = request.getSession();
        List<BoxMessage> boxMessageList = (List<BoxMessage>)session.getAttribute("boxMessage");
        if (boxMessageList != null && boxMessageList.size() > 0) {
            return boxMessageList;
        } else {
            return null;
        }
    }

    public void clearBoxMessage(HttpServletRequest request) {
        request.getSession().removeAttribute("boxMessage");
    }

    private void saveBoxMessageSession(HttpServletRequest request, List<BoxMessage> messageBoxList ) {
        Date expiryTime = new Date();
        DateUtils.addMinutes(expiryTime, 5);

        request.getSession().setAttribute("boxMessage", messageBoxList);
    }

}
