package com.bnyte.azir.api.mapstruct;

import com.bnyte.azir.api.vo.menu.MenuVO;
import com.bnyte.azir.common.entity.console.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author bnyte
 * @since 1.0.0
 */
@Mapper
public interface MenuTransfer {

    MenuTransfer INSTANCE = Mappers.getMapper(MenuTransfer.class);

    @Mappings({
        @Mapping(target = "children", ignore = true)
    })
    MenuVO toVO(Menu menu);

    List<MenuVO> toVOS(List<Menu> menus);

    @Mappings({
            @Mapping(target = "gmtModified", ignore = true),
            @Mapping(target = "deleted", ignore = true)
    })
    Menu domain(MenuVO menu);


}
