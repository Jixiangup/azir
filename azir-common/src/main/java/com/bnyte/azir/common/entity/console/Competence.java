package com.bnyte.azir.common.entity.console;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bnyte.azir.common.entity.AutoId;

/**
 * @author bnyte
 * @since 2022/6/2 14:29
 */
@TableName("t_console_competence")
public class Competence extends AutoId {

    /**
     * 路由id
     */
    private Long menuId;

    /**
     * 是否有删除权限
     */
    private Boolean remove;

    /**
     * 是否有编辑权限
     */
    private Boolean edit;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Boolean getRemove() {
        return remove;
    }

    public void setRemove(Boolean remove) {
        this.remove = remove;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }
}
