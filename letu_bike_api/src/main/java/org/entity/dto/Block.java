package org.entity.dto;

import java.io.Serializable;

public class Block implements Serializable {
    private Long blockId;

    private String blockCode;

    private static final long serialVersionUID = 1L;

    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    public String getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode == null ? null : blockCode.trim();
    }
}