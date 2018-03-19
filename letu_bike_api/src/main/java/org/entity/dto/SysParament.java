package org.entity.dto;

import java.io.Serializable;

public class SysParament implements Serializable {
    private Long sysParamentId;

    private Long sysParamentAdminId;

    private String sysParamentName;

    private String sysParamentValue;

    private String sysParamentDescription;
    
    private Integer sysParamentIsUpdate;

    private String sysParamentUpdateContent;

    private static final long serialVersionUID = 1L;

    public Long getSysParamentId() {
        return sysParamentId;
    }

    public void setSysParamentId(Long sysParamentId) {
        this.sysParamentId = sysParamentId;
    }

    public Long getSysParamentAdminId() {
        return sysParamentAdminId;
    }

    public void setSysParamentAdminId(Long sysParamentAdminId) {
        this.sysParamentAdminId = sysParamentAdminId;
    }

    public String getSysParamentName() {
        return sysParamentName;
    }

    public void setSysParamentName(String sysParamentName) {
        this.sysParamentName = sysParamentName == null ? null : sysParamentName.trim();
    }

    public String getSysParamentValue() {
        return sysParamentValue;
    }

    public void setSysParamentValue(String sysParamentValue) {
        this.sysParamentValue = sysParamentValue == null ? null : sysParamentValue.trim();
    }

    public String getSysParamentDescription() {
        return sysParamentDescription;
    }

    public void setSysParamentDescription(String sysParamentDescription) {
        this.sysParamentDescription = sysParamentDescription == null ? null : sysParamentDescription.trim();
    }

	public Integer getSysParamentIsUpdate() {
		return sysParamentIsUpdate;
	}

	public void setSysParamentIsUpdate(Integer sysParamentIsUpdate) {
		this.sysParamentIsUpdate = sysParamentIsUpdate;
	}

	public String getSysParamentUpdateContent() {
		return sysParamentUpdateContent;
	}

	public void setSysParamentUpdateContent(String sysParamentUpdateContent) {
		this.sysParamentUpdateContent = sysParamentUpdateContent;
	}
}