package com.bnyte.azir.api.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author bnyte
 * @since 2022/6/5 15:24
 */
@ApiModel("用户对象")
public class UserVO {

    /**
     * 用户主键id
     */
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 用户登录账号
     */
    @ApiModelProperty("用户登录账号")
    private String account;

    /**
     * 用户名称
     */
    @ApiModelProperty("用户名称")
    private String username;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String avatar;

    /**
     * 是否为admin用户
     */
    @ApiModelProperty("是否为admin用户")
    private Boolean admin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
