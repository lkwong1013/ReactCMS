package com.example.controller;

import com.example.neo4j.domain.UserEntity;
import com.example.neo4j.repo.UserEntityRepo;
import com.example.object.request.UserPermissionRequest;
import com.example.object.request.UserRoleRequest;
import com.example.object.request.UserSearchRequest;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by LKW on 2017/7/31.
 */

@Controller
@RequestMapping("/neo4j")
public class Neo4JTestController {

    @Autowired
    private UserEntityRepo userEntityRepo;

    @Autowired
    private UserPermissionService userPermissionService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private SearchingCriteria<UserSearchRequest> searchRequestSearchingCriteria;

    Logger log = Logger.getLogger(this.getClass());

    @RequestMapping(value = {"/api/searchData"}, method = {RequestMethod.POST})
    @ResponseBody
    public BaseResponseObj searchTest(@RequestBody UserSearchRequest request) throws IllegalAccessException {

        searchRequestSearchingCriteria.convert(request);
        Pageable pageRequest = new PageRequest(0, 5);
        List<UserEntity> result = userEntityRepo.searchByCriteria(request.getName(), request.getEmail(), pageRequest);
        log.info(request.getName());
        return new BaseResponseObj(HttpStatus.OK, result);

    }

    private String ucFirst(String input) {
        return input.toUpperCase();
//        if(input.length() <= 1){
//            return input.toUpperCase();
//        }
//        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }


    @RequestMapping(value = {"/api/getData"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseEntity getNeo4JData() {



        List<UserEntity> userAccountMList = userEntityRepo.findAll();

        ResponseEntity response = new ResponseEntity(userAccountMList, HttpStatus.OK);



        Thread t = new Thread(new ThreadTesting(0));
        t.start();
        log.info("No waiting...");
        return response;

    }

    @RequestMapping(value = {"/api/pagingTest"}, method = {RequestMethod.GET})
    @ResponseBody
    public BaseResponseObj pagingTest() {
        Pageable pageable = new PageRequest(0, 5);
        Page<UserEntity> paged = userEntityRepo.findAll(pageable);

        return new BaseResponseObj(HttpStatus.OK, paged);

    }

    @RequestMapping(value = {"/api/addPermission"}, method = {RequestMethod.POST})
    @ResponseBody
    public ResponseEntity addPermission(@RequestBody UserPermissionRequest request) {

        userPermissionService.savePermission(request);

        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = {"/api/addRole"}, method = {RequestMethod.POST})
    @ResponseBody
    public ResponseEntity addRole(@RequestBody UserRoleRequest request) {

        userRoleService.createRole(request);
        userPermissionService.loadRoleAuthMap();
        return new ResponseEntity(HttpStatus.OK);

    }


}
