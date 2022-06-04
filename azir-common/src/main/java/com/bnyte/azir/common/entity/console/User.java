package com.bnyte.azir.common.entity.console;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bnyte.azir.common.entity.AutoId;

/**
 * @author bnyte
 * @since 2022/5/26 18:24
 */
@TableName("t_console_user")
public class User extends AutoId {

    /**
     * 用户登录账号
     */
    private String account;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 是否为admin用户
     */
    private Boolean admin;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
