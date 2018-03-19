package org.entity.dto;

import java.io.Serializable;

public class Idcard implements Serializable {
    private Long idcardId;

    private Long idcardUserId;

    private Integer idcardCheckNum;

    private static final long serialVersionUID = 1L;

    public Long getIdcardId() {
        return idcardId;
    }

    public void setIdcardId(Long idcardId) {
        this.idcardId = idcardId;
    }

    public Long getIdcardUserId() {
        return idcardUserId;
    }

    public void setIdcardUserId(Long idcardUserId) {
        this.idcardUserId = idcardUserId;
    }

    public Integer getIdcardCheckNum() {
        return idcardCheckNum;
    }

    public void setIdcardCheckNum(Integer idcardCheckNum) {
        this.idcardCheckNum = idcardCheckNum;
    }
}