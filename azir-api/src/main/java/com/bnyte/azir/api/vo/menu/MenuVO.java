package com.bnyte.azir.api.vo.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author bnyte
 * @since 2022/5/31 13:16
 */
@ApiModel("菜单路由")
public class MenuVO {

    @ApiModelProperty("父节点Id")
    private Long parentId;

    @ApiModelProperty("菜单路由名称")
    private String menuName;

    @ApiModelProperty("路由icon")
    private String icon;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
