package org.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class BikeFixInfo implements Serializable {
    private Long fixId;

    private Long fixBikeId;

    private String fixMan;

    private String fixTel;

    private Date fixStarttime;

    private Date fixEndtime;

    private Double fixLongitude;
    
    private Double fixAtitude;

    private String fixRemark;

    private String fixBatchNumber;
    
    private Integer fixDel;
    
    private Long fixBlockId;
    
    private String fixBlock;
    
    //车辆
    private Bike bike;

    private static final long serialVersionUID = 1L;

    public Long getFixId() {
        return fixId;
    }

    public void setFixId(Long fixId) {
        this.fixId = fixId;
    }

    public Long getFixBikeId() {
		return fixBikeId;
	}

	public void setFixBikeId(Long fixBikeId) {
		this.fixBikeId = fixBikeId;
	}

	public String getFixMan() {
        return fixMan;
    }

    public void setFixMan(String fixMan) {
        this.fixMan = fixMan == null ? null : fixMan.trim();
    }

    public String getFixTel() {
        return fixTel;
    }

    public void setFixTel(String fixTel) {
        this.fixTel = fixTel == null ? null : fixTel.trim();
    }

    public Date getFixStarttime() {
        return fixStarttime;
    }

    public void setFixStarttime(Date fixStarttime) {
        this.fixStarttime = fixStarttime;
    }

    public Date getFixEndtime() {
        return fixEndtime;
    }

    public void setFixEndtime(Date fixEndtime) {
        this.fixEndtime = fixEndtime;
    }

    public String getFixRemark() {
        return fixRemark;
    }

    public void setFixRemark(String fixRemark) {
        this.fixRemark = fixRemark == null ? null : fixRemark.trim();
    }

    public String getFixBatchNumber() {
        return fixBatchNumber;
    }

    public void setFixBatchNumber(String fixBatchNumber) {
        this.fixBatchNumber = fixBatchNumber == null ? null : fixBatchNumber.trim();
    }

	public Double getFixLongitude() {
		return fixLongitude;
	}

	public void setFixLongitude(Double fixLongitude) {
		this.fixLongitude = fixLongitude;
	}

	public Double getFixAtitude() {
		return fixAtitude;
	}

	public void setFixAtitude(Double fixAtitude) {
		this.fixAtitude = fixAtitude;
	}

	public Integer getFixDel() {
		return fixDel;
	}

	public void setFixDel(Integer fixDel) {
		this.fixDel = fixDel;
	}

	public Long getFixBlockId() {
		return fixBlockId;
	}

	public void setFixBlockId(Long fixBlockId) {
		this.fixBlockId = fixBlockId;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public String getFixBlock() {
		return fixBlock;
	}

	public void setFixBlock(String fixBlock) {
		this.fixBlock = fixBlock;
	}

}