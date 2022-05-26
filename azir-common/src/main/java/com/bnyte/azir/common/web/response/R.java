package com.bnyte.azir.common.web.response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author bnyte
 * @since 2022/5/26 18:33
 */
@Api("响应结果集")
public class R<T> implements Serializable {

    /**
     * 响应状态码
     */
    @ApiModelProperty("响应状态码,成功为0反之失败")
    private Integer code;

    /**
     * 响应结果集
     */
    @ApiModelProperty("响应信息")
    private String message;

    /**
     * 响应结果
     */
    @ApiModelProperty("响应结果对象")
    private T data;

    private R() {

    }

    public static R<Void> empty() {
        return new R<>();
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    private void setCode(Integer code) {
        this.code = code;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    private void setData(T data) {
        this.data = data;
    }
}
