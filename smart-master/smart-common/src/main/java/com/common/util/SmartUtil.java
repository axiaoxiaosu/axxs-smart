package com.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * lwq
 */
public abstract class SmartUtil {

    public static final String SUPERUSER = "SuperAdmin";

    /**
     * 判断操作是否成功
     */
    public static boolean isSucceed(Object t) {
        if (t == null) {
            return false;
        } else if (t.getClass().isAssignableFrom(int.class)) {
            return (int) t > 0;
        } else if (t.getClass().isAssignableFrom(Integer.class)) {
            return (Integer) t > 0;
        }
        return false;
    }

    /**
     * 判断是否是超级管理员
     */
    public static boolean isSuperUser(String userRole) {
        return userRole != null && StringUtils.equals(userRole, SUPERUSER);
    }

}
