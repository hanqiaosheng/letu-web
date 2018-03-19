package org.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class GradeRecord implements Serializable {
    private Long gradeId;

    private String gradeRemark;

    private Integer gradeCount;

    private Long gradeUserId;

    private Date gradeCreateTime;

    private Integer gradeState;

    private static final long serialVersionUID = 1L;

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeRemark() {
        return gradeRemark;
    }

    public void setGradeRemark(String gradeRemark) {
        this.gradeRemark = gradeRemark == null ? null : gradeRemark.trim();
    }

    public Integer getGradeCount() {
        return gradeCount;
    }

    public void setGradeCount(Integer gradeCount) {
        this.gradeCount = gradeCount;
    }

    public Long getGradeUserId() {
        return gradeUserId;
    }

    public void setGradeUserId(Long gradeUserId) {
        this.gradeUserId = gradeUserId;
    }

    public Date getGradeCreateTime() {
        return gradeCreateTime;
    }

    public void setGradeCreateTime(Date gradeCreateTime) {
        this.gradeCreateTime = gradeCreateTime;
    }

    public Integer getGradeState() {
        return gradeState;
    }

    public void setGradeState(Integer gradeState) {
        this.gradeState = gradeState;
    }
}