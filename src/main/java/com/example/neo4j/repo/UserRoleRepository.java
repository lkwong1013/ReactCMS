package com.example.neo4j.repo;


import com.example.neo4j.domain.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;

import java.util.List;

/**
 * Created by LKW on 2017/8/6.
 */
public interface UserRoleRepository extends BaseRepository<UserRole> {

    @Query("MATCH (role:UserRole) " +
            "where role.roleName =~{0} " +
            "OPTIONAL MATCH (role:UserRole)-[rp:HAVE_PERMISSION]-(p:UserPermission) " +
            "where role.roleName =~{0} " +
            "RETURN role, rp, p ")
    Page<UserRole> searchByCriteriaPage(String userRole, Pageable pageable);

    List<UserRole> findByRoleName(String roleName);

}
