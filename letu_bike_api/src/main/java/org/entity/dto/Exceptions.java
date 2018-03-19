package org.entity.dto;

import java.io.Serializable;

public class Exceptions implements Serializable {
    private Long exceptionId;

    private Long exceptionUserId;

    private Long exceptionAdminId;

    private Integer exceptionType;

    private Integer exceptionResult;

    private String exceptionRemark;

    private Integer exceptionUserBikestate;

    private Integer exceptionBikestate;

    private static final long serialVersionUID = 1L;

    public Long getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(Long exceptionId) {
        this.exceptionId = exceptionId;
    }

    public Long getExceptionUserId() {
        return exceptionUserId;
    }

    public void setExceptionUserId(Long exceptionUserId) {
        this.exceptionUserId = exceptionUserId;
    }

    public Long getExceptionAdminId() {
        return exceptionAdminId;
    }

    public void setExceptionAdminId(Long exceptionAdminId) {
        this.exceptionAdminId = exceptionAdminId;
    }

    public Integer getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(Integer exceptionType) {
        this.exceptionType = exceptionType;
    }

    public Integer getExceptionResult() {
        return exceptionResult;
    }

    public void setExceptionResult(Integer exceptionResult) {
        this.exceptionResult = exceptionResult;
    }

    public String getExceptionRemark() {
        return exceptionRemark;
    }

    public void setExceptionRemark(String exceptionRemark) {
        this.exceptionRemark = exceptionRemark == null ? null : exceptionRemark.trim();
    }

    public Integer getExceptionUserBikestate() {
        return exceptionUserBikestate;
    }

    public void setExceptionUserBikestate(Integer exceptionUserBikestate) {
        this.exceptionUserBikestate = exceptionUserBikestate;
    }

    public Integer getExceptionBikestate() {
        return exceptionBikestate;
    }

    public void setExceptionBikestate(Integer exceptionBikestate) {
        this.exceptionBikestate = exceptionBikestate;
    }
}