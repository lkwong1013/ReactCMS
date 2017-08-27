package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by LKW on 2017/8/21.
 */

@Configuration
@EnableSwagger2
public class Swagger2 {

        @Bean
        public Docket createRestApi ()  {
            return  new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage( "com.example.controller" ))
                    .paths(PathSelectors.any())
                    .build();
        }
        private ApiInfo apiInfo ()  {
            return  new ApiInfoBuilder()
                    .title( "Swagger2 RESTful APIs Test" )
                    .description( "Swagger2 RESTful APIs Test" )
                    .termsOfServiceUrl( "" )
                    .contact( new Contact("Max", "https://samaxxw.com", "pubmaxwong@gmail.com"))
                    .version( "1.0" )
                    .build();
        }
}

