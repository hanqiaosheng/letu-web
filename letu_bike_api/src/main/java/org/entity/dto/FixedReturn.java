package org.entity.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class FixedReturn implements Serializable {
    private Long fixedReturnId;

    private Double fixedReturnLat;

    private Double fixedReturnLng;

    private Double fixedReturnDistance;

    private String fixedReturnModelsId;

    private String fixedReturnName;

    private Date fixedReturnCreatetime;

    private Long fixedReturnAdminId;

    private Integer fixedReturnState;

    private String fixedReturnBrief;

    private String fixedReturnTel;

    private String fixedReturnImage;
    
    private Long fixedReturnBlockId;
    
    private Long fixedReturnChannelId;
    
    private Integer fixedReturnBikeNum;
    
    private Long fixedReturnOriginatorId;
    
    private Date fixedReturnFixedTime;
    
    private String fixedReturnBlock;
    
    private String fixedReturnContent;
    
    private String latlng;
    
    private List<Models> models;
    
    private Channel channel;
    
    private String fixedReturnOriginator;//发起人
    
    private Admin admin;//发起人用
    
    private static final long serialVersionUID = 1L;

    public Long getFixedReturnId() {
        return fixedReturnId;
    }

    public void setFixedReturnId(Long fixedReturnId) {
        this.fixedReturnId = fixedReturnId;
    }

    public Double getFixedReturnLat() {
        return fixedReturnLat;
    }

    public void setFixedReturnLat(Double fixedReturnLat) {
        this.fixedReturnLat = fixedReturnLat;
    }

    public Double getFixedReturnLng() {
        return fixedReturnLng;
    }

    public void setFixedReturnLng(Double fixedReturnLng) {
        this.fixedReturnLng = fixedReturnLng;
    }

    public Double getFixedReturnDistance() {
        return fixedReturnDistance;
    }

    public void setFixedReturnDistance(Double fixedReturnDistance) {
        this.fixedReturnDistance = fixedReturnDistance;
    }

    public String getFixedReturnModelsId() {
        return fixedReturnModelsId;
    }

    public void setFixedReturnModelsId(String fixedReturnModelsId) {
        this.fixedReturnModelsId = fixedReturnModelsId == null ? null : fixedReturnModelsId.trim();
    }

    public String getFixedReturnName() {
        return fixedReturnName;
    }

    public void setFixedReturnName(String fixedReturnName) {
        this.fixedReturnName = fixedReturnName == null ? null : fixedReturnName.trim();
    }

    public Date getFixedReturnCreatetime() {
        return fixedReturnCreatetime;
    }

    public void setFixedReturnCreatetime(Date fixedReturnCreatetime) {
        this.fixedReturnCreatetime = fixedReturnCreatetime;
    }

    public Long getFixedReturnAdminId() {
        return fixedReturnAdminId;
    }

    public void setFixedReturnAdminId(Long fixedReturnAdminId) {
        this.fixedReturnAdminId = fixedReturnAdminId;
    }

    public Integer getFixedReturnState() {
        return fixedReturnState;
    }

    public void setFixedReturnState(Integer fixedReturnState) {
        this.fixedReturnState = fixedReturnState;
    }

    public String getFixedReturnBrief() {
        return fixedReturnBrief;
    }

    public void setFixedReturnBrief(String fixedReturnBrief) {
        this.fixedReturnBrief = fixedReturnBrief == null ? null : fixedReturnBrief.trim();
    }

    public String getFixedReturnTel() {
        return fixedReturnTel;
    }

    public void setFixedReturnTel(String fixedReturnTel) {
        this.fixedReturnTel = fixedReturnTel == null ? null : fixedReturnTel.trim();
    }

    public String getFixedReturnImage() {
        return fixedReturnImage;
    }

    public void setFixedReturnImage(String fixedReturnImage) {
        this.fixedReturnImage = fixedReturnImage == null ? null : fixedReturnImage.trim();
    }

	public String getLatlng() {
		return latlng;
	}

	public void setLatlng(String latlng) {
		this.latlng = latlng;
	}

	public List<Models> getModels() {
		return models;
	}

	public void setModels(List<Models> models) {
		this.models = models;
	}

	public Long getFixedReturnBlockId() {
		return fixedReturnBlockId;
	}

	public void setFixedReturnBlockId(Long fixedReturnBlockId) {
		this.fixedReturnBlockId = fixedReturnBlockId;
	}

	public Long getFixedReturnChannelId() {
		return fixedReturnChannelId;
	}

	public void setFixedReturnChannelId(Long fixedReturnChannelId) {
		this.fixedReturnChannelId = fixedReturnChannelId;
	}

	public Integer getFixedReturnBikeNum() {
		return fixedReturnBikeNum;
	}

	public void setFixedReturnBikeNum(Integer fixedReturnBikeNum) {
		this.fixedReturnBikeNum = fixedReturnBikeNum;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Long getFixedReturnOriginatorId() {
		return fixedReturnOriginatorId;
	}

	public void setFixedReturnOriginatorId(Long fixedReturnOriginatorId) {
		this.fixedReturnOriginatorId = fixedReturnOriginatorId;
	}

	public String getFixedReturnOriginator() {
		return fixedReturnOriginator;
	}

	public void setFixedReturnOriginator(String fixedReturnOriginator) {
		this.fixedReturnOriginator = fixedReturnOriginator;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Date getFixedReturnFixedTime() {
		return fixedReturnFixedTime;
	}

	public void setFixedReturnFixedTime(Date fixedReturnFixedTime) {
		this.fixedReturnFixedTime = fixedReturnFixedTime;
	}

	public String getFixedReturnContent() {
		return fixedReturnContent;
	}

	public void setFixedReturnContent(String fixedReturnContent) {
		this.fixedReturnContent = fixedReturnContent;
	}

	public String getFixedReturnBlock() {
		return fixedReturnBlock;
	}

	public void setFixedReturnBlock(String fixedReturnBlock) {
		this.fixedReturnBlock = fixedReturnBlock;
	}

}