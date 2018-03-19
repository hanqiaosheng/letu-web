package org.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class User implements Serializable {
    private Long userId;

    private Long userAccountId;

    private String userNickname;

    private String userTel;

    private String userIdcard;

    private String userRealname;

    private String userProfileImage;

    private Integer userGender;

    private String userOpenid;

    private String userUnionid;

    private Integer userState;

    private Date userCreatetime;

    private Date userLogintime;

    private Integer userUsetime;

    private Integer userUseDistance;

    private Long userChannelId;

    private Short userIsblacklist;

    private Date userDefriendTime;
    
    private String fromTime;
    
    private String toTime;
    
    private Long defriendAdminId;
    
    private String defriendAdminName;
    
    private String defriendReason;
    
    private String userToken;
    
    private String userSmallOpenid;
    
    private String userSessionKey;
    
    private Channel channel;
    
    private BigDecimal accountAvailableBalance;
    
    private Date userVipexpirationdate;
    
    private Date userLastusebiketime;

    private Date userFistusebiketime;

    private Long userFistusebikeid;
    
    private Bike bike;
    
    private Date userBirthday;
    
    private Double userTotalCalorie;

    private Double userTotalDistance;

    private Double userTotalRideTime;
    
    private Integer userLevel;

    private Integer userGrade;
    
    private Integer userTotalGrade;

    private Integer userUseGrade;

    private Integer userVerifyState;

    private Integer userCreatorType;

    private List<BikeRentInfo> rentInfos;

    private BikeRentInfo notFinishRentInfo;

    public BigDecimal getAccountAvailableBalance() {
		return accountAvailableBalance;
	}

	public void setAccountAvailableBalance(BigDecimal accountAvailableBalance) {
		this.accountAvailableBalance = accountAvailableBalance;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	private static final long serialVersionUID = 1L;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname == null ? null : userNickname.trim();
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel == null ? null : userTel.trim();
    }

    public String getUserIdcard() {
        return userIdcard;
    }

    public void setUserIdcard(String userIdcard) {
        this.userIdcard = userIdcard == null ? null : userIdcard.trim();
    }

    public String getUserRealname() {
        return userRealname;
    }

    public void setUserRealname(String userRealname) {
        this.userRealname = userRealname == null ? null : userRealname.trim();
    }

    public String getUserProfileImage() {
        return userProfileImage;
    }

    public void setUserProfileImage(String userProfileImage) {
        this.userProfileImage = userProfileImage == null ? null : userProfileImage.trim();
    }

    public Integer getUserGender() {
        return userGender;
    }

    public void setUserGender(Integer userGender) {
        this.userGender = userGender;
    }

    public String getUserOpenid() {
        return userOpenid;
    }

    public void setUserOpenid(String userOpenid) {
        this.userOpenid = userOpenid == null ? null : userOpenid.trim();
    }

    public String getUserUnionid() {
        return userUnionid;
    }

    public void setUserUnionid(String userUnionid) {
        this.userUnionid = userUnionid == null ? null : userUnionid.trim();
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public Date getUserCreatetime() {
        return userCreatetime;
    }

    public void setUserCreatetime(Date userCreatetime) {
        this.userCreatetime = userCreatetime;
    }

    public Date getUserLogintime() {
        return userLogintime;
    }

    public void setUserLogintime(Date userLogintime) {
        this.userLogintime = userLogintime;
    }

    public Integer getUserUsetime() {
        return userUsetime;
    }

    public void setUserUsetime(Integer userUsetime) {
        this.userUsetime = userUsetime;
    }

    public Integer getUserUseDistance() {
        return userUseDistance;
    }

    public void setUserUseDistance(Integer userUseDistance) {
        this.userUseDistance = userUseDistance;
    }

    public Long getUserChannelId() {
        return userChannelId;
    }

    public void setUserChannelId(Long userChannelId) {
        this.userChannelId = userChannelId;
    }

    public Short getUserIsblacklist() {
        return userIsblacklist;
    }

    public void setUserIsblacklist(Short userIsblacklist) {
        this.userIsblacklist = userIsblacklist;
    }

    public Date getUserDefriendTime() {
        return userDefriendTime;
    }

    public void setUserDefriendTime(Date userDefriendTime) {
        this.userDefriendTime = userDefriendTime;
    }

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public Long getDefriendAdminId() {
		return defriendAdminId;
	}

	public void setDefriendAdminId(Long defriendAdminId) {
		this.defriendAdminId = defriendAdminId;
	}

	public String getDefriendAdminName() {
		return defriendAdminName;
	}

	public void setDefriendAdminName(String defriendAdminName) {
		this.defriendAdminName = defriendAdminName;
	}

	public String getDefriendReason() {
		return defriendReason;
	}

	public void setDefriendReason(String defriendReason) {
		this.defriendReason = defriendReason;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public String getUserSmallOpenid() {
		return userSmallOpenid;
	}

	public void setUserSmallOpenid(String userSmallOpenid) {
		this.userSmallOpenid = userSmallOpenid;
	}
	public String getUserSessionKey() {
        return userSessionKey;
    }

    public void setUserSessionKey(String userSessionKey) {
        this.userSessionKey = userSessionKey == null ? null : userSessionKey.trim();
    }

	public Date getUserVipexpirationdate() {
		return userVipexpirationdate;
	}

	public void setUserVipexpirationdate(Date userVipexpirationdate) {
		this.userVipexpirationdate = userVipexpirationdate;
	}

	public Date getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}

	public Double getUserTotalCalorie() {
		return userTotalCalorie;
	}

	public void setUserTotalCalorie(Double userTotalCalorie) {
		this.userTotalCalorie = userTotalCalorie;
	}

	public Double getUserTotalDistance() {
		return userTotalDistance;
	}

	public void setUserTotalDistance(Double userTotalDistance) {
		this.userTotalDistance = userTotalDistance;
	}

	public Double getUserTotalRideTime() {
		return userTotalRideTime;
	}

	public void setUserTotalRideTime(Double userTotalRideTime) {
		this.userTotalRideTime = userTotalRideTime;
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public Integer getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(Integer userGrade) {
		this.userGrade = userGrade;
	}

	public Integer getUserTotalGrade() {
		return userTotalGrade;
	}

	public void setUserTotalGrade(Integer userTotalGrade) {
		this.userTotalGrade = userTotalGrade;
	}

	public Integer getUserUseGrade() {
		return userUseGrade;
	}

	public void setUserUseGrade(Integer userUseGrade) {
		this.userUseGrade = userUseGrade;
	}

	public Date getUserLastusebiketime() {
		return userLastusebiketime;
	}

	public void setUserLastusebiketime(Date userLastusebiketime) {
		this.userLastusebiketime = userLastusebiketime;
	}

	public Date getUserFistusebiketime() {
		return userFistusebiketime;
	}

	public void setUserFistusebiketime(Date userFistusebiketime) {
		this.userFistusebiketime = userFistusebiketime;
	}

	public Long getUserFistusebikeid() {
		return userFistusebikeid;
	}

	public void setUserFistusebikeid(Long userFistusebikeid) {
		this.userFistusebikeid = userFistusebikeid;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

    public Integer getUserVerifyState() {
        return userVerifyState;
    }

    public void setUserVerifyState(Integer userVerifyState) {
        this.userVerifyState = userVerifyState;
    }

    public Integer getUserCreatorType() {
        return userCreatorType;
    }

    public void setUserCreatorType(Integer userCreatorType) {
        this.userCreatorType = userCreatorType;
    }

    public List<BikeRentInfo> getRentInfos() {
        return rentInfos;
    }

    public void setRentInfos(List<BikeRentInfo> rentInfos) {
        this.rentInfos = rentInfos;
    }

    public BikeRentInfo getNotFinishRentInfo() {
        return notFinishRentInfo;
    }

    public void setNotFinishRentInfo(BikeRentInfo notFinishRentInfo) {
        this.notFinishRentInfo = notFinishRentInfo;
    }
}