package com.bnyte.azir.api.mapstruct;

import com.bnyte.azir.api.vo.menu.MenuVO;
import com.bnyte.azir.common.entity.console.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author bnyte
 * @since 1.0.0
 */
@Mapper
public interface MenuTransfer {

    MenuTransfer INSTANCE = Mappers.getMapper(MenuTransfer.class);

    MenuVO toVO(Menu menu);

    List<MenuVO> toVOS(List<Menu> menus);

    Menu domain(MenuVO menu);


}
