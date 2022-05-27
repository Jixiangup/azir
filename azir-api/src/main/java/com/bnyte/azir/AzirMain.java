package com.bnyte.azir;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author bnyte
 * @since 2022/5/26 14:59
 */
@EnableWebMvc
@SpringBootApplication
@MapperScan("com.bnyte.azir.mapper")
public class AzirMain {
    public static void main(String[] args) {
        SpringApplication.run(AzirMain.class, args);
    }
}
