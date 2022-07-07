package com.bnyte.azir.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bnyte.azir.common.entity.console.Menu;
import com.bnyte.azir.common.param.menu.MenuSearch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author bnyte
 * @since 1.0.0
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> selectMenus(@Param("userId") Long id, @Param("search") MenuSearch search);

    Menu selectAccess(@Param("path") String path, @Param("userId") Long id);
}
