package com.bnyte.azir.common.entity.console;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bnyte.azir.common.entity.AutoId;

/**
 * @author bnyte
 * @since 2022/5/26 17:33
 */
@TableName("t_console_cluster")
public class Cluster extends AutoId {

    /**
     * 集群中文名称
     */
    private String cnName;

    /**
     * 集群英文名称
     */
    private String enName;

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
}
