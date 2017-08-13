package com.example.service.impl;

import com.example.neo4j.domain.UserPermission;
import com.example.neo4j.domain.UserRole;
import com.example.neo4j.repo.UserPermissionRepository;
import com.example.neo4j.repo.UserRoleRepository;
import com.example.object.AuthorityInfo;
import com.example.object.request.UserPermissionRequest;
import com.example.service.UserPermissionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LKW on 2017/8/6.
 */

@Service
//@Transactional
public class UserPermissionServiceImpl implements UserPermissionService, InitializingBean {

    @Autowired
    Session session;

    @Autowired
    private UserPermissionRepository userPermissionRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    private final Logger log = Logger.getLogger(this.getClass());

    private Map<String, List<AuthorityInfo>> roleAuthorityMap;

    public void savePermission(UserPermissionRequest request) {

        UserPermission userPermission = new UserPermission();
        userPermission.setPermissionName(request.getPermissionName());
        userPermission.setUrl(request.getUrl());

        userPermissionRepository.save(userPermission);

    }

    public List<UserPermission> findByUrlLike(String url) {
        return userPermissionRepository.findByUrlLike(url);
    }

    public void loadRoleAuthMap() {

        roleAuthorityMap = new HashMap<String, List<AuthorityInfo>>();

        List<UserRole> userRoleList = this.userRoleRepository.findAll();

        if (userRoleList != null && userRoleList.size() > 0) {

            for (UserRole userRole : userRoleList) {
                List<AuthorityInfo> permittedInfo = new ArrayList<AuthorityInfo>();
                try {

                    List<UserPermission> permissionList = userRole.getHavePermission();
                    if (permissionList != null && permissionList.size() > 0) {
                        for (UserPermission obj : permissionList) {
                            AuthorityInfo authorityInfo = new AuthorityInfo();
                            // Added to avoid session not update
                            authorityInfo.setUrl(obj.getUrl());
                            authorityInfo.setPermissionName(obj.getPermissionName());
                            permittedInfo.add(authorityInfo);
                        }
                        roleAuthorityMap.put(userRole.getRoleName(), permittedInfo);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public Map<String, List<AuthorityInfo>> getRoleAuthMap() {
        return this.roleAuthorityMap;
    }

    public Boolean chkAuthByRole(List<UserRole> roleList, String requestURI) {

        String logPrefix = "chkAuthByRole(): ";

        if (roleList == null || StringUtils.isBlank(requestURI)) {
            log.error(logPrefix + "Parameter missing");
            return false;
        }

        // Super Admin - Granted all permission

        for (UserRole role : roleList) {
            if (("ROLE_SUPERADMIN").equals(role.getRoleName())) {
                log.info(logPrefix + requestURI + " - Permission Granted!");
                return true;
            }

            if (role.getHavePermission() != null && role.getHavePermission().size() > 0) {
                List<UserPermission> userPermissions = role.getHavePermission();
                for (UserPermission permission : userPermissions) {
                    if (requestURI.matches(permission.getUrl())) {  // Compare with regular expression
                        log.info(logPrefix + requestURI + " - Permission Granted! [superadmin]");
                        return true;
                    }
                }
            }

            List<AuthorityInfo> authorityInfoList = getRoleAuthMap().get(role.getRoleName());
            if (authorityInfoList == null) {
                log.error(logPrefix + "User Role not found");
                return false;
            }

            for (AuthorityInfo authorityInfo : authorityInfoList) {
                // Assume URL is regRex
                if (requestURI.matches(authorityInfo.getUrl())) {  // Compare with regular expression
                    log.info(logPrefix + requestURI + " - Permission Granted!");
                    return true;
                }
            }

        }
        log.info(logPrefix + requestURI + " - Permission Denied!");
        return false;

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        loadRoleAuthMap();
    }


}
