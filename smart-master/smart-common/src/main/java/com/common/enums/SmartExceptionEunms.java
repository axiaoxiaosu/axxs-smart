package com.common.enums;

/**
 * @author lwq
 * @data 2019/7/12
 */
public enum SmartExceptionEunms {
    LOGINEROOR(10002, "登陆失败,请检查用户名或密码", "登陆失败,请检查用户名或密码"),
    REGISTER_ERROR(10003, "注册失败...请稍后重试", "注册失败...请稍后重试"),
    TOKEN_ERROR(10004, "请携带正确的token", "请携带正确的token"),
    NOT_PERMISSION(10005, "没有权限操作", "没有权限操作"),
    NOT_FIND_MENUS(10006, "没有发现角色资源", "没有发现角色资源"),
    PARAMETER_NULL(201, "角色名称不能为空", "参数不能为空"),


    DELETE_ERROR(10007, "删除失败,请联系管理员", "删除失败"),
    ROLE_NOT_FIND(10008, "角色未找到", "角色未找到"),

    RORLE_BIND_USER(10008, "该角色绑定者用户,无法进行操作", "该角色绑定者用户,无法进行操作"),
    ;


    private int code;
    private String errMsg;
    private String msg;

    SmartExceptionEunms(int code, String msg, String errMsg) {
        this.code = code;
        this.msg = msg;
        this.errMsg = errMsg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
