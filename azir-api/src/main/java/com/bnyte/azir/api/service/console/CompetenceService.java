package com.bnyte.azir.api.service.console;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bnyte.azir.common.dto.CompetenceDTO;
import com.bnyte.azir.common.entity.console.Competence;

import java.util.List;

/**
 * @author bnyte
 * @since 1.0.0
 */
public interface CompetenceService extends IService<Competence> {
    CompetenceDTO queryCompetence(Long menuId, Long userId);

    List<Competence> queryCompetence(Long menuId);
}
