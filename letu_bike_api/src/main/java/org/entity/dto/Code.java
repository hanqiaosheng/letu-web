package org.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class Code implements Serializable {
    private Long codeId;

    private String codePhone;

    private String codeNum;

    private Integer codeState;

    private Integer codeType;

    private Date codeCreatetime;

    private String codeIp;

    private static final long serialVersionUID = 1L;

    public Long getCodeId() {
        return codeId;
    }

    public void setCodeId(Long codeId) {
        this.codeId = codeId;
    }

    public String getCodePhone() {
        return codePhone;
    }

    public void setCodePhone(String codePhone) {
        this.codePhone = codePhone == null ? null : codePhone.trim();
    }

    public String getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(String codeNum) {
        this.codeNum = codeNum;
    }

    public Integer getCodeState() {
        return codeState;
    }

    public void setCodeState(Integer codeState) {
        this.codeState = codeState;
    }

    public Integer getCodeType() {
        return codeType;
    }

    public void setCodeType(Integer codeType) {
        this.codeType = codeType;
    }

    public Date getCodeCreatetime() {
        return codeCreatetime;
    }

    public void setCodeCreatetime(Date codeCreatetime) {
        this.codeCreatetime = codeCreatetime;
    }

    public String getCodeIp() {
        return codeIp;
    }

    public void setCodeIp(String codeIp) {
        this.codeIp = codeIp == null ? null : codeIp.trim();
    }
}