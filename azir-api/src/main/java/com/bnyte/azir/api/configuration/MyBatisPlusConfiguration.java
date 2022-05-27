package com.bnyte.azir.api.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.bnyte.azir.common.env.EnvContext;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author bnyte
 * @since 2022/5/28 3:50
 */
@Configuration
@MapperScan("com.bnyte.azir.dao.mapper")
public class MyBatisPlusConfiguration {

    @Autowired
    EnvContext envContext;

    @Primary
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(envContext.getMysqlUrl());
        dataSource.setDriverClassName(envContext.getMysqlDriverClassName());
        dataSource.setUsername(envContext.getMysqlUsername());
        dataSource.setPassword(envContext.getMysqlPassword());
        return dataSource;
    }

}
