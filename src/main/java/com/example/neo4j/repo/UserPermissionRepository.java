package com.example.neo4j.repo;


import com.example.neo4j.domain.UserPermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;

import java.util.List;

/**
 * Created by LKW on 2017/8/6.
 */
public interface UserPermissionRepository extends BaseRepository<UserPermission> {

    List<UserPermission> findByPermissionName(String permissionName);

    List<UserPermission> findByIdIn(List<Long> idList);

    List<UserPermission> findByUrlLike(String url);

    @Query("MATCH (permission:UserPermission) WHERE permission.url=~ {0} and permission.permissionName=~ {1} return permission")
    Page<UserPermission> searchByCriteriaPage(String username, String email, Pageable pageable);

}
