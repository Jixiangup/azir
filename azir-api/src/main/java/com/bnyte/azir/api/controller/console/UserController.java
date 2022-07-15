package com.bnyte.azir.api.controller.console;

import com.bnyte.azir.api.vo.user.CurrentUserVO;
import com.bnyte.azir.api.vo.user.UserVO;
import com.bnyte.azir.common.web.response.R;
import com.bnyte.azir.api.vo.user.LoginVO;
import com.bnyte.azir.api.service.console.UserService;
import com.bnyte.forge.annotation.APIHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author bnyte
 * @since 1.0.0
 */
@RequestMapping("/user")
@RestController
@Api(tags = "用户控制器")
public class UserController {

    @Autowired
    UserService userService;

    @APIHelper
    @ApiOperation("登录")
    @PostMapping("/login")
    R<Void> login(@RequestBody @Validated LoginVO loginVO) {
        userService.login(loginVO);
        return R.empty();
    }

    @APIHelper
    @ApiOperation("当前用户信息")
    @GetMapping("/current_user")
    R<CurrentUserVO> currentUser() {
        return R.ok(userService.currentUser());
    }

    @APIHelper
    @ApiOperation("用户列表")
    @GetMapping("/list")
    R<List<UserVO>> users() {
        return R.ok(userService.users());
    }

    @APIHelper
    @ApiOperation("用户详情")
    @GetMapping("/info/{id}")
    R<UserVO> info(@PathVariable("id") Long id) {
        return R.ok(userService.info(id));
    }

    @APIHelper
    @DeleteMapping("/logout")
    @ApiOperation("登出")
    R<Void> logout() {
        userService.logout();
        return R.empty();
    }

    @APIHelper
    @DeleteMapping("/freeze/{id}")
    @ApiOperation("冻结用户")
    R<Void> freeze(@PathVariable("id") Long id) {
        userService.freeze(id);
        return R.empty();
    }

    @APIHelper
    @PutMapping("/update_admin/{id}")
    @ApiOperation("更新是否为admin")
    R<Boolean> updateAdmin(@PathVariable("id") Long id) {
        return R.ok(userService.updateAdmin(id));
    }

    @APIHelper
    @PostMapping("/created_sub_user")
    @ApiOperation("创建子用户")
    R<Void> createdSubUser(@RequestBody @Validated UserVO vo) {
        userService.createdSubUser(vo);
        return R.empty();
    }


}
