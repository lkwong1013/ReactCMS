package com.example.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


/**
 * Created by LKW on 2017/2/8.
 */

@Configuration
@ComponentScan({"com.project.configuration"})
@PropertySource(value = { "classpath:application.properties" })
public class EnvSetting {

    @Autowired
    private Environment environment;

    Logger log = Logger.getLogger(this.getClass());

    public String getSetting(String key) throws Exception {
        try {
            return environment.getRequiredProperty(key);
        } catch (Exception e) {
            log.error(e.getMessage());
            return "";
        }
    }
}


