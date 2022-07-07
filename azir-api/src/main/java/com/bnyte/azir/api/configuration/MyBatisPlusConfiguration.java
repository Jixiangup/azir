package com.bnyte.azir.api.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.bnyte.azir.common.env.EnvContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.File;
import java.net.URI;
import java.util.Objects;

/**
 * @author bnyte
 * @since 1.0.0
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

    @Primary
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage("com.bnyte.azir.common.entity");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resourceDash = resolver.getResources("classpath*:mapper/**/*.xml");
        sqlSessionFactoryBean.setMapperLocations(resourceDash);
        Resource resource = resolver.getResource("classpath:mybatis-config.xml");
        sqlSessionFactoryBean.setConfigLocation(resource);
        Objects.requireNonNull(sqlSessionFactoryBean.getObject()).getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();
    }

}
