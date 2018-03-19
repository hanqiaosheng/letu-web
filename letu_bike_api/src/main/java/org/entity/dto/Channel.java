package org.entity.dto;

import java.io.Serializable;

public class Channel implements Serializable {
    private Long channelId;

    private String channelName;

    private String channelChargerName;

    private String channelChargerTel;

    private Long channelChargerAdminid;

    private Integer channelState;

    private Integer channelLevel;

    private Long channelParentId;

    private String channelNum;

    private String channelIntroduction;
    
    private String adminName;
    
    private Long channelCityId;

    private static final long serialVersionUID = 1L;

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getChannelChargerName() {
        return channelChargerName;
    }

    public void setChannelChargerName(String channelChargerName) {
        this.channelChargerName = channelChargerName == null ? null : channelChargerName.trim();
    }

    public String getChannelChargerTel() {
        return channelChargerTel;
    }

    public void setChannelChargerTel(String channelChargerTel) {
        this.channelChargerTel = channelChargerTel == null ? null : channelChargerTel.trim();
    }

    public Long getChannelChargerAdminid() {
        return channelChargerAdminid;
    }

    public void setChannelChargerAdminid(Long channelChargerAdminid) {
        this.channelChargerAdminid = channelChargerAdminid;
    }

    public Integer getChannelState() {
        return channelState;
    }

    public void setChannelState(Integer channelState) {
        this.channelState = channelState;
    }

    public Integer getChannelLevel() {
        return channelLevel;
    }

    public void setChannelLevel(Integer channelLevel) {
        this.channelLevel = channelLevel;
    }

    public Long getChannelParentId() {
        return channelParentId;
    }

    public void setChannelParentId(Long channelParentId) {
        this.channelParentId = channelParentId;
    }

    public String getChannelNum() {
        return channelNum;
    }

    public void setChannelNum(String channelNum) {
        this.channelNum = channelNum == null ? null : channelNum.trim();
    }

    public String getChannelIntroduction() {
        return channelIntroduction;
    }

    public void setChannelIntroduction(String channelIntroduction) {
        this.channelIntroduction = channelIntroduction == null ? null : channelIntroduction.trim();
    }

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Long getChannelCityId() {
		return channelCityId;
	}

	public void setChannelCityId(Long channelCityId) {
		this.channelCityId = channelCityId;
	}
}