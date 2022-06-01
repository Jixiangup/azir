package com.bnyte.azir.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bnyte.azir.common.entity.console.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author bnyte
 * @since 2022/5/31 13:15
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> selectMenus(@Param("userId") Long id);

    Menu selectAccess(@Param("path") String path, @Param("userId") Long id);
}
