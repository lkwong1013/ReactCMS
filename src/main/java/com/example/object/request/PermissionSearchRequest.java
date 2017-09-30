package com.example.object.request;

import lombok.Data;

/**
 * Created by LKW on 2017/9/30.
 */

@Data
public class PermissionSearchRequest extends BaseQuery {

    private String url;

    private String permissionName;

}
