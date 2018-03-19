package org.entity.dto;

import java.io.Serializable;

public class BikeLockInfo implements Serializable {
    private Long bikeLockId;

    private Long bikeLockBikeId;

    private String bikeLockCode;

    private Integer bikeLockEquipmentState;

    private Double bikeLockVoltage;

    private Integer bikeLockStatus;

    private Double bikeLockLat;

    private Double bikeLockLng;

    private Double bikeLockHeight;

    private Integer bikeLockDel;

    private Long bikeLockBlockid;

    private Integer bikeLockPower;

    private String bikeLockDomainPwd;

    private String bikeLockDomain;

    private String bikeLockPort;

    private Integer bikeLockDomainPwdState;

    private Integer bikeLockDominState;
    
    private String bikeCode;
    
    private Integer bikeLockState;
    
    private Integer bikeLockType;
    
    private Bike bike;
    
    private String bikeLockVersion;
    
    private Integer bikeLockUpstate;

    private String bikeLockUpfile;

    private String bikeLockUpversion;
    
    private Integer bikeLockIsFence;
    
    private String bikeLockSimCode;

    private static final long serialVersionUID = 1L;
    
    
    public Integer getBikeLockUpstate() {
		return bikeLockUpstate;
	}

	public void setBikeLockUpstate(Integer bikeLockUpstate) {
		this.bikeLockUpstate = bikeLockUpstate;
	}

	public String getBikeLockUpfile() {
		return bikeLockUpfile;
	}

	public void setBikeLockUpfile(String bikeLockUpfile) {
		this.bikeLockUpfile = bikeLockUpfile;
	}

	public String getBikeLockUpversion() {
		return bikeLockUpversion;
	}

	public void setBikeLockUpversion(String bikeLockUpversion) {
		this.bikeLockUpversion = bikeLockUpversion;
	}

	public String getBikeLockVersion() {
		return bikeLockVersion;
	}

	public void setBikeLockVersion(String bikeLockVersion) {
		this.bikeLockVersion = bikeLockVersion;
	}

	public Integer getBikeLockType() {
		return bikeLockType;
	}

	public void setBikeLockType(Integer bikeLockType) {
		this.bikeLockType = bikeLockType;
	}

	public Long getBikeLockId() {
        return bikeLockId;
    }

    public void setBikeLockId(Long bikeLockId) {
        this.bikeLockId = bikeLockId;
    }

    public Long getBikeLockBikeId() {
        return bikeLockBikeId;
    }

    public void setBikeLockBikeId(Long bikeLockBikeId) {
        this.bikeLockBikeId = bikeLockBikeId;
    }

    public String getBikeLockCode() {
        return bikeLockCode;
    }

    public void setBikeLockCode(String bikeLockCode) {
        this.bikeLockCode = bikeLockCode == null ? null : bikeLockCode.trim();
    }

    public Integer getBikeLockEquipmentState() {
        return bikeLockEquipmentState;
    }

    public void setBikeLockEquipmentState(Integer bikeLockEquipmentState) {
        this.bikeLockEquipmentState = bikeLockEquipmentState;
    }

    public Double getBikeLockVoltage() {
        return bikeLockVoltage;
    }

    public void setBikeLockVoltage(Double bikeLockVoltage) {
        this.bikeLockVoltage = bikeLockVoltage;
    }

    public Integer getBikeLockStatus() {
        return bikeLockStatus;
    }

    public void setBikeLockStatus(Integer bikeLockStatus) {
        this.bikeLockStatus = bikeLockStatus;
    }

    public Double getBikeLockLat() {
        return bikeLockLat;
    }

    public void setBikeLockLat(Double bikeLockLat) {
        this.bikeLockLat = bikeLockLat;
    }

    public Double getBikeLockLng() {
        return bikeLockLng;
    }

    public void setBikeLockLng(Double bikeLockLng) {
        this.bikeLockLng = bikeLockLng;
    }

    public Double getBikeLockHeight() {
        return bikeLockHeight;
    }

    public void setBikeLockHeight(Double bikeLockHeight) {
        this.bikeLockHeight = bikeLockHeight;
    }

    public Integer getBikeLockDel() {
        return bikeLockDel;
    }

    public void setBikeLockDel(Integer bikeLockDel) {
        this.bikeLockDel = bikeLockDel;
    }

    public Long getBikeLockBlockid() {
        return bikeLockBlockid;
    }

    public void setBikeLockBlockid(Long bikeLockBlockid) {
        this.bikeLockBlockid = bikeLockBlockid;
    }

    public Integer getBikeLockPower() {
        return bikeLockPower;
    }

    public void setBikeLockPower(Integer bikeLockPower) {
        this.bikeLockPower = bikeLockPower;
    }

    public String getBikeLockDomainPwd() {
        return bikeLockDomainPwd;
    }

    public void setBikeLockDomainPwd(String bikeLockDomainPwd) {
        this.bikeLockDomainPwd = bikeLockDomainPwd == null ? null : bikeLockDomainPwd.trim();
    }

    public String getBikeLockDomain() {
        return bikeLockDomain;
    }

    public void setBikeLockDomain(String bikeLockDomain) {
        this.bikeLockDomain = bikeLockDomain == null ? null : bikeLockDomain.trim();
    }

    public String getBikeLockPort() {
        return bikeLockPort;
    }

    public void setBikeLockPort(String bikeLockPort) {
        this.bikeLockPort = bikeLockPort == null ? null : bikeLockPort.trim();
    }

    public Integer getBikeLockDomainPwdState() {
        return bikeLockDomainPwdState;
    }

    public void setBikeLockDomainPwdState(Integer bikeLockDomainPwdState) {
        this.bikeLockDomainPwdState = bikeLockDomainPwdState;
    }

    public Integer getBikeLockDominState() {
        return bikeLockDominState;
    }

    public void setBikeLockDominState(Integer bikeLockDominState) {
        this.bikeLockDominState = bikeLockDominState;
    }

	public String getBikeCode() {
		return bikeCode;
	}

	public void setBikeCode(String bikeCode) {
		this.bikeCode = bikeCode;
	}

	public Integer getBikeLockState() {
		return bikeLockState;
	}

	public void setBikeLockState(Integer bikeLockState) {
		this.bikeLockState = bikeLockState;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public Integer getBikeLockIsFence() {
		return bikeLockIsFence;
	}

	public void setBikeLockIsFence(Integer bikeLockIsFence) {
		this.bikeLockIsFence = bikeLockIsFence;
	}

	public String getBikeLockSimCode() {
		return bikeLockSimCode;
	}

	public void setBikeLockSimCode(String bikeLockSimCode) {
		this.bikeLockSimCode = bikeLockSimCode;
	}
    
}