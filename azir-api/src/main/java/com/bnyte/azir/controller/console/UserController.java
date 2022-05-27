package com.bnyte.azir.controller.console;

import com.bnyte.azir.common.web.response.R;
import com.bnyte.azir.service.console.UserService;
import com.bnyte.azir.vo.user.LoginVO;
import com.bnyte.forge.annotation.APIHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bnyte
 * @since 2022/5/26 18:29
 */
@RequestMapping("/user")
@RestController
@Api("用户控制器")
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

}
