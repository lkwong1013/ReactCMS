package com.example.neo4j.repo;

import com.example.neo4j.domain.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LKW on 2017/5/26.
 */
@Repository
public interface UserEntityRepo extends BaseRepository<UserEntity> {

    List<UserEntity> findByName(String username);

    @Query("MATCH (user:UserEntity) WHERE user.name=~ {0} and user.email=~ {1} return user")
    List<UserEntity> searchByCriteria(String username, String email, Pageable pageable);
//    List<UserEntity> findAll();
}
