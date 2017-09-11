package com.example.neo4j.domain;


import com.example.neo4j.domain.relation.RelationshipTypes;
import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;
import java.util.List;

/**
 * Created by LKW on 2017/7/31.
 */

@NodeEntity
@Data
public class UserEntity extends BaseEntity {

    private String uuid;
    private String name;
    private String tag;
    private String password;
    private String email;
    private String accessKey;
    private Date keyExpiryDate;

    @Relationship(type = RelationshipTypes.FRIEND, direction = "OUTGOING")
    private List<UserEntity> friendsTo;

    @Relationship(type = RelationshipTypes.ROLE, direction = "OUTGOING")
    private List<UserRole> haveRole;
}
