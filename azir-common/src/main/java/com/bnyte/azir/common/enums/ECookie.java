package com.bnyte.azir.common.enums;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author bnyte
 * @since 1.0.0
 */
public enum ECookie {
    /**
     * 鉴权token key
     */
    X_ACCESS_TOKEN("X-ACCESS-TOKEN"),
    USERNAME("username"),
    TENANT_NAME("tenant_name"),
    TENANT_ID("tenant_id"),
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

    public static List<ECookie> logoutKeys() {
        return Stream.of(
                ECookie.X_ACCESS_TOKEN,
                ECookie.USERNAME,
                ECookie.TENANT_NAME,
                ECookie.TENANT_ID
        ).collect(Collectors.toList());
    }
}
