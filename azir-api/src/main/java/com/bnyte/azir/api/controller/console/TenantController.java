package com.bnyte.azir.api.controller.console;

import com.bnyte.azir.api.service.console.TenantService;
import com.bnyte.azir.api.vo.tenant.TenantVO;
import com.bnyte.azir.common.web.response.R;
import com.bnyte.forge.annotation.APIHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author bnyte
 * @since 2022/5/28 18:22
 */
@Api(tags = "租户控制器")
@RestController
@RequestMapping("/tenant")
public class TenantController {

    @Autowired
    TenantService tenantService;

    @APIHelper
    @GetMapping("/list")
    @ApiOperation("用户下的租户列表")
    R<List<TenantVO>> tenants() {
        return R.ok(tenantService.tenants());
    }

    @APIHelper
    @GetMapping("/select_tenant/{tenant_id}")
    @ApiOperation("切换租户")
    R<Void> selectTenant(@PathVariable("tenant_id") Long tenantId) {
        tenantService.selectTenant(tenantId);
        return R.empty();
    }

    @APIHelper
    @PostMapping("/create")
    @ApiOperation("创建租户")
    R<Void> create(@RequestBody @Validated TenantVO tenantVO) {
        tenantService.create(tenantVO);
        return R.empty();
    }

    @APIHelper
    @PutMapping("/update")
    @ApiOperation("更新租户")
    R<Void> update(@RequestBody @Validated TenantVO tenantVO) {
        tenantService.updateTenant(tenantVO);
        return R.empty();
    }

    @APIHelper
    @DeleteMapping("/delete/{id}")
    @ApiOperation("通过ID删除租户")
    R<Void> deleteById(@PathVariable("id") Long id) {
        tenantService.delete(id);
        return R.empty();
    }


}
