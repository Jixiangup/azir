package com.bnyte.azir.api.vo.cluster;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author bnyte
 * @since 1.0.0
 */
@ApiModel("创建集群")
public class ClusterVO {

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("集群中文名称")
    @NotBlank(message = "集群中文名称不能为空")
    private String cnName;

    @ApiModelProperty("集群英文名称")
    @NotBlank(message = "集群英文名称不能为空")
    private String enName;

    @ApiModelProperty(value = "租户id", hidden = true)
    private Long tenantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }
}
