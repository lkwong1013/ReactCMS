package com.example.neo4j.domain;

import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by LKW on 2017/7/31.
 */

@NodeEntity
@Data
public abstract class BaseEntity implements Serializable {

    @GraphId
    private Long id;

    @LastModifiedDate
    private Date lastUpdate;

    @CreatedDate
    private Date createDate;

}
