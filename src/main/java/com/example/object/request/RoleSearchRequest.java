package com.example.object.request;

import lombok.Data;

/**
 * Created by LKW on 2017/10/1.
 */

@Data
public class RoleSearchRequest extends BaseQuery {

    private String roleName;

}
