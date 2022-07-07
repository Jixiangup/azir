package com.bnyte.azir.api.service.console;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bnyte.azir.api.vo.cluster.ClusterVO;
import com.bnyte.azir.common.entity.console.Cluster;

import java.util.List;

/**
 * @author bnyte
 * @since 1.0.0
 */
public interface ClusterService extends IService<Cluster> {
    void create(ClusterVO clusterVO);

    List<ClusterVO> vos();

    void deleteForTenantId(Long id);

    void deleteById(Long id);

    void updateCluster(ClusterVO clusterVO);

}
