package com.bnyte.azir.common.dto;

/**
 * @author bnyte
 * @since 2022/6/2 15:48
 */
public class CompetenceDTO {
    private Long id;
    private Long menuId;
    private Long userId;
    private Long roleId;
    private Boolean remove;
    private Boolean edit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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
