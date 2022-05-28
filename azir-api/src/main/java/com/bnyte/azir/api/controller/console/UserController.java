package com.bnyte.azir.api.controller.console;

import com.bnyte.azir.api.vo.user.CurrentUserVO;
import com.bnyte.azir.common.web.response.R;
import com.bnyte.azir.api.vo.user.LoginVO;
import com.bnyte.azir.api.service.console.UserService;
import com.bnyte.forge.annotation.APIHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author bnyte
 * @since 2022/5/26 18:29
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

}
