//package com.example.config;
//
//import lombok.val;
//import org.neo4j.ogm.session.Session;
//import org.neo4j.ogm.session.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
////import org.springframework.data.neo4j.config.Neo4jConfiguration;
//import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
//import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
//import org.springframework.data.transaction.ChainedTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//
///**
// * Created by LKW on 2017/7/30.
// */
//
//@Configuration
//@EnableNeo4jRepositories(basePackages = "com.example.neo4j.repo")
//@EnableTransactionManagement
//@ComponentScan("com.example.neo4j.repo")
//public class Neo4jConfig /*extends Neo4jConfiguration */{
//
//    @Bean
//    public org.neo4j.ogm.config.Configuration getConfiguration() {
//        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
//        config
//                .driverConfiguration()
//                .setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
//                .setURI("http://admin:123456@192.168.1.58:7474");
//        return config;
//    }
//
//    @Bean
//    public SessionFactory sessionFactory() {
//        // with domain entity base package(s)
//        return new SessionFactory("org.neo4j.example.domain");
//    }
//
//    @Bean
//    public Neo4jTransactionManager transactionManager() {
//        return new Neo4jTransactionManager((Session) sessionFactory());
//    }
//
////    @Override
////    public SessionFactory getSessionFactory() {
////        return new SessionFactory(getConfiguration(), "com.project.neo4j.domain");
////    }
//
////    @Override
////    @Bean
////    public Session getSession() throws Exception {
////        return super.getSession();
////    }
//
////    @Override
////    @Bean
////    public SessionFactory getSessionFactory() {
////        return new SessionFactory(getConfiguration(), "com.project.neo4j.domain");
////    }
////
////    @Bean
////    public Session getSession() throws Exception {
////        return super.getSession();
////    }
////
////
////    @Autowired
//////    @Bean(name = "neo4jTransactionManager")
////    @Bean
////    public Neo4jTransactionManager neo4jTransactionManager(Session sessionFactory) {
////        return new Neo4jTransactionManager(sessionFactory);
////    }
////
////
////    @Autowired
////    @Bean(name = "transactionManager")
////    public PlatformTransactionManager transactionManager(Neo4jTransactionManager neo4jTransactionManager) {
////        return new ChainedTransactionManager(
////                neo4jTransactionManager
////        );
////    }
//
////    SessionFactory sessionFactory = new SessionFactory("com.project.neo4j.domain");
////    @Bean
////    public SessionFactory sessionFactory() {
////        return sessionFactory;
////    }
////    @Bean
////    public Neo4jTransactionManager transactionManager() {
////        return new Neo4jTransactionManager(sessionFactory());
////    }
////
////    @Bean
////    public Session session() {
////        return sessionFactory.openSession();
////    }
//
////    @Bean
////    public org.neo4j.ogm.config.Configuration configuration() {
////        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration();
////        configuration.driverConfiguration()
////        .setDriverClassName("org.neo4j.ogm.drivers.bolt.driver.BoltDriver")
////        .setURI("bolt://admin:123456@192.168.1.58");
//////                .uri("bolt://192.168.1.58")
//////                .credentials("admin", "123456")
//////                .build();
////        return configuration;
////    }
////
////    @Bean
////    public SessionFactory SessionFactory() {
////        // with domain entity base package(s)
////        return new SessionFactory(configuration(), "com.project.neo4j.domain");
////    }
////
////    @Bean
////    public Neo4jTransactionManager transactionManager() {
////        return new Neo4jTransactionManager(SessionFactory());
////    }
////
////    @Bean
////    public Session session() {
////        return this.SessionFactory().openSession();
////    }
//
//}