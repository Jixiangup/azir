package com.bnyte.azir.api.service.impl.console;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bnyte.azir.common.entity.console.User;
import com.bnyte.azir.common.enums.ECookie;
import com.bnyte.azir.common.exception.RdosDefineException;
import com.bnyte.azir.common.jwt.JWTHS256;
import com.bnyte.azir.common.util.CookieUtils;
import com.bnyte.azir.common.web.response.Code;
import com.bnyte.azir.dao.mapper.UserMapper;
import com.bnyte.azir.api.service.console.UserService;
import com.bnyte.azir.api.vo.user.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author bnyte
 * @since 2022/5/26 18:31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    HttpServletResponse response;

    @Autowired
    HttpServletRequest request;

    @Override
    public void login(LoginVO loginVO) {
        // 前置token校验
        if (isLogin()) {
            return;
        }

        // 密码校验
        User authenticate = authenticateWithPassword(loginVO.getAccount(), loginVO.getPassword());
        if (Objects.isNull(authenticate)) {
            throw new RdosDefineException(Code.AUTHENTICATION_ERROR);
        }

        // 生成token
        String token = JWTHS256.buildToken(authenticate);

        // 写cookie
        writeCookie(authenticate, token);
    }

    private boolean isLogin() {
        String token = CookieUtils.getValue(request, ECookie.X_ACCESS_TOKEN.getKey());
        if (StringUtils.hasText(token)) return Objects.nonNull(JWTHS256.checkToken(token));
        return false;
    }

    private void writeCookie(User authenticate, String token) {
        CookieUtils.set(response, ECookie.USERNAME.getKey(), authenticate.getUsername(), true);
        CookieUtils.set(response, ECookie.X_ACCESS_TOKEN.getKey(), token, true);
    }

    @Override
    public User authenticateWithPassword(String account, String password) {
        return getOne(
                Wrappers.lambdaQuery(User.class)
                        .eq(User::getAccount, account)
                        .eq(User::getPassword, password)
                        .eq(User::getDeleted, false));
    }


}
