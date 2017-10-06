package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * Created by LKW on 2017/7/26.
 */
@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
//@EnableMongoRepositories
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

//    @Autowired
//    private MongoDBAuthenticationProvider authenticationProvider;

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/admin/*").hasRole("ADMIN")
                .antMatchers("/auth/*").hasAnyRole("ADMIN","USER")
                .antMatchers("/test/*").authenticated()
                //.antMatchers("/*").permitAll()
                .and()
                .httpBasic()
                .and()
                // .exceptionHandling().accessDeniedPage("/my-error-page")
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and()
                .headers().cacheControl().disable()
        ;
    }

    // create two users, admin and user


}