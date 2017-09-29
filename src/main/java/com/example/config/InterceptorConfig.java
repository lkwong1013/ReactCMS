package com.example.config;


import com.example.interceptor.ApiInterceptor;
import com.example.interceptor.SecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * Created by LKW on 2017/8/13.
 */

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    SecurityInterceptor securityInterceptor;
    /**
     *
     * Interceptor registration
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new TestInterceptor());

        // Restrict the interceptor run in the defined path pattern

        // Run This interceptor except login
        //registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**").excludePathPatterns("/login/*").excludePathPatterns("/loginAction/");

        //registry.addInterceptor(new TestInterceptor()).addPathPatterns("/login/*");

        //registry.addInterceptor(new TransactionInterceptor()).addPathPatterns("/person/save/*");

		/*----- [Start] Security Interceptor -----*/
//        registry.addInterceptor(securityInterceptor()).excludePathPatterns("/**/api/**");
		/*----- [End] Security Interceptor -----*/
		/*----- [Start] API Interceptor -----*/
        registry.addInterceptor(apiInterceptor()).addPathPatterns("/**/api/**").excludePathPatterns("/**/login", "/**/register");
		/*----- [End] API Interceptor -----*/
		/*----- [Start] Locale Change Interceptor -----*/
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("locale");
        registry.addInterceptor(interceptor);
		/*----- [End] Locale Change Interceptor -----*/

    }

    @Bean
    public SecurityInterceptor securityInterceptor() {
        return new SecurityInterceptor();
    }

    @Bean
    public ApiInterceptor apiInterceptor() {
        return new ApiInterceptor();
    }


}
