package com.bnyte.azir.api.controller.console;

import com.bnyte.azir.api.service.console.MenuService;
import com.bnyte.azir.api.vo.menu.MenuVO;
import com.bnyte.azir.common.param.menu.MenuSearch;
import com.bnyte.azir.common.web.response.R;
import com.bnyte.forge.annotation.APIHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author bnyte
 * @since 2022/5/31 13:09
 */
@Api(tags = "菜单路由控制器")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @APIHelper
    @GetMapping("/list")
    @ApiOperation("菜单路由列表")
    R<List<MenuVO>> list(MenuSearch menuSearch) {
        return R.ok(menuService.menus(menuSearch));
    }

    @APIHelper
    @GetMapping("/allow_access")
    @ApiOperation("是否允许访问当前路由")
    R<Boolean> allowAccess(@RequestParam("path") String path) {
        return R.ok(menuService.allowAccess(path));
    }

    @APIHelper
    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除菜单路由")
    R<Boolean> delete(@PathVariable("id") Long id) {
        return R.ok(menuService.delete(id));
    }

    @APIHelper
    @PutMapping("/update")
    @ApiOperation("更新菜单路由")
    R<Boolean> update(@RequestBody @Validated MenuVO menuVO) {
        return R.ok(menuService.updated(menuVO));
    }

    @APIHelper
    @PostMapping("/created")
    @ApiOperation("创建菜单路由")
    R<Long> created(@RequestBody @Validated MenuVO menuVO) {
        return R.ok(menuService.created(menuVO));
    }

    @APIHelper
    @GetMapping("/info/{id}")
    @ApiOperation("获取路由信息")
    R<MenuVO> info(@PathVariable("id") Long id) {
        return R.ok(menuService.info(id));
    }

}
