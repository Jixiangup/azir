package com.bnyte.azir.controller.console;

import com.bnyte.azir.service.console.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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

    @ApiOperation("登录")
    @PostMapping("/login")
    String login() {
        return "";
    }

}
