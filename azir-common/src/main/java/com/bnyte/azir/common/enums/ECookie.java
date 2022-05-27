package com.bnyte.azir.common.enums;

/**
 * @author bnyte
 * @since 2022/5/28 5:00
 */
public enum ECookie {
    /**
     * 鉴权token key
     */
    X_ACCESS_TOKEN("X-ACCESS-TOKEN"),
    USERNAME("username"),
    ;

    private String key;

    ECookie(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
