package com.bnyte.azir.common.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author bnyte
 * @since 1。0.0
 */
@ApiModel("搜索参数对象")
public class Search {

    @ApiModelProperty("当前页码")
    private Long current = 1L;

    @ApiModelProperty("查询记录数")
    private Long pageSize = 20L;

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }
}
