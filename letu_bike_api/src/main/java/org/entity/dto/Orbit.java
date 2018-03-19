package org.entity.dto;

import java.io.Serializable;

public class Orbit implements Serializable {
    private Long orbitId;

    private Long orbitRentInfoId;

    private String orbitDistance;

    private String orbitLatlngJson;

    private static final long serialVersionUID = 1L;

    public Long getOrbitId() {
        return orbitId;
    }

    public void setOrbitId(Long orbitId) {
        this.orbitId = orbitId;
    }

    public Long getOrbitRentInfoId() {
        return orbitRentInfoId;
    }

    public void setOrbitRentInfoId(Long orbitRentInfoId) {
        this.orbitRentInfoId = orbitRentInfoId;
    }

    public String getOrbitDistance() {
        return orbitDistance;
    }

    public void setOrbitDistance(String orbitDistance) {
        this.orbitDistance = orbitDistance == null ? null : orbitDistance.trim();
    }

    public String getOrbitLatlngJson() {
        return orbitLatlngJson;
    }

    public void setOrbitLatlngJson(String orbitLatlngJson) {
        this.orbitLatlngJson = orbitLatlngJson == null ? null : orbitLatlngJson.trim();
    }
}