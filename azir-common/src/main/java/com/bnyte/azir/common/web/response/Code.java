package com.bnyte.azir.common.web.response;

/**
 * 1-10中间状态
 * 10-100用户、权限异常
 * 101-200校验异常
 *
 * @author bnyte
 * @since 2022/5/27 16:16
 */
public enum Code {
    OK(0, "succeed"),
    FAIL(-1, "failed"),

    // 用户、权限 10 - 100
    AUTHENTICATION_ERROR(10, "用户鉴权失败"),

    // 校验异常
    VALIDATION_ERROR(101, "%s")
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
