package com.bnyte.azir.api.mapstruct;

import com.bnyte.azir.api.mapstruct.annotation.InitIgnore;
import com.bnyte.azir.api.vo.cluster.ClusterVO;
import com.bnyte.azir.common.entity.console.Cluster;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author bnyte
 * @since 1.0.0
 */
@Mapper
public interface ClusterTransfer {

    ClusterTransfer INSTANCE = Mappers.getMapper(ClusterTransfer.class);

    @InitIgnore
    Cluster toDomain(ClusterVO clusterVO);

    @Mappings({
            @Mapping(target = "tenantId", ignore = true)
    })
    ClusterVO toVO(Cluster cluster);

    List<ClusterVO> toVOS(List<Cluster> clusters);


}
