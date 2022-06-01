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
}
