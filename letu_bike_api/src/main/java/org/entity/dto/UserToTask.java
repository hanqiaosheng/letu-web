package org.entity.dto;

import java.io.Serializable;

public class UserToTask implements Serializable {
    private Long userToTaskId;

    private Long userId;

    private Long dataDetId;

    private Integer userToTaskState;

    private static final long serialVersionUID = 1L;

    public Long getUserToTaskId() {
        return userToTaskId;
    }

    public void setUserToTaskId(Long userToTaskId) {
        this.userToTaskId = userToTaskId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDataDetId() {
        return dataDetId;
    }

    public void setDataDetId(Long dataDetId) {
        this.dataDetId = dataDetId;
    }

    public Integer getUserToTaskState() {
        return userToTaskState;
    }

    public void setUserToTaskState(Integer userToTaskState) {
        this.userToTaskState = userToTaskState;
    }
}