package com.example.service.impl;

import com.example.exception.DuplicateRecordFoundException;
import com.example.exception.ParameterMissingException;
import com.example.exception.RecordNotFoundException;
import com.example.neo4j.domain.UserPermission;
import com.example.neo4j.domain.UserRole;
import com.example.neo4j.repo.UserPermissionRepository;
import com.example.neo4j.repo.UserRoleRepository;
import com.example.object.request.RoleSearchRequest;
import com.example.object.request.UserRoleRequest;
import com.example.service.UserRoleService;
import com.example.utils.SearchingCriteria;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
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

    @Autowired
    private SearchingCriteria<RoleSearchRequest> searchRequestSearchingCriteria;

    private final Logger log = Logger.getLogger(this.getClass());

    public Page<UserRole> roleList(RoleSearchRequest request) throws IllegalAccessException {

        searchRequestSearchingCriteria.convert(request);
        Pageable pageRequest = new PageRequest(request.getPage(), request.getPageSize(), request.getSortDirection(), request.getSortBy());
        Page<UserRole> pageResult = userRoleRepository.searchByCriteriaPage(request.getRoleName(), pageRequest);
        log.info(request.getRoleName());
        return pageResult;

    }

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

    public void updateRole(Long id, UserRoleRequest request) {

        UserRole source = userRoleRepository.findOne(id);

        // Check original record
        if (source == null) {
            throw new RecordNotFoundException();
        }

        // Request Parameter Check
        if (StringUtils.isBlank(request.getRoleName())) {
            throw new ParameterMissingException("Role Name");
        }

        // Duplication Check
        List<UserRole> chkDuplicate = new ArrayList<>();
        if (!request.getRoleName().equals(source.getRoleName())) {
            chkDuplicate = userRoleRepository.findByRoleName(request.getRoleName());
            if (chkDuplicate != null && chkDuplicate.size() > 0) {
                throw new DuplicateRecordFoundException();
            }
        }

        // Normal Case
        source.setRoleName(request.getRoleName());

        if (request.getPermissionIdList() != null && request.getPermissionIdList().size() > 0) {
            Iterable <UserPermission> permissionList = userPermissionRepository.findAll(request.getPermissionIdList());
            if (permissionList != null) {
                source.setHavePermission(Lists.newArrayList(permissionList));
            } else {
                throw new RecordNotFoundException("Permission not found");
            }
        } else {
            source.setHavePermission(null);
        }

        userRoleRepository.save(source);


    }

}
