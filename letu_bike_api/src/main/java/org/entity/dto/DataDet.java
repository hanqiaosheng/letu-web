package org.entity.dto;

import java.io.Serializable;

public class DataDet implements Serializable {
    private Long dataDetId;

    private Long dataId;

    private String dataDetName;

    private String dataDetVal;

    private String dataDetDescription;

    private Integer dataDetDr;
    
    private UserTask userTask;//用户任务信息

    private static final long serialVersionUID = 1L;

    public Long getDataDetId() {
        return dataDetId;
    }

    public void setDataDetId(Long dataDetId) {
        this.dataDetId = dataDetId;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public String getDataDetName() {
        return dataDetName;
    }

    public void setDataDetName(String dataDetName) {
        this.dataDetName = dataDetName == null ? null : dataDetName.trim();
    }

    public String getDataDetVal() {
        return dataDetVal;
    }

    public void setDataDetVal(String dataDetVal) {
        this.dataDetVal = dataDetVal == null ? null : dataDetVal.trim();
    }

    public String getDataDetDescription() {
        return dataDetDescription;
    }

    public void setDataDetDescription(String dataDetDescription) {
        this.dataDetDescription = dataDetDescription == null ? null : dataDetDescription.trim();
    }

    public Integer getDataDetDr() {
        return dataDetDr;
    }

    public void setDataDetDr(Integer dataDetDr) {
        this.dataDetDr = dataDetDr;
    }

	public UserTask getUserTask() {
		return userTask;
	}

	public void setUserTask(UserTask userTask) {
		this.userTask = userTask;
	}
}