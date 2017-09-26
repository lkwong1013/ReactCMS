package com.example.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;


/**
 * Created by LKW on 2017/8/21.
 */

@Configuration
@EnableSwagger2
public class Swagger2 {

        @Bean
        public Docket createRestApi ()  {
            return  new Docket(DocumentationType.SWAGGER_2)
                    .globalOperationParameters(
                            newArrayList(
                                new ParameterBuilder().name("lang").description("Language")
                                        .modelRef(new ModelRef("string"))
                                        .parameterType("header")
                                        .defaultValue("en")
                                        .required(false)
                                        .build(),
                                new ParameterBuilder().name("username").description("User Name")
                                        .modelRef(new ModelRef("string"))
                                        .parameterType("header")
                                        .defaultValue("admin_0807")
                                        .required(false)
                                        .build(),
                                new ParameterBuilder().name("token").description("Login Token")
                                        .modelRef(new ModelRef("string"))
                                        .parameterType("header")
                                        .defaultValue("e7502b4fc78137e7966db5daa5bad675565ecbbc44bdcf3cab42b03f1c0c9213")
                                        .required(false)
                                        .build()
                            ))
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

