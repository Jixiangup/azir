package com.bnyte.azir.common.env;

import com.bnyte.azir.common.analyze.YAMLPropertySourceAnalyze;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author bnyte
 * @since 2022/5/27 18:55
 */
@Configuration
@PropertySource(
        value = "file:${user.dir}/conf/application.yaml",
        encoding = "UTF-8",
        factory = YAMLPropertySourceAnalyze.class
)
public class EnvContext implements InitializingBean {

    @Autowired
    Environment environment;

    @Autowired
    ServerProperties serverProperties;

    /**
     * MySQL数据源驱动
     */
    @Value("${datasource.mysql.driver-class-name:com.mysql.cj.jdbc.Driver}")
    private String mysqlDriverClassName;

    /**
     * MySQL数据源驱动
     */
    @Value("${datasource.mysql.url:jdbc:mysql://127.0.0.1/azir}")
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

    /**
     * 服务端口
     */
    @Value("${server.port:8080}")
    private Integer serverPort;

    /**
     * 服务全局请求地址
     */
    @Value("${server.servlet.context-path:azir}")
    private String contextPath;

    @Override
    public void afterPropertiesSet() throws Exception {
        // setting server info
        customizeServerProperties();
    }

    private void customizeServerProperties() {
        serverProperties.setPort(serverPort);
        ServerProperties.Servlet servlet = serverProperties.getServlet();
        servlet.setContextPath(contextPath);
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getMysqlDriverClassName() {
        return mysqlDriverClassName;
    }

    public void setMysqlDriverClassName(String mysqlDriverClassName) {
        this.mysqlDriverClassName = mysqlDriverClassName;
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

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }


}
