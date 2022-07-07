package com.bnyte.azir.api.service.impl.console;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bnyte.azir.api.service.console.CompetenceService;
import com.bnyte.azir.common.dto.CompetenceDTO;
import com.bnyte.azir.common.entity.console.Competence;
import com.bnyte.azir.dao.mapper.CompetenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bnyte
 * @since 1.0.0
 */
@Service
public class CompetenceServiceImpl extends ServiceImpl<CompetenceMapper, Competence> implements CompetenceService {

    @Autowired
    CompetenceMapper competenceMapper;

    @Override
    public CompetenceDTO queryCompetence(Long menuId, Long userId) {
        return competenceMapper.selectCompetence(menuId, userId);
    }

    @Override
    public List<Competence> queryCompetence(Long menuId) {
        return list(Wrappers.lambdaQuery(Competence.class).eq(Competence::getMenuId, menuId));
    }
}
