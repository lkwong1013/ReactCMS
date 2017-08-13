//package com.example.config;
//
//import com.example.domain.Account;
//import com.example.repository.UserAccountRepo;
//import com.mongodb.client.MongoCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.InternalAuthenticationServiceException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
///**
// * Created by LKW on 2017/7/26.
// */
//@Service
//public class MongoDBAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
//
////    @Autowired
////    private UserAccountRepo userAccountRepo;
//
//    @Override
//    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
//    }
//
////    @Override
////    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
////        UserDetails loadedUser;
//
////        try {
////           // Account client = userAccountRepo.findByUserName(username).get(0);
////           // loadedUser = new User(client.getUsername(), client.getPassword(), client.getAuthorities());
////        } catch (Exception repositoryProblem) {
////            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
////        }
////
////        if (loadedUser == null) {
////            throw new InternalAuthenticationServiceException(
////                    "UserDetailsService returned null, which is an interface contract violation");
////        }
////        return loadedUser;
//    }
//}
