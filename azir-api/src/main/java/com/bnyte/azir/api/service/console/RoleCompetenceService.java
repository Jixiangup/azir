package com.bnyte.azir.api.service.console;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bnyte.azir.common.entity.console.RoleCompetence;

import java.util.List;

/**
 * @author bnyte
 * @since 2022/6/4 21:34
 */
public interface RoleCompetenceService extends IService<RoleCompetence> {
    Boolean removeRoleCompetence(Long roleId, Long competenceId);

    Boolean removeForCompetenceIds(List<Long> competenceIds);
}
