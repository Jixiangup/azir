package com.bnyte.azir.api.mapstruct;

import com.bnyte.azir.api.mapstruct.annotation.InitIgnore;
import com.bnyte.azir.api.vo.tenant.TenantVO;
import com.bnyte.azir.common.entity.console.Tenant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author bnyte
 * @since 1.0.0
 */
@Mapper
public interface TenantTransfer {

    TenantTransfer INSTANCE = Mappers.getMapper(TenantTransfer.class);

    TenantVO toTenantVO(Tenant tenant);

    @InitIgnore
    Tenant toTenant(TenantVO tenant);

    List<TenantVO> toTenantVOS(List<Tenant> tenants);

}
