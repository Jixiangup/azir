package com.bnyte.azir.api.service.impl.console;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bnyte.azir.api.service.console.RoleService;
import com.bnyte.azir.common.entity.console.Role;
import com.bnyte.azir.dao.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
 * @author bnyte
 * @since 2022/6/4 21:35
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
