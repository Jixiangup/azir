package com.bnyte.azir.common.web.response;

import com.bnyte.forge.annotation.EventField;
import com.bnyte.forge.annotation.TimestampField;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author bnyte
 * @since 1.0.0
 */
@Api("响应结果集")
public class R<T> implements Serializable {

    private static final long serialVersionUID = 9426410000L;

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
     * 本次请求事件id
     */
    @EventField
    @ApiModelProperty("事件id")
    private String requestId;

    /**
     * 请求时间戳
     */
    @TimestampField
    @ApiModelProperty("请求时间戳")
    private Long timestamp;

    /**
     * 响应结果
     */
    @ApiModelProperty("响应结果对象")
    private T data;

    /**
     * 提供给前端确认请求是否成功
     */
    @ApiModelProperty("标记本次请求是否成功")
    private Boolean status = false;

    private R() {

    }

    private R(T data) {
        this.data = data;
    }

    /**
     * 响应空data数据
     * @return 响应空data数据 状态为OK {@link Code}
     */
    public static <T> R<T> ok(T data) {
        return R.build(new R<>(data));
    }

    /**
     * 响应空data数据
     * @return 响应空data数据 状态为error {@link Code}
     */
    public static R<Void> fail(Code code) {
        return R.build(code);
    }

    /**
     * 响应空data数据并指定message
     * @return 响应空data数据 状态为error {@link Code}
     */
    public static R<Void> fail(String message, Code code) {
        return R.build(message, code);
    }

    /**
     * 响应空data数据
     * @return 响应空data数据 状态为OK {@link Code}
     */
    public static R<Void> empty() {
        return R.build(new R<>());
    }

    private static R<Void> build(Code code) {
        R<Void> voidR = new R<>();
        voidR.setCode(code.getCode());
        voidR.setMessage(code.getMessage());
        voidR.setStatus(voidR.getCode().equals(Code.OK.getCode()));
        return voidR;
    }

    private static R<Void> build(String message, Code code) {
        R<Void> voidR = new R<>();
        voidR.setCode(code.getCode());
        voidR.setMessage(String.format(code.getMessage(), message));
        voidR.setStatus(voidR.getCode().equals(Code.OK.getCode()));
        return voidR;
    }

    private static <T> R<T> build(R<T> r) {
        r.setCode(Code.OK.getCode());
        r.setMessage(Code.OK.getMessage());
        r.setStatus(r.getCode().equals(Code.OK.getCode()));
        return r;
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

    public R<T> code(Integer code) {
        this.code = code;
        return this;
    }

    public R<T> message(String message) {
        this.message = message;
        return this;
    }

    public R<T> data(T data) {
        this.data = data;
        return this;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
