package com.bnyte.azir.common.param.menu;

import com.bnyte.azir.common.param.Search;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

/**
 * @author bnyte
 * @since 1.0.0
 */
@ApiModel("路由搜索参数")
public class MenuSearch extends Search {

    @ApiModelProperty("菜单路由名称")
    private String name;

    @ApiModelProperty("路由icon")
    private String icon;

    @ApiModelProperty("路由路径")
    private String path;

    public Boolean enableSearch() {
        return StringUtils.hasText(this.getIcon()) || StringUtils.hasText(this.getPath()) || StringUtils.hasText(this.getName());
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
