package org.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class Bike implements Serializable {
    private Long bikeId;

    private Long bikeManagerId;

    private Long bikeAdminId;

    private Long bikeBlockId;

    private String bikeCode;

    private Integer bikeState;

    private Integer bikeDel;

    private String bikeAddress;

    private Double bikeAtitude;

    private Double bikeLongitude;

    private Integer bikeRentNum;

    private Date bikeCreatetime;

    private String bikeBatchNumber;

    private Long bikeLockId;

    private String bikeChipCode;

    private String bikeDynamotorCode;

    private Date bikePutTime;

    private BikeRepair bikeRepair;
    
    private BikeLockInfo bikeLock;

    private Long bikeModelsId;
    
    private String bikeManagerName;

    private String bikeManagerTel;
    //
    private Models models;

    private Date bikeLastRentTime;
    
  //最后维护时间
    private Date fixLasttime;
    
    private String lockCode;
    
    private Integer bikeAlert;
    
    private Long bikeFixedReturnId;
    
    private Integer lockState;
    
    private FixedReturn fixedReturn;
    
    private Date bikeTemporarylocktime;
    
    private String bikeBlock;
    
    private String simCode;

    private static final long serialVersionUID = 1L;

    public Long getBikeId() {
        return bikeId;
    }

    public void setBikeId(Long bikeId) {
        this.bikeId = bikeId;
    }

    public Long getBikeManagerId() {
        return bikeManagerId;
    }

    public void setBikeManagerId(Long bikeManagerId) {
        this.bikeManagerId = bikeManagerId;
    }

    public Long getBikeAdminId() {
        return bikeAdminId;
    }

    public void setBikeAdminId(Long bikeAdminId) {
        this.bikeAdminId = bikeAdminId;
    }

    public Long getBikeBlockId() {
        return bikeBlockId;
    }

    public void setBikeBlockId(Long bikeBlockId) {
        this.bikeBlockId = bikeBlockId;
    }

    public String getBikeCode() {
        return bikeCode;
    }

    public void setBikeCode(String bikeCode) {
        this.bikeCode = bikeCode == null ? null : bikeCode.trim();
    }

    public Integer getBikeState() {
        return bikeState;
    }

    public void setBikeState(Integer bikeState) {
        this.bikeState = bikeState;
    }

    public Integer getBikeDel() {
        return bikeDel;
    }

    public void setBikeDel(Integer bikeDel) {
        this.bikeDel = bikeDel;
    }

    public String getBikeAddress() {
        return bikeAddress;
    }

    public void setBikeAddress(String bikeAddress) {
        this.bikeAddress = bikeAddress == null ? null : bikeAddress.trim();
    }

    public Double getBikeAtitude() {
        return bikeAtitude;
    }

    public void setBikeAtitude(Double bikeAtitude) {
        this.bikeAtitude = bikeAtitude;
    }

    public Double getBikeLongitude() {
        return bikeLongitude;
    }

    public void setBikeLongitude(Double bikeLongitude) {
        this.bikeLongitude = bikeLongitude;
    }

    public Integer getBikeRentNum() {
        return bikeRentNum;
    }

    public void setBikeRentNum(Integer bikeRentNum) {
        this.bikeRentNum = bikeRentNum;
    }

    public Date getBikeCreatetime() {
        return bikeCreatetime;
    }

    public void setBikeCreatetime(Date bikeCreatetime) {
        this.bikeCreatetime = bikeCreatetime;
    }

    public String getBikeBatchNumber() {
        return bikeBatchNumber;
    }

    public void setBikeBatchNumber(String bikeBatchNumber) {
        this.bikeBatchNumber = bikeBatchNumber == null ? null : bikeBatchNumber.trim();
    }

    public Long getBikeLockId() {
        return bikeLockId;
    }

    public void setBikeLockId(Long bikeLockId) {
        this.bikeLockId = bikeLockId;
    }

    public String getBikeChipCode() {
        return bikeChipCode;
    }

    public void setBikeChipCode(String bikeChipCode) {
        this.bikeChipCode = bikeChipCode == null ? null : bikeChipCode.trim();
    }

    public String getBikeDynamotorCode() {
        return bikeDynamotorCode;
    }

    public void setBikeDynamotorCode(String bikeDynamotorCode) {
        this.bikeDynamotorCode = bikeDynamotorCode == null ? null : bikeDynamotorCode.trim();
    }

    public Date getBikePutTime() {
        return bikePutTime;
    }

    public void setBikePutTime(Date bikePutTime) {
        this.bikePutTime = bikePutTime;
    }

    public Long getBikeModelsId() {
        return bikeModelsId;
    }

    public void setBikeModelsId(Long bikeModelsId) {
        this.bikeModelsId = bikeModelsId;
    }

    public String getBikeManagerName() {
		return bikeManagerName;
	}

	public void setBikeManagerName(String bikeManagerName) {
		this.bikeManagerName = bikeManagerName;
	}

	public String getBikeManagerTel() {
		return bikeManagerTel;
	}

	public void setBikeManagerTel(String bikeManagerTel) {
		this.bikeManagerTel = bikeManagerTel;
	}

	public Date getBikeLastRentTime() {
        return bikeLastRentTime;
    }

    public void setBikeLastRentTime(Date bikeLastRentTime) {
        this.bikeLastRentTime = bikeLastRentTime;
    }
	public BikeRepair getBikeRepair() {
		return bikeRepair;
	}

	public void setBikeRepair(BikeRepair bikeRepair) {
		this.bikeRepair = bikeRepair;
	}

	public BikeLockInfo getBikeLock() {
		return bikeLock;
	}

	public void setBikeLock(BikeLockInfo bikeLock) {
		this.bikeLock = bikeLock;
	}

	public Models getModels() {
		return models;
	}

	public void setModels(Models models) {
		this.models = models;
	}

	public Date getFixLasttime() {
		return fixLasttime;
	}

	public void setFixLasttime(Date fixLasttime) {
		this.fixLasttime = fixLasttime;
	}

	public String getLockCode() {
		return lockCode;
	}

	public void setLockCode(String lockCode) {
		this.lockCode = lockCode;
	}

	public Integer getBikeAlert() {
		return bikeAlert;
	}

	public void setBikeAlert(Integer bikeAlert) {
		this.bikeAlert = bikeAlert;
	}

	public Long getBikeFixedReturnId() {
		return bikeFixedReturnId;
	}

	public void setBikeFixedReturnId(Long bikeFixedReturnId) {
		this.bikeFixedReturnId = bikeFixedReturnId;
	}

	public Integer getLockState() {
		return lockState;
	}

	public void setLockState(Integer lockState) {
		this.lockState = lockState;
	}

	public FixedReturn getFixedReturn() {
		return fixedReturn;
	}

	public void setFixedReturn(FixedReturn fixedReturn) {
		this.fixedReturn = fixedReturn;
	}

	public Date getBikeTemporarylocktime() {
		return bikeTemporarylocktime;
	}

	public void setBikeTemporarylocktime(Date bikeTemporarylocktime) {
		this.bikeTemporarylocktime = bikeTemporarylocktime;
	}

	public String getBikeBlock() {
		return bikeBlock;
	}

	public void setBikeBlock(String bikeBlock) {
		this.bikeBlock = bikeBlock;
	}

	public String getSimCode() {
		return simCode;
	}

	public void setSimCode(String simCode) {
		this.simCode = simCode;
	}
	
}