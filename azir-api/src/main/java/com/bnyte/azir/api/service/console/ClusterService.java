package com.bnyte.azir.api.service.console;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bnyte.azir.api.vo.cluster.ClusterVO;
import com.bnyte.azir.common.entity.console.Cluster;

import java.util.List;

/**
 * @author bnyte
 * @since 2022/5/26 17:47
 */
public interface ClusterService extends IService<Cluster> {
    void create(ClusterVO clusterVO);

    List<ClusterVO> listByTenant();
}
