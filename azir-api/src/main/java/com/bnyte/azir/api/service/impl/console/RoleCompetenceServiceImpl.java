package com.bnyte.azir.api.service.impl.console;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bnyte.azir.api.service.console.RoleCompetenceService;
import com.bnyte.azir.common.entity.console.RoleCompetence;
import com.bnyte.azir.dao.mapper.RoleCompetenceMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bnyte
 * @since 1.0.0
 */
@Service
public class RoleCompetenceServiceImpl extends ServiceImpl<RoleCompetenceMapper, RoleCompetence> implements RoleCompetenceService {
    @Override
    public Boolean removeRoleCompetence(Long roleId, Long competenceId) {
        return remove(Wrappers.lambdaQuery(RoleCompetence.class).eq(RoleCompetence::getCompetenceId, competenceId).eq(RoleCompetence::getRoleId, roleId));
    }

    @Override
    public Boolean removeForCompetenceIds(List<Long> competenceIds) {
        if (CollectionUtils.isEmpty(competenceIds)) return true;
        return remove(Wrappers.lambdaQuery(RoleCompetence.class).in(RoleCompetence::getCompetenceId, competenceIds));
    }
}
