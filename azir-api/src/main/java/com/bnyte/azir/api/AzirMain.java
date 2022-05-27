package com.bnyte.azir.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author bnyte
 * @since 2022/5/26 14:59
 */
@EnableWebMvc
@SpringBootApplication
@ComponentScan("com.bnyte.azir")
public class AzirMain {
    public static void main(String[] args) {
        // pre path setting
        prePathSetting();

        SpringApplication.run(AzirMain.class, args);
    }

    private static void prePathSetting() {
    }
}
