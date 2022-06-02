package com.bnyte.azir.api.service.impl.console;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bnyte.azir.api.service.console.CompetenceService;
import com.bnyte.azir.common.dto.CompetenceDTO;
import com.bnyte.azir.common.entity.console.Competence;
import com.bnyte.azir.dao.mapper.CompetenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bnyte
 * @since 2022/6/2 14:32
 */
@Service
public class CompetenceServiceImpl extends ServiceImpl<CompetenceMapper, Competence> implements CompetenceService {

    @Autowired
    CompetenceMapper competenceMapper;

    @Override
    public CompetenceDTO queryCompetence(Long menuId, Long userId) {
        return competenceMapper.selectCompetence(menuId, userId);
    }
}
