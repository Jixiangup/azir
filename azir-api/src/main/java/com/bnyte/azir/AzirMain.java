package com.bnyte.azir;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.File;

/**
 * @author bnyte
 * @since 2022/5/26 14:59
 */
@EnableWebMvc
@SpringBootApplication
@MapperScan("com.bnyte.azir.mapper")
public class AzirMain {
    public static void main(String[] args) {
        // pre path setting
        prePathSetting();

        SpringApplication.run(AzirMain.class, args);
    }

    private static void prePathSetting() {
    }
}
