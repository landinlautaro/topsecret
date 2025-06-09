package com.example.topsecret.dto;

import java.util.List;

public class TopSecretRequestDto {
    private List<SatelliteDto> satellites;

    public TopSecretRequestDto() {
    }

    public TopSecretRequestDto(List<SatelliteDto> satellites) {
        this.satellites = satellites;
    }

    public List<SatelliteDto> getSatellites() {
        return satellites;
    }

    public void setSatellites(List<SatelliteDto> satellites) {
        this.satellites = satellites;
    }
}
