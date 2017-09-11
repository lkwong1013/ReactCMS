package com.example.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;

/**
 * Created by LKW on 2016/11/10.
 */

@Configuration
@EnableMongoRepositories(basePackages = "com.example")
public class MongoDBConfig {

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        MongoCredential credential = MongoCredential.createMongoCRCredential("max", "baseProject", "z3bx4j".toCharArray());
        ServerAddress adr = new ServerAddress("192.168.1.58", 27017);

        MongoClient mongoClient = new MongoClient(adr, Arrays.asList(credential));

        SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(mongoClient, "baseProject");

        return simpleMongoDbFactory;

    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory()); // Select DB
        return mongoTemplate;
    }

}
