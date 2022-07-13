package com.bnyte.azir.api.vo.tenant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author bnyte
 * @since 1.0.0
 */
@ApiModel("租户信息")
public class TenantVO {

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("租户中文名称")
    @NotBlank(message = "租户中文名称不能为空")
    private String cnName;

    @ApiModelProperty("租户英文名称")
    @NotBlank(message = "租户英文名称不能为空")
    private String enName;

    @ApiModelProperty(hidden = true)
    private Long userId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
