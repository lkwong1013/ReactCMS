package com.example.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * Created by LKW on 2016/11/4.
 */
public class BaseUtils<T> {

    @Autowired
    private MessageSource messageSource;

    private static final Logger log = Logger.getLogger(BaseUtils.class);

    public BaseUtils() {

    }
    public static String getMessage(String key) {

        BaseUtils b = new BaseUtils();
        String result = b.getMessageContent(key);
        return result;

    }

    public String getMessageContent(String key) {

        Locale currentLocale = LocaleContextHolder.getLocale();
        String test = messageSource.getMessage(key, null, currentLocale);
        return messageSource.getMessage(key, null, currentLocale);

    }

    public static String encodeBase64 (byte[] source) {
        final Base64 base64 = new Base64();
        String base64Image = base64.encodeAsString(source);
        return base64Image;
    }


}
