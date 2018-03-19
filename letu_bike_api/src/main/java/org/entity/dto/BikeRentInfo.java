package org.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BikeRentInfo implements Serializable {
    private Long rentInfoId;

    private Long rentInfoBikeId;

    private Long rentInfoUserId;

    private Date rentStarttime;

    private Date rentEndtime;

    private Double rentStartlat;

    private Double rentStartlng;

    private Double rentEndlat;

    private Double rentEndlng;

    private BigDecimal rentPrice;

    private BigDecimal rentInsurancePrice;

    private Date rentPayTime;

    private Integer rentState;

    private Integer rentDistance;

    private Integer rentPayType;

    private Integer rentIsdel;

    private Long rentStartBlockId;

    private Long rentEndBlockId;

    private String rentOrderCode;
    
    private Integer rentLongtime;
    
    private Long rentStartFixedId;

    private Long rentEndFixedId;
    
    private Long rentBikeChannelId;

    //search
    
    private String bBikeCode;
        
    private List<Long> rentInfoIds;
    
    private List<Long> bikeIds;
    
    private Date rentFromTime;
    
    private Date rentTotime;
    
    private Date returnFromTime;
    
    private Date returnTotime;
    
    private Bike bike;
    
    private List<Long> rentBlockIds;
    
    private List<Long> returnBlockIds;
    
    //user
    private User user;
    
    private String rentLatLng;
    
    private String returnLatLng;
    
    private static final long serialVersionUID = 1L;
    
    private Integer rentIsvillager;
    
    private Date rentTemporarylocktime;
    
    private Long rentInvoiceId;
    
    private Channel channel;
    
    private Long time;//时间差 分钟
    
    private String startFixedName;//租赁开始地点
    
    private String endFixedName;//租赁结束地点
    
    private String rentStartBlock;

    private String rentEndBlock;
    
    private BigDecimal rentCouponMoney;
    
    private String weekName;

    private Long rentGuideGroupId;

    private Integer rentUnlockWay;

    private Long rentCouponId;
    
	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Integer getRentIsvillager() {
		return rentIsvillager;
	}

	public void setRentIsvillager(Integer rentIsvillager) {
		this.rentIsvillager = rentIsvillager;
	}

	public Long getRentInfoId() {
        return rentInfoId;
    }

    public void setRentInfoId(Long rentInfoId) {
        this.rentInfoId = rentInfoId;
    }

    public Long getRentInfoBikeId() {
        return rentInfoBikeId;
    }

    public void setRentInfoBikeId(Long rentInfoBikeId) {
        this.rentInfoBikeId = rentInfoBikeId;
    }

    public Long getRentInfoUserId() {
        return rentInfoUserId;
    }

    public void setRentInfoUserId(Long rentInfoUserId) {
        this.rentInfoUserId = rentInfoUserId;
    }

    public Date getRentStarttime() {
        return rentStarttime;
    }

    public void setRentStarttime(Date rentStarttime) {
        this.rentStarttime = rentStarttime;
    }

    public Date getRentEndtime() {
        return rentEndtime;
    }

    public void setRentEndtime(Date rentEndtime) {
        this.rentEndtime = rentEndtime;
    }

    public Double getRentStartlat() {
        return rentStartlat;
    }

    public void setRentStartlat(Double rentStartlat) {
        this.rentStartlat = rentStartlat;
    }

    public Double getRentStartlng() {
        return rentStartlng;
    }

    public void setRentStartlng(Double rentStartlng) {
        this.rentStartlng = rentStartlng;
    }

    public Double getRentEndlat() {
        return rentEndlat;
    }

    public void setRentEndlat(Double rentEndlat) {
        this.rentEndlat = rentEndlat;
    }

    public Double getRentEndlng() {
        return rentEndlng;
    }

    public void setRentEndlng(Double rentEndlng) {
        this.rentEndlng = rentEndlng;
    }

    public BigDecimal getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(BigDecimal rentPrice) {
        this.rentPrice = rentPrice;
    }

    public BigDecimal getRentInsurancePrice() {
        return rentInsurancePrice;
    }

    public void setRentInsurancePrice(BigDecimal rentInsurancePrice) {
        this.rentInsurancePrice = rentInsurancePrice;
    }

    public Date getRentPayTime() {
        return rentPayTime;
    }

    public void setRentPayTime(Date rentPayTime) {
        this.rentPayTime = rentPayTime;
    }

    public Integer getRentState() {
        return rentState;
    }

    public void setRentState(Integer rentState) {
        this.rentState = rentState;
    }

    public Integer getRentDistance() {
        return rentDistance;
    }

    public void setRentDistance(Integer rentDistance) {
        this.rentDistance = rentDistance;
    }

    public Integer getRentPayType() {
        return rentPayType;
    }

    public void setRentPayType(Integer rentPayType) {
        this.rentPayType = rentPayType;
    }

    public Integer getRentIsdel() {
        return rentIsdel;
    }

    public void setRentIsdel(Integer rentIsdel) {
        this.rentIsdel = rentIsdel;
    }

    public Long getRentStartBlockId() {
        return rentStartBlockId;
    }

    public void setRentStartBlockId(Long rentStartBlockId) {
        this.rentStartBlockId = rentStartBlockId;
    }

    public Long getRentEndBlockId() {
        return rentEndBlockId;
    }

    public void setRentEndBlockId(Long rentEndBlockId) {
        this.rentEndBlockId = rentEndBlockId;
    }

    public String getRentOrderCode() {
        return rentOrderCode;
    }

    public void setRentOrderCode(String rentOrderCode) {
        this.rentOrderCode = rentOrderCode == null ? null : rentOrderCode.trim();
    }

	public Long getRentStartFixedId() {
		return rentStartFixedId;
	}

	public void setRentStartFixedId(Long rentStartFixedId) {
		this.rentStartFixedId = rentStartFixedId;
	}

	public Long getRentEndFixedId() {
		return rentEndFixedId;
	}

	public void setRentEndFixedId(Long rentEndFixedId) {
		this.rentEndFixedId = rentEndFixedId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Long> getRentInfoIds() {
		return rentInfoIds;
	}

	public void setRentInfoIds(List<Long> rentInfoIds) {
		this.rentInfoIds = rentInfoIds;
	}

	public Date getRentFromTime() {
		return rentFromTime;
	}

	public void setRentFromTime(Date rentFromTime) {
		this.rentFromTime = rentFromTime;
	}

	public Date getRentTotime() {
		return rentTotime;
	}

	public void setRentTotime(Date rentTotime) {
		this.rentTotime = rentTotime;
	}

	public Date getReturnFromTime() {
		return returnFromTime;
	}

	public void setReturnFromTime(Date returnFromTime) {
		this.returnFromTime = returnFromTime;
	}

	public Date getReturnTotime() {
		return returnTotime;
	}

	public void setReturnTotime(Date returnTotime) {
		this.returnTotime = returnTotime;
	}

	public String getbBikeCode() {
		return bBikeCode;
	}

	public void setbBikeCode(String bBikeCode) {
		this.bBikeCode = bBikeCode;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public String getRentLatLng() {
		return rentLatLng;
	}

	public void setRentLatLng(String rentLatLng) {
		this.rentLatLng = rentLatLng;
	}

	public String getReturnLatLng() {
		return returnLatLng;
	}

	public void setReturnLatLng(String returnLatLng) {
		this.returnLatLng = returnLatLng;
	}

	public List<Long> getBikeIds() {
		return bikeIds;
	}

	public void setBikeIds(List<Long> bikeIds) {
		this.bikeIds = bikeIds;
	}

	public List<Long> getRentBlockIds() {
		return rentBlockIds;
	}

	public void setRentBlockIds(List<Long> rentBlockIds) {
		this.rentBlockIds = rentBlockIds;
	}

	public List<Long> getReturnBlockIds() {
		return returnBlockIds;
	}

	public void setReturnBlockIds(List<Long> returnBlockIds) {
		this.returnBlockIds = returnBlockIds;
	}

	public Date getRentTemporarylocktime() {
		return rentTemporarylocktime;
	}

	public void setRentTemporarylocktime(Date rentTemporarylocktime) {
		this.rentTemporarylocktime = rentTemporarylocktime;
	}

	public Long getRentInvoiceId() {
		return rentInvoiceId;
	}

	public void setRentInvoiceId(Long rentInvoiceId) {
		this.rentInvoiceId = rentInvoiceId;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Integer getRentLongtime() {
		return rentLongtime;
	}

	public void setRentLongtime(Integer rentLongtime) {
		this.rentLongtime = rentLongtime;
	}

	public String getStartFixedName() {
		return startFixedName;
	}

	public void setStartFixedName(String startFixedName) {
		this.startFixedName = startFixedName;
	}

	public String getEndFixedName() {
		return endFixedName;
	}

	public void setEndFixedName(String endFixedName) {
		this.endFixedName = endFixedName;
	}

	public Long getRentBikeChannelId() {
		return rentBikeChannelId;
	}

	public void setRentBikeChannelId(Long rentBikeChannelId) {
		this.rentBikeChannelId = rentBikeChannelId;
	}

	public String getRentStartBlock() {
		return rentStartBlock;
	}

	public void setRentStartBlock(String rentStartBlock) {
		this.rentStartBlock = rentStartBlock;
	}

	public String getRentEndBlock() {
		return rentEndBlock;
	}

	public void setRentEndBlock(String rentEndBlock) {
		this.rentEndBlock = rentEndBlock;
	}

	public BigDecimal getRentCouponMoney() {
		return rentCouponMoney;
	}

	public void setRentCouponMoney(BigDecimal rentCouponMoney) {
		this.rentCouponMoney = rentCouponMoney;
	}

	public String getWeekName() {
		return weekName;
	}

	public void setWeekName(String weekName) {
		this.weekName = weekName;
	}

	public Long getRentGuideGroupId() {
		return rentGuideGroupId;
	}

	public void setRentGuideGroupId(Long rentGuideGroupId) {
		this.rentGuideGroupId = rentGuideGroupId;
	}

	public Integer getRentUnlockWay() {
		return rentUnlockWay;
	}

	public void setRentUnlockWay(Integer rentUnlockWay) {
		this.rentUnlockWay = rentUnlockWay;
	}

	public Long getRentCouponId() {
		return rentCouponId;
	}

	public void setRentCouponId(Long rentCouponId) {
		this.rentCouponId = rentCouponId;
	}
}