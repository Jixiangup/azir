package com.bnyte.azir.api.service.console;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bnyte.azir.api.vo.user.CurrentUserVO;
import com.bnyte.azir.api.vo.user.LoginVO;
import com.bnyte.azir.api.vo.user.UserVO;
import com.bnyte.azir.common.entity.console.User;

import java.util.List;

/**
 * @author bnyte
 * @since 1.0.0
 */
public interface UserService extends IService<User> {

    void login(LoginVO loginVO);

    User authenticateWithPassword(String account, String password);

    CurrentUserVO currentUser();

    List<UserVO> users();

    UserVO info(Long id);

    void logout();

    void freeze(Long id);

    Boolean updateAdmin(Long id);

    void createdSubUser(UserVO vo);
}
