package org.entity.dto;

import java.io.Serializable;

public class Permission implements Serializable {
    private Long permissionId;

    private String permissionName;

    private String permissionDescription;

    private String permissionValue;

    private Long permissionParentId;

    private Long permissionTypeId;

    private static final long serialVersionUID = 1L;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription == null ? null : permissionDescription.trim();
    }

    public String getPermissionValue() {
        return permissionValue;
    }

    public void setPermissionValue(String permissionValue) {
        this.permissionValue = permissionValue == null ? null : permissionValue.trim();
    }

    public Long getPermissionParentId() {
        return permissionParentId;
    }

    public void setPermissionParentId(Long permissionParentId) {
        this.permissionParentId = permissionParentId;
    }

    public Long getPermissionTypeId() {
        return permissionTypeId;
    }

    public void setPermissionTypeId(Long permissionTypeId) {
        this.permissionTypeId = permissionTypeId;
    }
}