package com.bnyte.azir.common.enums;

/**
 * @author bnyte
 * @since 1.0.0
 */
public enum  EUserStatus {

    NORMAL("正常", 0),
    FREEZE("冻结", 1),
    AWAITING_ACTIVATION("待激活", 2),
    ;


    /**
     * 备注
     */
    private String remark;

    /**
     * 数据库写入key
     */
    private Integer key;

    EUserStatus(String remark, Integer key) {
        this.remark = remark;
        this.key = key;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }
}
