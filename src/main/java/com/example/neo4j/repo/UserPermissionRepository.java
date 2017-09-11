package com.example.neo4j.repo;


import com.example.neo4j.domain.UserPermission;

import java.util.List;

/**
 * Created by LKW on 2017/8/6.
 */
public interface UserPermissionRepository extends BaseRepository<UserPermission> {

    UserPermission findByPermissionName(String permissionName);

    List<UserPermission> findByIdIn(List<Long> idList);

    List<UserPermission> findByUrlLike(String url);

}
