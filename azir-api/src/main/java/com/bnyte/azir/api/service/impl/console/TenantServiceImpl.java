package com.bnyte.azir.api.service.impl.console;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bnyte.azir.api.mapstruct.TenantTransfer;
import com.bnyte.azir.api.service.console.TenantService;
import com.bnyte.azir.api.vo.tenant.TenantVO;
import com.bnyte.azir.common.entity.console.Tenant;
import com.bnyte.azir.common.entity.console.User;
import com.bnyte.azir.common.enums.ECookie;
import com.bnyte.azir.common.exception.RdosDefineException;
import com.bnyte.azir.common.util.CookieUtils;
import com.bnyte.azir.common.web.response.Code;
import com.bnyte.azir.dao.mapper.TenantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author bnyte
 * @since 2022/5/28 18:25
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements TenantService {

    @Autowired
    CookieUtils cookieUtils;

    @Override
    public List<TenantVO> tenants() {
        User user = cookieUtils.currentUser();
        List<Tenant> tenants = list(
                Wrappers.lambdaQuery(Tenant.class)
                        .eq(Tenant::getDeleted, false)
                        .eq(Tenant::getUserId, user.getId())
        );
        return TenantTransfer.INSTANCE.toTenantVOS(tenants);
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
        if (Objects.nonNull(queryByCnName(tenantVO.getCnName()))) {
            throw new RdosDefineException(Code.TENANT_CN_NAME_EXISTS);
        }

        if (Objects.nonNull(queryByEnName(tenantVO.getEnName()))) {
            throw new RdosDefineException(Code.TENANT_EN_NAME_EXISTS);
        }
    }

    @Override
    public Tenant queryByEnName(String enName) {
        return getOne(Wrappers.lambdaQuery(Tenant.class).eq(Tenant::getDeleted, false).eq(Tenant::getEnName, enName));
    }

    @Override
    public Tenant queryByCnName(String cnName) {
        return getOne(Wrappers.lambdaQuery(Tenant.class).eq(Tenant::getDeleted, false).eq(Tenant::getCnName, cnName));
    }


    @Override
    public Tenant getById(Serializable id) {
        return getOne(Wrappers.lambdaQuery(Tenant.class).eq(Tenant::getId, id).eq(Tenant::getDeleted, false));
    }
}
