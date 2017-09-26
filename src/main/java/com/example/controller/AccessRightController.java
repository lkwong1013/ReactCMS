package com.example.controller;

import com.example.object.request.UserPermissionRequest;
import com.example.object.request.UserRoleRequest;
import com.example.service.UserPermissionService;
import com.example.service.UserRoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.example.controller.ApiGenericController.ROOT_URL;

/**
 * Created by LKW on 2017/9/27.
 */

@Controller
@RequestMapping(ROOT_URL + AccessRightController.MODULE)
public class AccessRightController {

    protected static final String MODULE = "access";

    @Autowired
    private UserPermissionService userPermissionService;

    @Autowired
    private UserRoleService userRoleService;

    Logger log = Logger.getLogger(this.getClass());

    @RequestMapping(value = {"/api/addPermission"}, method = {RequestMethod.PUT})
    @ResponseBody
    public ResponseEntity addPermission(@RequestBody UserPermissionRequest request) {

        userPermissionService.savePermission(request);
        return new ResponseEntity(HttpStatus.OK);

    }


    @RequestMapping(value = {"/api/addRole"}, method = {RequestMethod.PUT})
    @ResponseBody
    public ResponseEntity addRole(@RequestBody UserRoleRequest request) {

        userRoleService.createRole(request);
        userPermissionService.loadRoleAuthMap();
        return new ResponseEntity(HttpStatus.OK);

    }

}
