package com.bnyte.azir.api.service.impl.console;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bnyte.azir.api.constant.ConfigConstant;
import com.bnyte.azir.api.mapstruct.MenuTransfer;
import com.bnyte.azir.api.service.console.CompetenceService;
import com.bnyte.azir.api.service.console.MenuService;
import com.bnyte.azir.api.vo.menu.MenuVO;
import com.bnyte.azir.common.dto.CompetenceDTO;
import com.bnyte.azir.common.entity.console.Menu;
import com.bnyte.azir.common.exception.RdosDefineException;
import com.bnyte.azir.common.util.CookieUtils;
import com.bnyte.azir.common.web.response.Code;
import com.bnyte.azir.dao.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Autowired
    CompetenceService competenceService;

    @Override
    public List<MenuVO> menus() {
        List<Menu> menus = menuMapper.selectMenus(cookieUtils.currentUser().getId());

        List<MenuVO> menuVOS = MenuTransfer.INSTANCE.toVOS(menus);
        // 获取所有一级目录
        return menuVOS.stream()
            .filter(menuVO -> menuVO.getParentId().equals(ConfigConstant.menuRootId))
            .peek(menuVO -> menuVO.setChildren(recursivelyQueryChildren(menuVO, menuVOS)))
            .collect(Collectors.toList());
    }

    @Override
    public Boolean allowAccess(String path) {
        Menu menu = menuMapper.selectAccess(path, cookieUtils.currentUser().getId());
        return Objects.nonNull(menu);
    }

    @Override
    public Boolean delete(Long id) {
        CompetenceDTO competenceDTO = competenceService.queryCompetence(id, cookieUtils.currentUser().getId());
        if (Objects.isNull(competenceDTO)) throw new RdosDefineException(Code.PERMISSION_DENIED);

        // 删除角色权限字典关联

        // 删除权限
        return true;
    }

    /**
     * 通过父节点id从所有节点中获取当前父节点的所有子节点
     * @param root 根节点
     * @param all 所有节点
     * @return 返回该根节点的所有子节点
     */
    private List<MenuVO> recursivelyQueryChildren(MenuVO root, List<MenuVO> all) {
        return all.stream()
                .filter(menuVO -> menuVO.getParentId().equals(root.getId()))
                .peek(menuVO -> recursivelyQueryChildren(menuVO, all))
                .collect(Collectors.toList());
    }
}
