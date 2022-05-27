package com.bnyte.azir.service.impl.console;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bnyte.azir.common.entity.console.User;
import com.bnyte.azir.common.web.response.Code;
import com.bnyte.azir.exception.RdosDefineException;
import com.bnyte.azir.mapper.UserMapper;
import com.bnyte.azir.service.console.UserService;
import com.bnyte.azir.vo.user.LoginVO;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author bnyte
 * @since 2022/5/26 18:31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void login(LoginVO loginVO) {
        User authenticate = authenticateWithPassword(loginVO.getAccount(), loginVO.getPassword());
        if (Objects.isNull(authenticate)) {
            throw new RdosDefineException(Code.AUTHENTICATION_ERROR);
        }
        // TODO: 2022/5/27 写入token
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
