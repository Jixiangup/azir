package com.bnyte.azir.api.service.impl.console;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bnyte.azir.api.constant.ConfigConstant;
import com.bnyte.azir.api.constant.MessageConstant;
import com.bnyte.azir.api.mapstruct.MenuTransfer;
import com.bnyte.azir.api.service.console.CompetenceService;
import com.bnyte.azir.api.service.console.MenuService;
import com.bnyte.azir.api.service.console.RoleCompetenceService;
import com.bnyte.azir.api.service.console.UserService;
import com.bnyte.azir.api.util.CookieUtils;
import com.bnyte.azir.api.vo.menu.MenuVO;
import com.bnyte.azir.api.vo.user.CurrentUserVO;
import com.bnyte.azir.common.dto.CompetenceDTO;
import com.bnyte.azir.common.entity.console.Competence;
import com.bnyte.azir.common.entity.console.Menu;
import com.bnyte.azir.common.exception.RdosDefineException;
import com.bnyte.azir.common.param.menu.MenuSearch;
import com.bnyte.azir.common.web.response.Code;
import com.bnyte.azir.dao.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author bnyte
 * @since 1.0.0
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    CookieUtils cookieUtils;

    @Autowired
    CompetenceService competenceService;

    @Autowired
    RoleCompetenceService roleCompetenceService;

    @Autowired
    UserService userService;

    @Override
    public List<MenuVO> menus(MenuSearch menuSearch) {

        List<Menu> menus = userService.currentUser().getAdmin() ?
                list(Wrappers.lambdaQuery(Menu.class)
                        .like(Objects.nonNull(menuSearch.getIcon()), Menu::getIcon, menuSearch.getIcon())
                        .like(Objects.nonNull(menuSearch.getName()), Menu::getName, menuSearch.getName())
                        .like(Objects.nonNull(menuSearch.getPath()), Menu::getPath, menuSearch.getPath())
                        .orderByAsc(Menu::getWeights)) :
                menuMapper.selectMenus(cookieUtils.currentUser().getId(), menuSearch);

        // 处理搜索之后的menu
        List<Menu> result = new ArrayList<>();
        if (menuSearch.enableSearch()) handlerMenusParent(menus, result);

        List<MenuVO> menuVOS = MenuTransfer.INSTANCE.toVOS(menuSearch.enableSearch() ? result : menus);
        // 获取所有一级目录
        return menuVOS.stream()
            .filter(menuVO -> menuVO.getParentId().equals(ConfigConstant.MENU_ROOT_ID))
            .peek(menuVO -> menuVO.setChildren(recursivelyQueryChildren(menuVO, menuVOS)))
            .collect(Collectors.toList());
    }

    private void handlerMenusParent(List<Menu> menus, List<Menu> result) {
        for (Menu menu : menus) {
            result.add(menu);
            if (menu.getParentId().equals(ConfigConstant.MENU_ROOT_ID)) continue;
            List<Menu> parentMenu = list(Wrappers.lambdaQuery(Menu.class).eq(Menu::getId, menu.getParentId()));
            handlerMenusParent(parentMenu, result);
        }
    }

    @Override
    public Boolean allowAccess(String path) {

        Long userId = cookieUtils.currentUser().getId();
        CurrentUserVO currentUser = userService.currentUser();
        if (currentUser.getAdmin()) return true;

        Menu menu = menuMapper.selectAccess(path, userId);
        return Objects.nonNull(menu);
    }

    @Override
    @Transactional
    public Boolean delete(Long id) {
        Long userId = cookieUtils.currentUser().getId();
        CurrentUserVO currentUser = userService.currentUser();
        // 管理员无需获取权限
        if (currentUser.getAdmin()) {
            List<Competence> competences = competenceService.queryCompetence(id);
            List<Long> competenceIds = competences.stream().map(Competence::getId).collect(Collectors.toList());
            competenceService.removeByIds(competenceIds);
            roleCompetenceService.removeForCompetenceIds(competenceIds);
            return removeById(id);
        }
        CompetenceDTO competenceDTO = competenceService.queryCompetence(id, userId);
        if (Objects.isNull(competenceDTO)) throw new RdosDefineException(Code.PERMISSION_DENIED);

        Assert.isTrue(competenceDTO.getRemove(), MessageConstant.PERMISSION_DENIED);

        // 删除角色权限字典关联
        competenceService.removeById(competenceDTO.getId());

        roleCompetenceService.removeRoleCompetence(competenceDTO.getRoleId(), competenceDTO.getId());

        // 删除路由
        return removeById(competenceDTO.getId());
    }

    @Override
    public Boolean updated(MenuVO menuVO) {
        Menu forName = queryForName(menuVO.getName());
        if (Objects.nonNull(forName)) Assert.isTrue(forName.getId().equals(menuVO.getId()), MessageConstant.MENU_NAME_EXISTS);
        Menu forPath = queryForPath(menuVO.getPath());
        if (Objects.nonNull(forPath)) Assert.isTrue(forPath.getId().equals(menuVO.getId()), MessageConstant.MENU_PATH_EXISTS);

        Menu menuDb = getById(menuVO.getId());
        Menu menu = MenuTransfer.INSTANCE.domain(menuVO);
        menu.setId(menuDb.getId());

        return updateById(menu);
    }

    @Override
    public Menu queryForName(String name) {
        return getOne(Wrappers.lambdaQuery(Menu.class).eq(Menu::getName, name));
    }

    @Override
    public Menu queryForPath(String path) {
        return getOne(Wrappers.lambdaQuery(Menu.class).eq(Menu::getPath , path));
    }

    @Override
    public Long created(MenuVO menuVO) {
        Assert.isNull(queryForPath(menuVO.getPath()), MessageConstant.MENU_PATH_EXISTS);
        Assert.isNull(queryForName(menuVO.getName()), MessageConstant.MENU_NAME_EXISTS);
        Menu domain = MenuTransfer.INSTANCE.domain(menuVO);
        save(domain);
        return domain.getId();
    }

    @Override
    public MenuVO info(Long id) {
        Menu menu = getById(id);
        return MenuTransfer.INSTANCE.toVO(menu);
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
                .peek(menuVO -> menuVO.setChildren(recursivelyQueryChildren(menuVO, all)))
                .collect(Collectors.toList());
    }
}
