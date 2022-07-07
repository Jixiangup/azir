package com.bnyte.azir.api.service.impl.console;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bnyte.azir.api.mapstruct.ClusterTransfer;
import com.bnyte.azir.api.service.console.ClusterService;
import com.bnyte.azir.api.util.CookieUtils;
import com.bnyte.azir.api.vo.cluster.ClusterVO;
import com.bnyte.azir.common.entity.console.Cluster;
import com.bnyte.azir.common.enums.ECookie;
import com.bnyte.azir.common.exception.RdosDefineException;
import com.bnyte.azir.common.web.response.Code;
import com.bnyte.azir.dao.mapper.ClusterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bnyte
 * @since 1.0.0
 */
@Service
public class ClusterServiceImpl extends ServiceImpl<ClusterMapper, Cluster> implements ClusterService {

    @Autowired
    CookieUtils cookieUtils;

    @Override
    public void create(ClusterVO clusterVO) {
        String tenantId = cookieUtils.getValue(ECookie.TENANT_ID.getKey());
        if (!StringUtils.hasText(tenantId)) throw new RdosDefineException(Code.NO_TENANT_SELECTED);
        clusterVO.setTenantId(Long.parseLong(tenantId));
        Cluster cluster = ClusterTransfer.INSTANCE.toDomain(clusterVO);
        save(cluster);
    }

    @Override
    public List<ClusterVO> vos() {
        String tenantId = cookieUtils.getValue(ECookie.TENANT_ID.getKey());
        if (!StringUtils.hasText(tenantId)) throw new RdosDefineException(Code.NO_TENANT_SELECTED);
        List<Cluster> clusters = list();
        return ClusterTransfer.INSTANCE.toVOS(clusters);
    }

    @Override
    @Transactional
    public void deleteForTenantId(Long id) {

        List<Cluster> clusters = list();
        if (CollectionUtils.isEmpty(clusters)) return;

        // 后续这里可能会涉及到删除组件

        removeByIds(clusters.stream().map(Cluster::getId).collect(Collectors.toList()));

    }

    @Override
    public void deleteById(Long id) {
        // 这个位置未来会校验是否包含组件

        // 删除集群
        removeById(id);
    }

    @Override
    public void updateCluster(ClusterVO clusterVO) {
        Assert.notNull(clusterVO.getId(), "集群id不能为空");

        Cluster cluster = ClusterTransfer.INSTANCE.toDomain(clusterVO);
        cluster.update();
        updateById(cluster);
    }
}
