package com.bnyte.azir.common.util;

import com.bnyte.azir.common.entity.console.User;
import com.bnyte.azir.common.enums.ECookie;
import com.bnyte.azir.common.jwt.JWTHS256;
import com.bnyte.forge.aop.actuator.APIHelperActuator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author bnyte
 * @since 2022/5/28 3:00
 */
@Component
public class CookieUtils {
    // 默认缓存时间,单位/秒, 2H
    private static final int COOKIE_MAX_AGE = Integer.MAX_VALUE;
    // 保存路径,根路径
    private static final String COOKIE_PATH = "/";

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    /**
     * 保存
     *
     * @param key cookie的key
     * @param value 保存的value
     * @param ifRemember 是否记住账号
     */
    public void set(String key, String value, boolean ifRemember) {
        int age = ifRemember ? COOKIE_MAX_AGE : -1;
        set(key, value, null, COOKIE_PATH, age, false);
    }

    /**
     * 保存
     *
     * @param key cookie的key
     * @param value 保存的值
     * @param maxAge 保存时间
     * @param domain 保存的实例
     * @param isHttpOnly 是否仅http（防止模仿真实用户攻击）
     * @param path cookie保存的路径
     */
    private void set(String key, String value, String domain, String path, int maxAge, boolean isHttpOnly) {
        Cookie cookie = new Cookie(key, value);
        if (domain != null) {
            cookie.setDomain(domain);
        }
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(isHttpOnly);
        response.addCookie(cookie);
    }

    /**
     * 查询value
     *
     * @param key cookie的key
     * @return 返回保存的cookie值
     */
    public String getValue(String key) {
        Cookie cookie = get(key);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    /**
     * 查询Cookie
     *
     * @param key cookie的key
     * @return 返回保存的cookie对象
     */
    private Cookie get(String key) {
        Cookie[] arr_cookie = request.getCookies();
        if (arr_cookie != null && arr_cookie.length > 0) {
            for (Cookie cookie : arr_cookie) {
                if (cookie.getName().equals(key)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * 删除Cookie
     *
     * @param key cookie的key
     */
    public void remove(String key) {
        Cookie cookie = get(key);
        if (cookie != null) {
            set(key, "", null, COOKIE_PATH, 0, false);
        }
    }

    public User currentUser() {
        String token = getValue(ECookie.X_ACCESS_TOKEN.getKey());
        return JWTHS256.checkToken(token);
    }
}
