package com.common.constants;

/**
 * @author lwq
 * @data 2019/8/16
 */
public class RedisKeyConstants {

    public static final String USERGROUPKEY = "user";
    public static final String USERROLE = "role";


    public static final String getRoleKey(String usernem) {
        return new StringBuffer(USERROLE).append("-").append(usernem).toString();
    }

}
