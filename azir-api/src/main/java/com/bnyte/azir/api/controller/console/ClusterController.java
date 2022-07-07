package com.bnyte.azir.api.controller.console;

import com.bnyte.azir.api.service.console.ClusterService;
import com.bnyte.azir.api.vo.cluster.ClusterVO;
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
 * @since 1.0.0
 */
@RestController
@RequestMapping("/cluster")
@Api(tags = "集群控制器")
public class ClusterController {

    @Autowired
    ClusterService clusterService;

    @APIHelper
    @ApiOperation("创建集群")
    @PostMapping("/create")
    R<Void> create(@RequestBody @Validated ClusterVO clusterVO) {
        clusterService.create(clusterVO);
        return R.empty();
    }

    @APIHelper
    @ApiOperation("集群列表")
    @GetMapping("/list")
    R<List<ClusterVO>> list() {
        return R.ok(clusterService.vos());
    }

    @APIHelper
    @ApiOperation("删除集群")
    @DeleteMapping("/delete/{id}")
    R<Void> delete(@PathVariable("id") Long id) {
        clusterService.deleteById(id);
        return R.empty();
    }

    @APIHelper
    @ApiOperation("更新集群")
    @PutMapping("/update")
    R<Void> update(@RequestBody ClusterVO clusterVO) {
        clusterService.updateCluster(clusterVO);
        return R.empty();
    }

}
