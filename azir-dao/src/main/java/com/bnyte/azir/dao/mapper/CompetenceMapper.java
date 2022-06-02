package com.bnyte.azir.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bnyte.azir.common.dto.CompetenceDTO;
import com.bnyte.azir.common.entity.console.Competence;
import org.apache.ibatis.annotations.Param;

/**
 * @author bnyte
 * @since 2022/6/2 14:50
 */
public interface CompetenceMapper extends BaseMapper<Competence> {
    CompetenceDTO selectCompetence(@Param("menuId") Long menuId, @Param("userId") Long userId);
}
