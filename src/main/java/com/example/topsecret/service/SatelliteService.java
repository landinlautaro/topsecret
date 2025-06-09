package com.example.topsecret.service;

import java.util.Collection;

import com.example.topsecret.dto.SatelliteDto;

public interface SatelliteService {
    public void save(String name, SatelliteDto data);
    public Collection<SatelliteDto> getAll();
    public boolean isReady();
}
