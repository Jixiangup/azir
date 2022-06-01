package com.bnyte.azir.api.service.impl.console;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bnyte.azir.api.mapstruct.MenuTransfer;
import com.bnyte.azir.api.service.console.MenuService;
import com.bnyte.azir.api.vo.menu.MenuVO;
import com.bnyte.azir.common.entity.console.Menu;
import com.bnyte.azir.common.util.CookieUtils;
import com.bnyte.azir.dao.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bnyte
 * @since 2022/5/31 13:13
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    CookieUtils cookieUtils;

    @Override
    public List<MenuVO> menus() {
        List<Menu> menus = menuMapper.selectMenus(cookieUtils.currentUser().getId());
        return MenuTransfer.INSTANCE.toVOS(menus);
    }
}
