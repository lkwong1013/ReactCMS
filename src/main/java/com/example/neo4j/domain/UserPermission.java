package com.example.neo4j.domain;

import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by LKW on 2017/8/6.
 */

@NodeEntity
@Data
public class UserPermission extends BaseEntity {

    private String permissionName;

    private String url;

}
