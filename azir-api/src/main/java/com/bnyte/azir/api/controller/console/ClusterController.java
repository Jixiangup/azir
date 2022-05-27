package com.bnyte.azir.api.controller.console;

import com.bnyte.azir.api.service.console.ClusterService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
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
