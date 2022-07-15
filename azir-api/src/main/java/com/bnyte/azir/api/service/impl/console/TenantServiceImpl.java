package com.bnyte.azir.api.service.impl.console;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bnyte.azir.common.constant.MessageConstant;
import com.bnyte.azir.api.mapstruct.TenantTransfer;
import com.bnyte.azir.api.service.console.ClusterService;
import com.bnyte.azir.api.service.console.TenantService;
import com.bnyte.azir.api.util.CookieUtils;
import com.bnyte.azir.api.vo.tenant.TenantVO;
import com.bnyte.azir.common.entity.console.Tenant;
import com.bnyte.azir.common.entity.console.User;
import com.bnyte.azir.common.enums.ECookie;
import com.bnyte.azir.common.exception.RdosDefineException;
import com.bnyte.azir.common.web.response.Code;
import com.bnyte.azir.dao.mapper.TenantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;

/**
 * @author bnyte
 * @since 1.0.0
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements TenantService {

    @Autowired
    ClusterService clusterService;

    @Autowired
    CookieUtils cookieUtils;

    @Override
    public List<TenantVO> tenants() {
        User user = cookieUtils.currentUser();
        return TenantTransfer.INSTANCE.toTenantVOS(tenantsByUserId(user.getId()));
    }

    private List<Tenant> tenantsByUserId(Long id) {
        return list(
                Wrappers.lambdaQuery(Tenant.class)
                        .eq(Tenant::getUserId, id)
        );
    }

    @Override
    public void selectTenant(Long tenantId) {
        Tenant tenant = getById(tenantId);
        if (Objects.isNull(tenant)) throw new RdosDefineException(Code.TENANT_NOT_EXISTS);
        cookieUtils.set(ECookie.TENANT_NAME.getKey(), tenant.getCnName(), true);
        cookieUtils.set(ECookie.TENANT_ID.getKey(), tenant.getId().toString(), true);
    }

    @Override
    public void create(TenantVO tenantVO) {
        createPreCheck(tenantVO);

        Tenant tenant = TenantTransfer.INSTANCE.toTenant(tenantVO);
        tenant.setUserId(cookieUtils.currentUser().getId());

        save(tenant);
    }

    private void createPreCheck(TenantVO tenantVO) {
        assertName(tenantVO);
    }

    private void assertName(TenantVO tenantVO) {
        if (Objects.nonNull(queryByCnName(tenantVO.getCnName()))) {
            throw new RdosDefineException(Code.TENANT_CN_NAME_EXISTS);
        }

        if (Objects.nonNull(queryByEnName(tenantVO.getEnName()))) {
            throw new RdosDefineException(Code.TENANT_EN_NAME_EXISTS);
        }
    }

    @Override
    public Tenant queryByEnName(String enName) {
        return getOne(Wrappers.lambdaQuery(Tenant.class).eq(Tenant::getEnName, enName));
    }

    @Override
    public Tenant queryByCnName(String cnName) {
        return getOne(Wrappers.lambdaQuery(Tenant.class).eq(Tenant::getCnName, cnName));
    }

    @Override
    public void updateTenant(TenantVO tenantVO) {
        Assert.notNull(tenantVO.getId(), MessageConstant.ID_CANNOT_BE_EMPTY);

        assertName(tenantVO);

        Tenant tenant = TenantTransfer.INSTANCE.toTenant(tenantVO);
        tenant.update();

        updateById(tenant);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Tenant tenant = getById(id);
        if (Objects.isNull(tenant)) return;

        // 查询先删除集群
        clusterService.deleteForTenantId(id);

        // 删除租户
        removeById(id);

    }
}
