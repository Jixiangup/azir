package com.bnyte.azir.service.impl.console;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bnyte.azir.common.entity.console.User;
import com.bnyte.azir.mapper.UserMapper;
import com.bnyte.azir.service.console.UserService;
import org.springframework.stereotype.Service;

/**
 * @author bnyte
 * @since 2022/5/26 18:31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
