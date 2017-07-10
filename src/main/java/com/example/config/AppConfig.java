package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by max.wong on 7/7/2017.
 */
//@Configuration
//public class StaticResourceConfiguration extends WebMvcConfigurerAdapter {
//
//    @Bean
//    public ViewResolver getViewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/");
//        resolver.setSuffix(".html");
//        return resolver;
//    }
//
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
//    }
//}


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

@Configuration
class AppConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".html");

        return viewResolver;
    }

//    @Bean
//    public MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename("/WEB-INF/i18/resources");
//        messageSource.setDefaultEncoding("UTF-8");
//        return messageSource;
//    }
//
//    @Bean
//    public LocaleResolver localeResolver(){
//        CookieLocaleResolver resolver = new CookieLocaleResolver();
//        resolver.setDefaultLocale(new Locale("en")); // resources_{languangeName}.properties
//        resolver.setCookieName("myLocaleCookie");
//        resolver.setCookieMaxAge(4800);
//        return resolver;
//    }
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**/api/**").allowedOrigins("*");
//    }

    // Added on 20161226 for File upload module
//    @Bean(name="multipartResolver")
//    public CommonsMultipartResolver getResolver() throws IOException {
//        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//        //resolver.setMaxUploadSizePerFile(5242880);//5MB
//        return resolver;
//    }

    // Email feature
//    @Bean
//    public JavaMailSender getMailSender(){
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//
//        //Using gmail
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//        mailSender.setUsername("pubmaxwong@gmail.com");
//        mailSender.setPassword("max62001");
//
//        Properties javaMailProperties = new Properties();
//        javaMailProperties.put("mail.smtp.starttls.enable", "true");
//        javaMailProperties.put("mail.smtp.auth", "true");
//        javaMailProperties.put("mail.transport.protocol", "smtp");
//        javaMailProperties.put("mail.debug", "true");//Prints out everything on screen
//        javaMailProperties.put("mail.smtp.allow8bitmime", "true");
//        javaMailProperties.put("mail.smtps.allow8bitmime", "true");
//
//        mailSender.setJavaMailProperties(javaMailProperties);
//        return mailSender;
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/source/**").addResourceLocations("/source/");
        registry.addResourceHandler("/lib/**").addResourceLocations("/lib/");
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/views/static/");
    }

    /**
     *
     * Exception Handler registration
     */
//	@Bean
//	public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
//		SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
//		Properties errorMaps = new Properties();
//		errorMaps.setProperty("NotLoggedInException","loginPage");
////		//errorMaps.setProperty("NullPointerException", "error");
//		resolver.setExceptionMappings(errorMaps);
////		resolver.setDefaultErrorView("loginPage");
////		resolver.setExceptionAttribute("exc");
//		return resolver;
//	}


    /**
     *
     * Interceptor registration
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //registry.addInterceptor(new TestInterceptor());
//
//        // Restrict the interceptor run in the defined path pattern
//
//        // Run This interceptor except login
//        //registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**").excludePathPatterns("/login/*").excludePathPatterns("/loginAction/");
//
//        //registry.addInterceptor(new TestInterceptor()).addPathPatterns("/login/*");
//
//        //registry.addInterceptor(new TransactionInterceptor()).addPathPatterns("/person/save/*");
//
//		/*----- [Start] Security Interceptor -----*/
//        registry.addInterceptor(securityInterceptor()).excludePathPatterns("/**/api/**");
//		/*----- [End] Security Interceptor -----*/
//		/*----- [Start] API Interceptor -----*/
//        registry.addInterceptor(apiInterceptor()).addPathPatterns("/**/api/**").excludePathPatterns("/**/login");
//		/*----- [End] API Interceptor -----*/
//		/*----- [Start] Locale Change Interceptor -----*/
//        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
//        interceptor.setParamName("locale");
//        registry.addInterceptor(interceptor);
//		/*----- [End] Locale Change Interceptor -----*/
//
//    }

//    @Bean
//    public SecurityInterceptor securityInterceptor() {
//        return new SecurityInterceptor();
//    }
//
//    @Bean
//    public ApiInterceptor apiInterceptor() {
//        return new ApiInterceptor();
//    }
}

