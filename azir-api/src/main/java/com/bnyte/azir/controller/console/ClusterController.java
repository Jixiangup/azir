package com.bnyte.azir.controller.console;

import com.bnyte.azir.service.console.ClusterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bnyte
 * @since 2022/5/26 17:46
 */
@RestController
@RequestMapping("/cluster")
@Api("集群控制器")
public class ClusterController {

    @Autowired
    ClusterService clusterService;


}
