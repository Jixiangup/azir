package com.bnyte.azir.api.service.impl.console;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bnyte.azir.api.mapstruct.UserTransfer;
import com.bnyte.azir.api.service.console.TenantService;
import com.bnyte.azir.api.vo.user.CurrentUserVO;
import com.bnyte.azir.api.vo.user.UserVO;
import com.bnyte.azir.common.entity.console.Tenant;
import com.bnyte.azir.common.entity.console.User;
import com.bnyte.azir.common.enums.ECookie;
import com.bnyte.azir.common.enums.EUserStatus;
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
import java.io.Serializable;
import java.util.List;
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

    @Autowired
    CookieUtils cookieUtils;

    @Autowired
    TenantService tenantService;

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
        String token = cookieUtils.getValue(ECookie.X_ACCESS_TOKEN.getKey());
        if (StringUtils.hasText(token)) return Objects.nonNull(JWTHS256.checkToken(token));
        return false;
    }

    private void writeCookie(User authenticate, String token) {
        cookieUtils.set(ECookie.USERNAME.getKey(), authenticate.getUsername(), true);
        cookieUtils.set(ECookie.X_ACCESS_TOKEN.getKey(), token, true);
    }

    @Override
    public User authenticateWithPassword(String account, String password) {
        return getOne(
                Wrappers.lambdaQuery(User.class)
                        .eq(User::getAccount, account)
                        .eq(User::getPassword, password));
    }

    @Override
    public CurrentUserVO currentUser() {
        User userForToken = cookieUtils.currentUser();
        if (Objects.isNull(userForToken)) throw new RdosDefineException(Code.USER_NOT_FOUND);

        // 校验当前租户是否还存在
        String tenantId = cookieUtils.getValue(ECookie.TENANT_ID.getKey());
        Tenant tenant = tenantService.getById(tenantId);
        if (Objects.isNull(tenant)) {
            cookieUtils.remove(ECookie.TENANT_ID.getKey());
            cookieUtils.remove(ECookie.TENANT_NAME.getKey());
        }

        User user = getById(userForToken.getId());
        return UserTransfer.INSTANCE.toCurrentUserVO(user);
    }

    @Override
    public List<UserVO> users() {
        User currentUser = cookieUtils.currentUser();
        List<User> users = list(Wrappers.lambdaQuery(User.class).eq(!currentUser.getAdmin(), User::getParentUserId, currentUser.getId()));
        return UserTransfer.INSTANCE.toVOS(users);
    }

    @Override
    public UserVO info(Long id) {
        return UserTransfer.INSTANCE.toVO(getById(id));
    }

    @Override
    public void logout() {
        ECookie.logoutKeys().forEach(cookie -> cookieUtils.remove(cookie.getKey()));
    }

    @Override
    public void freeze(Long id) {
        User user = getById(id);
        if (Objects.isNull(user)) return;
        User currentUser = cookieUtils.currentUser();
        if (!currentUser.getAdmin() || !currentUser.getId().equals(user.getParentUserId())) throw new RdosDefineException(Code.PERMISSION_DENIED);
        if (user.getStatus().equals(EUserStatus.NORMAL.getKey())) return;
        user.setStatus(EUserStatus.FREEZE.getKey());
        updateById(user);
    }
}
