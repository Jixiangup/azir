package com.bnyte.azir.api.service.impl.console;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bnyte.azir.common.entity.console.Cluster;
import com.bnyte.azir.dao.mapper.ClusterMapper;
import com.bnyte.azir.api.service.console.ClusterService;
import org.springframework.stereotype.Service;

/**
 * @author bnyte
 * @since 2022/5/26 17:48
 */
@Service
public class ClusterServiceImpl extends ServiceImpl<ClusterMapper, Cluster> implements ClusterService {

}
