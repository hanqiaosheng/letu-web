package org.entity.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Admin implements Serializable {
    private Long adminId;

    private Long adminChannelId;

    private String adminUsername;

    private String adminPwd;

    private String adminRealname;

    private String adminTel;

    private Date adminRegTime;

    private Integer adminState;

    private String adminSalt;

    private String adminDistrictCode;

    private Long adminCreateAdminid;

    private String adminCreateEmail;

    //身份证
    private String adminIdCard;

    //导游端小程序 用户唯一识别码
    private String openId;

    //角色列表
    private List<Role> roles;

    //渠道
    private Channel channel;


    private static final long serialVersionUID = 1L;

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getAdminChannelId() {
        return adminChannelId;
    }

    public void setAdminChannelId(Long adminChannelId) {
        this.adminChannelId = adminChannelId;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername == null ? null : adminUsername.trim();
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd == null ? null : adminPwd.trim();
    }

    public String getAdminRealname() {
        return adminRealname;
    }

    public void setAdminRealname(String adminRealname) {
        this.adminRealname = adminRealname == null ? null : adminRealname.trim();
    }

    public String getAdminTel() {
        return adminTel;
    }

    public void setAdminTel(String adminTel) {
        this.adminTel = adminTel == null ? null : adminTel.trim();
    }

    public Date getAdminRegTime() {
        return adminRegTime;
    }

    public void setAdminRegTime(Date adminRegTime) {
        this.adminRegTime = adminRegTime;
    }

    public Integer getAdminState() {
        return adminState;
    }

    public void setAdminState(Integer adminState) {
        this.adminState = adminState;
    }

    public String getAdminSalt() {
        return adminSalt;
    }

    public void setAdminSalt(String adminSalt) {
        this.adminSalt = adminSalt == null ? null : adminSalt.trim();
    }

    public String getAdminDistrictCode() {
        return adminDistrictCode;
    }

    public void setAdminDistrictCode(String adminDistrictCode) {
        this.adminDistrictCode = adminDistrictCode == null ? null : adminDistrictCode.trim();
    }

    public Long getAdminCreateAdminid() {
        return adminCreateAdminid;
    }

    public void setAdminCreateAdminid(Long adminCreateAdminid) {
        this.adminCreateAdminid = adminCreateAdminid;
    }

    public String getAdminCreateEmail() {
        return adminCreateEmail;
    }

    public void setAdminCreateEmail(String adminCreateEmail) {
        this.adminCreateEmail = adminCreateEmail == null ? null : adminCreateEmail.trim();
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }


    public String getAdminIdCard() {
        return adminIdCard;
    }

    public void setAdminIdCard(String adminIdCard) {
        this.adminIdCard = adminIdCard;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}