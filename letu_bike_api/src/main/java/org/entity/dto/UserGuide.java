package org.entity.dto;

import java.io.Serializable;

public class UserGuide implements Serializable {
    private Long userGuideId;

    private String userGuideType;

    private Long userGuideAdminid;

    private String userGuideContent;
    
    private String editAdminName;
    
    private String gotoUrl;

    private static final long serialVersionUID = 1L;

    public Long getUserGuideId() {
        return userGuideId;
    }

    public void setUserGuideId(Long userGuideId) {
        this.userGuideId = userGuideId;
    }

    public String getUserGuideType() {
		return userGuideType;
	}

	public void setUserGuideType(String userGuideType) {
		this.userGuideType = userGuideType;
	}

	public Long getUserGuideAdminid() {
        return userGuideAdminid;
    }

    public void setUserGuideAdminid(Long userGuideAdminid) {
        this.userGuideAdminid = userGuideAdminid;
    }

    public String getUserGuideContent() {
        return userGuideContent;
    }

    public void setUserGuideContent(String userGuideContent) {
        this.userGuideContent = userGuideContent == null ? null : userGuideContent.trim();
    }

	public String getEditAdminName() {
		return editAdminName;
	}

	public void setEditAdminName(String editAdminName) {
		this.editAdminName = editAdminName;
	}

	public String getGotoUrl() {
		return gotoUrl;
	}

	public void setGotoUrl(String gotoUrl) {
		this.gotoUrl = gotoUrl;
	}
}