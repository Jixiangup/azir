package com.bnyte.azir.service.console;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bnyte.azir.common.entity.console.User;
import com.bnyte.azir.vo.user.LoginVO;

/**
 * @author bnyte
 * @since 2022/5/26 18:30
 */
public interface UserService extends IService<User> {

    void login(LoginVO loginVO);

    User authenticateWithPassword(String account, String password);

}
