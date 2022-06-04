package com.bnyte.azir.common.entity.console;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bnyte.azir.common.entity.AutoId;

/**
 * @author bnyte
 * @since 2022/6/4 21:46
 */
@TableName("t_role_competence")
public class RoleCompetence extends AutoId {

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 权限id
     */
    private Long competenceId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getCompetenceId() {
        return competenceId;
    }

    public void setCompetenceId(Long competenceId) {
        this.competenceId = competenceId;
    }
}
