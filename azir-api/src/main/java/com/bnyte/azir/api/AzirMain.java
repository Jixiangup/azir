package com.bnyte.azir.api;

import com.bnyte.azir.api.util.SystemUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.SpringVersion;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author bnyte
 * @since 1.0.0
 */
@EnableWebMvc
@SpringBootApplication
@ComponentScan("com.bnyte.azir")
public class AzirMain {

    private static final Logger log = LoggerFactory.getLogger(AzirMain.class);

    public static void main(String[] args) {
        // pre settings
        preSettings();

        // starter application
        ConfigurableApplicationContext context = new SpringApplicationBuilder(AzirMain.class)
                .main(SpringVersion.class)
                .bannerMode(Banner.Mode.CONSOLE)
                .run(args);

        // postprocessing
        postProcessing(context);
    }

    private static void preSettings() {
        prePathSetting();
    }

    /**
     * post-processing for application
     */
    private static void postProcessing(ApplicationContext context) {
        // logging swagger web address
        SystemUtil systemUtil = context.getBean(SystemUtil.class);
        String swaggerWebAddress = systemUtil.swaggerWebAddress();
        log.info("swagger started successfully and website address for: {}", swaggerWebAddress);

    }

    private static void prePathSetting() {
    }
}
