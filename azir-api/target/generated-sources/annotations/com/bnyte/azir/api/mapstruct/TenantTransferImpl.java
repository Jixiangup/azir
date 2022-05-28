package com.bnyte.azir.api.mapstruct;

import com.bnyte.azir.api.vo.tenant.TenantVO;
import com.bnyte.azir.common.entity.console.Tenant;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-28T23:27:54+0800",
    comments = "version: 1.5.0.RC1, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
public class TenantTransferImpl implements TenantTransfer {

    @Override
    public TenantVO toTenantVO(Tenant tenant) {
        if ( tenant == null ) {
            return null;
        }

        TenantVO tenantVO = new TenantVO();

        tenantVO.setId( tenant.getId() );
        tenantVO.setCnName( tenant.getCnName() );
        tenantVO.setEnName( tenant.getEnName() );

        return tenantVO;
    }

    @Override
    public Tenant toTenant(TenantVO tenant) {
        if ( tenant == null ) {
            return null;
        }

        Tenant tenant1 = new Tenant();

        tenant1.setId( tenant.getId() );
        tenant1.setCnName( tenant.getCnName() );
        tenant1.setEnName( tenant.getEnName() );

        return tenant1;
    }

    @Override
    public List<TenantVO> toTenantVOS(List<Tenant> tenants) {
        if ( tenants == null ) {
            return null;
        }

        List<TenantVO> list = new ArrayList<TenantVO>( tenants.size() );
        for ( Tenant tenant : tenants ) {
            list.add( toTenantVO( tenant ) );
        }

        return list;
    }
}
