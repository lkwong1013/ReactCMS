package com.example.service.impl;

import com.example.neo4j.domain.UserPermission;
import com.example.neo4j.domain.UserRole;
import com.example.neo4j.repo.UserPermissionRepository;
import com.example.neo4j.repo.UserRoleRepository;
import com.example.object.request.UserRoleRequest;
import com.example.service.UserRoleService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Created by LKW on 2017/8/6.
 */

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserPermissionRepository userPermissionRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public void createRole(UserRoleRequest request) {

        UserRole userRole = new UserRole();
        userRole.setRoleName(request.getRoleName());
        Iterable <UserPermission> userPermission = userPermissionRepository.findAll(request.getPermissionIdList());


        if (userPermission != null) {
            List<UserPermission> permissionList = Lists.newArrayList(userPermission);
            userRole.setHavePermission(permissionList);
            userRoleRepository.save(userRole);
        }

    }

}
