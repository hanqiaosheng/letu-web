package org.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class HotWord implements Serializable {
    private Long hotWordId;

    private String hotWordName;

    private Integer hotWordState;

    private Integer hotWordTopNum;

    private String hotWordBrief;

    private Date hotWordCreateTime;

    private Date hotWordUpdateTime;

    private static final long serialVersionUID = 1L;

    public Long getHotWordId() {
        return hotWordId;
    }

    public void setHotWordId(Long hotWordId) {
        this.hotWordId = hotWordId;
    }

    public String getHotWordName() {
        return hotWordName;
    }

    public void setHotWordName(String hotWordName) {
        this.hotWordName = hotWordName == null ? null : hotWordName.trim();
    }

    public Integer getHotWordState() {
        return hotWordState;
    }

    public void setHotWordState(Integer hotWordState) {
        this.hotWordState = hotWordState;
    }

    public Integer getHotWordTopNum() {
        return hotWordTopNum;
    }

    public void setHotWordTopNum(Integer hotWordTopNum) {
        this.hotWordTopNum = hotWordTopNum;
    }

    public String getHotWordBrief() {
        return hotWordBrief;
    }

    public void setHotWordBrief(String hotWordBrief) {
        this.hotWordBrief = hotWordBrief == null ? null : hotWordBrief.trim();
    }

    public Date getHotWordCreateTime() {
        return hotWordCreateTime;
    }

    public void setHotWordCreateTime(Date hotWordCreateTime) {
        this.hotWordCreateTime = hotWordCreateTime;
    }

    public Date getHotWordUpdateTime() {
        return hotWordUpdateTime;
    }

    public void setHotWordUpdateTime(Date hotWordUpdateTime) {
        this.hotWordUpdateTime = hotWordUpdateTime;
    }
}