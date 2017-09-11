package com.example.neo4j.domain;

import com.example.neo4j.domain.relation.RelationshipTypes;
import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Created by LKW on 2017/8/5.
 */

@NodeEntity
@Data
public class UserRole extends BaseEntity {

    private String roleName;

    @Relationship(type = RelationshipTypes.PERMISSON, direction = "OUTGOING")
    private List<UserPermission> havePermission;

}
