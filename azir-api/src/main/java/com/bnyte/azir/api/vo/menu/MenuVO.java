package com.bnyte.azir.api.vo.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author bnyte
 * @since 2022/5/31 13:16
 */
@ApiModel("菜单路由")
public class MenuVO {

    @ApiModelProperty("路由id")
    private Long id;

    @ApiModelProperty("父节点Id")
    private Long parentId;

    @ApiModelProperty("菜单路由名称")
    private String name;

    @ApiModelProperty("路由icon")
    private String icon;

    @ApiModelProperty("路由路径")
    private String path;

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
}
