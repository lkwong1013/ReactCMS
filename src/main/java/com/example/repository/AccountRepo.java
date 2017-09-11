package com.example.repository;

import com.example.domain.Account;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by LKW on 2017/7/26.
 */

@Repository
public interface AccountRepo extends CrudRepository<Account, String> {

    @Query("{'userName' : ?0}")
    Account findByUserName(String username);

}
