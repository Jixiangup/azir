package com.bnyte.azir.api.service.console;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bnyte.azir.common.dto.CompetenceDTO;
import com.bnyte.azir.common.entity.console.Competence;

/**
 * @author bnyte
 * @since 2022/6/2 14:31
 */
public interface CompetenceService extends IService<Competence> {
    CompetenceDTO queryCompetence(Long menuId, Long userId);
}
