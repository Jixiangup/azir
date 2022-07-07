package com.bnyte.azir.api.service.console;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bnyte.azir.api.vo.tenant.TenantVO;
import com.bnyte.azir.common.entity.console.Tenant;
import com.bnyte.azir.common.entity.console.User;

import java.io.Serializable;
import java.util.List;

/**
 * @author bnyte
 * @since 1.0.0
 */
public interface TenantService extends IService<Tenant> {
    List<TenantVO> tenants();

    void selectTenant(Long tenantId);

    void create(TenantVO tenantVO);

    Tenant queryByEnName(String enName);

    Tenant queryByCnName(String cnName);

    void updateTenant(TenantVO tenantVO);

    void delete(Long id);
}
