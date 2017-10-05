package com.example.service;


import com.example.neo4j.domain.UserRole;
import com.example.object.request.RoleSearchRequest;
import com.example.object.request.UserRoleRequest;
import com.example.object.response.accessRight.UserRoleResponse;
import org.springframework.data.domain.Page;

/**
 * Created by LKW on 2017/8/6.
 */
public interface UserRoleService {

    void createRole(UserRoleRequest request);

    UserRoleResponse getUserRole(Long id);

    Page<UserRole> roleList(RoleSearchRequest request) throws IllegalAccessException;

    void updateRole(Long id, UserRoleRequest request);

    void removeRole(Long id);

}
