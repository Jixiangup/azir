package com.bnyte.azir.api.vo.user;

import io.swagger.annotations.ApiModel;

/**
 * @author bnyte
 * @since 1.0.0
 */
@ApiModel("当前用户")
public class CurrentUserVO {

    /**
     * 用户主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 是否为admin用户
     */
    private Boolean admin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
