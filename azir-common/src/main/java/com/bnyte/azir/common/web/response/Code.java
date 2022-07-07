package com.bnyte.azir.common.web.response;

/**
 * 1-9中间状态
 * 10-99用户、权限异常
 * 100-199校验异常
 * 201-299系统类异常
 * 300-999业务逻辑类异常
 *
 * @author bnyte
 * @since 1.0.0
 */
public enum Code {
    OK(0, "succeed"),
    FAIL(-1, "failed"),

    // 用户、权限 10 - 99
    AUTHENTICATION_ERROR(10, "用户鉴权失败"),
    TOKEN_EXPIRED_ERROR(11, "token令牌过期"),
    THE_USER_DOES_NOT_EXIST_ERROR(12, "用户不存在"),
    USER_NOT_FOUND(13, "用户未找到"),
    PERMISSION_DENIED(14, "没有找到对应权限"),
    USERNAME_OR_PASSWORD_ERROR(15, "用户名或密码错误"),

    // 校验异常 100 - 199
    VALIDATION_ERROR(100, "%s"),
    TENANT_NOT_EXISTS(101, "租户不存在"),
    TENANT_EN_NAME_EXISTS(102, "租户英文名称已经存在"),
    TENANT_CN_NAME_EXISTS(103, "租户中文名称已经存在"),
    NO_TENANT_SELECTED(104, "没有租户选择"),

    // 系统异常 200 - 299
    SYSTEM_OPERATIONAL_ERROR(202, "系统操作错误"),
    SQL_EXECUTION_ERROR(203, "%s"),

    ;

    /**
     * 响应code
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String message;

    Code(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
