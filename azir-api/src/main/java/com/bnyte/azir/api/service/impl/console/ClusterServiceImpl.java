package com.bnyte.azir.api.service.impl.console;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bnyte.azir.api.mapstruct.ClusterTransfer;
import com.bnyte.azir.api.vo.cluster.ClusterVO;
import com.bnyte.azir.common.entity.console.Cluster;
import com.bnyte.azir.common.enums.ECookie;
import com.bnyte.azir.common.exception.RdosDefineException;
import com.bnyte.azir.common.util.CookieUtils;
import com.bnyte.azir.common.web.response.Code;
import com.bnyte.azir.dao.mapper.ClusterMapper;
import com.bnyte.azir.api.service.console.ClusterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author bnyte
 * @since 2022/5/26 17:48
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
    public List<ClusterVO> listByTenant() {
        String tenantId = cookieUtils.getValue(ECookie.TENANT_ID.getKey());
        if (!StringUtils.hasText(tenantId)) throw new RdosDefineException(Code.NO_TENANT_SELECTED);
        List<Cluster> clusters = list(
                Wrappers.lambdaQuery(Cluster.class)
                        .eq(Cluster::getDeleted, false)
                        .eq(Cluster::getTenantId, tenantId)
        );
        return ClusterTransfer.INSTANCE.toVOS(clusters);
    }
}
