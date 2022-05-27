package com.bnyte.azir.common.jwt;

import com.bnyte.azir.common.entity.console.User;
import com.bnyte.azir.common.exception.RdosDefineException;
import com.bnyte.azir.common.util.JacksonUtils;
import com.bnyte.azir.common.web.response.Code;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * @author bnyte
 * @since 2020-01-13 23:12
 **/
public class JWTHS256 {

//    private static final Logger log = LoggerFactory.getLogger(JWTHS256.class);

    /**
     * 创建秘钥
     */
    private static final byte[] SECRET = Arrays.copyOf("EY0h0j0mHoyxGoigoYgn".getBytes(StandardCharsets.UTF_8), 256);

    /**
     * 过期时间三十分钟
     */
    private static final long EXPIRE_TIME = 1000 * 60 * 30;


    /**
     * 生成Token
     * @param user 登录成功的用户信息
     * @return token
     */
    public static String buildToken(User user) {
        try {
            /*
              1.创建一个32-byte的密匙
             */
            MACSigner macSigner = new MACSigner(SECRET);
            /*
              2. 建立payload 载体
             */
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject("bnyte-token")
                    .issuer("https://azir.bnyte.com")
                    .expirationTime(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                    .claim("user", JacksonUtils.toJSONString(user))
                    .build();

            /*
              3. 建立签名
             */
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(macSigner);

            /*
              4. 生成token
             */
            return signedJWT.serialize();
        } catch (JOSEException e) {
//            log.error("[{}] build token error for {}", JWTHS256.class.getSimpleName(), e.getMessage(), e);
            throw new RdosDefineException(Code.FAIL);
        }
    }


    /**
     * 校验token
     * @param token 需要校验的token
     * @return 返回用户信息
     */
    public static User checkToken(String token) {
        try {
            SignedJWT jwt = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SECRET);
            //校验是否有效
            if (!jwt.verify(verifier)) {
                throw new RdosDefineException(Code.AUTHENTICATION_ERROR);
            }

            //校验超时
            Date expirationTime = jwt.getJWTClaimsSet().getExpirationTime();
            if (new Date().after(expirationTime)) {
                throw new RdosDefineException(Code.TOKEN_EXPIRED_ERROR);
            }

            //获取载体中的数据
            Object user = jwt.getJWTClaimsSet().getClaim("user");
            //是否有openUid
            if (Objects.isNull(user)){
                throw new RdosDefineException(Code.THE_USER_DOES_NOT_EXIST_ERROR);
            }
            return JacksonUtils.parse(String.valueOf(user), User.class);
        } catch (ParseException | JOSEException e) {
            throw new RdosDefineException(Code.FAIL);
        }
    }


}