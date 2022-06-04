package com.bnyte.azir.api.service.console;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bnyte.azir.api.vo.menu.MenuVO;
import com.bnyte.azir.common.entity.console.Menu;
import com.bnyte.azir.common.web.response.R;

import java.util.List;

/**
 * @author bnyte
 * @since 2022/5/31 13:11
 */
public interface MenuService extends IService<Menu> {
    List<MenuVO> menus();

    Boolean allowAccess(String path);

    Boolean delete(Long id);

    Boolean updated(MenuVO menuVO);

    Menu queryForName(String name);

    Menu queryForPath(String path);

    Long created(MenuVO menuVO);

    MenuVO info(Long id);
}
