package com.example.service;

import com.example.domain.Account;
import com.example.model.SUserAccount;
import com.example.repository.AccountRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by LKW on 2017/7/26.
 */

@Service
public class UserAccountSecurityService implements UserDetailsService {

    @Autowired
    private AccountRepo userAccountRepo;

    Logger log = Logger.getLogger(this.getClass());
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account userAccount = userAccountRepo.findByUserName(username);
        Iterable<Account> accountList = userAccountRepo.findAll();
        log.info("Account List : " + accountList.toString());
        log.info("Login User : " + username);
        log.info("Retrieve User : " + userAccount.getUserName());
        log.info("Retrieve User Password : " + userAccount.getPassword());
        if (userAccount == null) {
            throw new UsernameNotFoundException(username);
        } else {
            UserDetails details = new SUserAccount(userAccount);
            return details;
        }
    }
}

