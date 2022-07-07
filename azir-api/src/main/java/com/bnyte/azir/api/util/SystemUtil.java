package com.bnyte.azir.api.util;

import com.bnyte.azir.common.env.EnvContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static com.bnyte.azir.api.constant.ConfigConstant.DEFAULT_LOCAL_ADDRESS;
import static com.bnyte.azir.api.constant.ConfigConstant.SWAGGER_PATH;

/**
 * @author bnyte
 * @since 1.0.0
 */
@Component
public class SystemUtil {

    private static final Logger log = LoggerFactory.getLogger(SystemUtil.class);

    @Autowired
    EnvContext envContext;

    public String swaggerWebAddress() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            if (log.isWarnEnabled()) log.warn("find local inet address error");
            return "http://" + DEFAULT_LOCAL_ADDRESS + ":" + envContext.getServerPort() + envContext.getContextPath() + SWAGGER_PATH;
        }
        String host = address.getHostAddress();
        return "http://" +host + ":" + envContext.getServerPort() + envContext.getContextPath() + SWAGGER_PATH;
    }

}
