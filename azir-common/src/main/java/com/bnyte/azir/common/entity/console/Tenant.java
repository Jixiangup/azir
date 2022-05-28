package com.bnyte.azir.common.entity.console;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bnyte.azir.common.entity.AutoId;

/**
 * @author bnyte
 * @since 2022/5/26 18:17
 */
@TableName("t_console_tenant")
public class Tenant extends AutoId {

    /**
     * 租户中文名称
     */
    private String cnName;

    /**
     * 租户英文名称
     */
    private String enName;

    /**
     * 用户id
     */
    private Long userId;

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
