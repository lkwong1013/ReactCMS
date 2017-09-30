package com.example.controller;

import com.example.neo4j.domain.UserEntity;
import com.example.object.request.PermissionSearchRequest;
import com.example.object.request.UserPermissionRequest;
import com.example.object.request.UserRoleRequest;
import com.example.object.response.BaseResponseObj;
import com.example.service.UserPermissionService;
import com.example.service.UserRoleService;
import com.example.utils.SearchingCriteria;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.controller.ApiGenericController.ROOT_URL;

/**
 * Created by LKW on 2017/9/27.
 */

@RestController
@RequestMapping(ROOT_URL + AccessRightController.MODULE)
public class AccessRightController {

    protected static final String MODULE = "access";

    @Autowired
    private UserPermissionService userPermissionService;

    @Autowired
    private UserRoleService userRoleService;


    Logger log = Logger.getLogger(this.getClass());

    @RequestMapping(value = {"/permissionList"}, method = {RequestMethod.POST})
    public BaseResponseObj permissionList(@RequestBody PermissionSearchRequest request) throws IllegalAccessException {
        return new BaseResponseObj(HttpStatus.OK, userPermissionService.permissionList(request));
    }

    @RequestMapping(value = {"/api/addPermission"}, method = {RequestMethod.PUT})
    public ResponseEntity addPermission(@RequestBody UserPermissionRequest request) {

        userPermissionService.savePermission(request);
        return new ResponseEntity(HttpStatus.OK);

    }


    @RequestMapping(value = {"/api/addRole"}, method = {RequestMethod.PUT})
    public ResponseEntity addRole(@RequestBody UserRoleRequest request) {

        userRoleService.createRole(request);
        userPermissionService.loadRoleAuthMap();
        return new ResponseEntity(HttpStatus.OK);

    }

}
