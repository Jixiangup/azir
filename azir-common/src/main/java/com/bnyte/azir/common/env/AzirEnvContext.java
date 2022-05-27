package com.bnyte.azir.common.env;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author bnyte
 * @since 2022/5/27 18:55
 */
@Configuration
@PropertySource(value = "file:${user.dir}/conf/application.yaml")
public class AzirEnvContext {

    @Autowired
    Environment environment;

    /**
     * MySQL数据源驱动
     */
    @Value("${datasource.mysql.driver-class-name:com.mysql.cj.jdbc.Driver}")
    private String jdbcDriverClassName;

    /**
     * MySQL数据源驱动
     */
    @Value("${datasource.mysql.url:127.0.0.1}")
    private String mysqlUrl;

    /**
     * MySQL数据源驱动
     */
    @Value("${datasource.mysql.username:root}")
    private String mysqlUsername;

    /**
     * MySQL用户
     */
    @Value("${datasource.mysql.password:root}")
    private String mysqlPassword;

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getJdbcDriverClassName() {
        return jdbcDriverClassName;
    }

    public void setJdbcDriverClassName(String jdbcDriverClassName) {
        this.jdbcDriverClassName = jdbcDriverClassName;
    }

    public String getMysqlUrl() {
        return mysqlUrl;
    }

    public void setMysqlUrl(String mysqlUrl) {
        this.mysqlUrl = mysqlUrl;
    }

    public String getMysqlUsername() {
        return mysqlUsername;
    }

    public void setMysqlUsername(String mysqlUsername) {
        this.mysqlUsername = mysqlUsername;
    }

    public String getMysqlPassword() {
        return mysqlPassword;
    }

    public void setMysqlPassword(String mysqlPassword) {
        this.mysqlPassword = mysqlPassword;
    }
}
