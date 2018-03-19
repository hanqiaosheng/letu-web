package org.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class BikeRepair implements Serializable {
    private Long managerId;

    private String managerName;

    private String managerTel;

    private Date managerCreatetime;

    private static final long serialVersionUID = 1L;

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    public String getManagerTel() {
        return managerTel;
    }

    public void setManagerTel(String managerTel) {
        this.managerTel = managerTel == null ? null : managerTel.trim();
    }

    public Date getManagerCreatetime() {
        return managerCreatetime;
    }

    public void setManagerCreatetime(Date managerCreatetime) {
        this.managerCreatetime = managerCreatetime;
    }
}