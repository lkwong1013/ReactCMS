package com.example.utils;

/**
 * Created by LKW on 2016/11/15.
 */
public class Constants {

    public static String baseUrl = "/";

    public static Integer STATUS_ENABLE = 0;
    public static Integer STATUS_DISABLE = 1;

    // -------- Change Password -------- //
    public static String MSG_PASSWORD_NOT_MATCH = "Password not match";
    public static String MSG_PASSWORD_UPDATED = "Password updated";

    // -------- File manage -------- //
    public static long MAX_FILE_SIZE = 5242880;
    public static String FILE_TYPE_JPG = "image/jpeg";
    public static String FILE_TYPE_PNG = "image/png";
    public static String MSG_FILE_OVERSIZE = "File size should not larger than " + MAX_FILE_SIZE;
    public static String MSG_INVALID_IMAGE_FILE_TYPE = "Staff icon only support : *.jpg or *.png";

    // -------- Global Message -------- //
    public static String MSG_INVALID_DB_RECORD = "Invalid Record found! Please check DB";
    public static String MSG_DUPLICATE_RECORD = "Duplicate Record found!";

    public static String MSG_SEQ_UPDATED = "Record Sequence updated";

    public static String SEQ_UP = "UP";
    public static String SEQ_DOWN = "DOWN";

    public static Integer BOX_MSG_SUCCESS_STATUS_CODE = 200;
    public static Integer BOX_MSG_FAILED_STATUS_CODE = 401;
    public static Integer BOX_MSG_DURATION = 3000; // 3 seconds
    public static String BOX_MSG_TYPE_SUCCESS = "success";

    // ------ User Role ------ //
    public static String USER_ROLE_DISABLE = "User Role Disabled";
    public static String USER_ROLE_ENABLE = "User Role Enable";


    public static String CACHE_TEST = "TEST CACHE";

    public static Constants constants;


    public static Constants getInstance() {

        if( constants == null ){
            constants = new Constants();
            return constants;
        }
        return constants;

    }

    public String getCacheTest() {
        return CACHE_TEST;
    }

}
