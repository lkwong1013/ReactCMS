package com.example.object.response.accessRight;

import com.example.neo4j.domain.UserPermission;
import lombok.Data;

import java.util.List;

/**
 * Created by LKW on 2017/10/5.
 */

@Data
public class UserRoleResponse {

    private String roleName;

    private List<UserPermission> permissionList;

}

