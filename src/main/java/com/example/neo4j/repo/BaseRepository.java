package com.example.neo4j.repo;


import com.example.neo4j.domain.BaseEntity;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LKW on 2017/8/5.
 */

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends GraphRepository<T> {

    List<T> findAll();

}
