package org.entity.dto;

import java.io.Serializable;

public class Data implements Serializable {
    private Long dataId;

    private String dataName;

    private String dataDescription;

    private static final long serialVersionUID = 1L;

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName == null ? null : dataName.trim();
    }

    public String getDataDescription() {
        return dataDescription;
    }

    public void setDataDescription(String dataDescription) {
        this.dataDescription = dataDescription == null ? null : dataDescription.trim();
    }
}