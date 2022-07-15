package com.bnyte.azir.api.vo.menu;

import com.bnyte.azir.common.constant.ConfigConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * @author bnyte
 * @since 1.0.0
 */
@ApiModel("菜单路由")
public class MenuVO {

    @ApiModelProperty("路由id")
    private Long id;

    @ApiModelProperty("父节点Id")
    private Long parentId = ConfigConstant.MENU_ROOT_ID;

    @ApiModelProperty("菜单路由名称")
    @NotBlank(message = "菜单路由名称不能为空")
    private String name;

    @ApiModelProperty("路由icon")
    private String icon;

    @ApiModelProperty("路由路径")
    @NotBlank(message = "菜单路由地址不能为空")
    private String path;

    @ApiModelProperty("权重")
    private Integer weights;

    @ApiModelProperty("创建时间")
    private Date gmtCreate;

    /**
     * 是否公开, 即为跳过权限验证, 即使没有权限也可以访问, 但是需要登录, 默认开启校验
     */
    @ApiModelProperty("是否公开")
    private Boolean verify;

    @ApiModelProperty("当前路由的子路由")
    private List<MenuVO> children;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<MenuVO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuVO> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getWeights() {
        return weights;
    }

    public void setWeights(Integer weights) {
        this.weights = weights;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Boolean getVerify() {
        return verify;
    }

    public void setVerify(Boolean verify) {
        this.verify = verify;
    }
}
