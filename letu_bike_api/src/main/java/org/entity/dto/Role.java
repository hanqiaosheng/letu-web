package org.entity.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Role implements Serializable {
    private Long roleId;

    private String roleName;

    private String roleValue;

    private Integer roleState;

    private Date roleCreatetime;

    private Integer roleHasnum;

    private Date roleUpdatetime;

    private Long roleCreateman;

    private String roleDescription;
    //权限
    private List<Permission> permissions;

    public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	private static final long serialVersionUID = 1L;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleValue() {
        return roleValue;
    }

    public void setRoleValue(String roleValue) {
        this.roleValue = roleValue == null ? null : roleValue.trim();
    }

    public Integer getRoleState() {
        return roleState;
    }

    public void setRoleState(Integer roleState) {
        this.roleState = roleState;
    }

    public Date getRoleCreatetime() {
        return roleCreatetime;
    }

    public void setRoleCreatetime(Date roleCreatetime) {
        this.roleCreatetime = roleCreatetime;
    }

    public Integer getRoleHasnum() {
        return roleHasnum;
    }

    public void setRoleHasnum(Integer roleHasnum) {
        this.roleHasnum = roleHasnum;
    }

    public Date getRoleUpdatetime() {
        return roleUpdatetime;
    }

    public void setRoleUpdatetime(Date roleUpdatetime) {
        this.roleUpdatetime = roleUpdatetime;
    }

    public Long getRoleCreateman() {
        return roleCreateman;
    }

    public void setRoleCreateman(Long roleCreateman) {
        this.roleCreateman = roleCreateman;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription == null ? null : roleDescription.trim();
    }
}