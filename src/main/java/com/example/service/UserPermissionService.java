package com.example.service;


import com.example.neo4j.domain.UserPermission;
import com.example.neo4j.domain.UserRole;
import com.example.object.AuthorityInfo;
import com.example.object.request.PermissionSearchRequest;
import com.example.object.request.UserPermissionRequest;
import com.example.object.response.accessRight.PermissionResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by LKW on 2017/8/6.
 */
public interface UserPermissionService {

    Page<UserPermission> permissionList(PermissionSearchRequest request) throws IllegalAccessException;

    void savePermission(UserPermissionRequest request);

    void updatePermission(Long id, UserPermissionRequest request);

    List<UserPermission> findByUrlLike(String url);

    PermissionResponse getPermission(Long id);

    void loadRoleAuthMap();

    Map<String, List<AuthorityInfo>> getRoleAuthMap();

    Boolean chkAuthByRole(List<UserRole> roleList, String requestURI);

}
