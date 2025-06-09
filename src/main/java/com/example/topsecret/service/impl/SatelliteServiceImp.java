package com.example.topsecret.service.impl;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.example.topsecret.dto.SatelliteDto;
import com.example.topsecret.service.SatelliteService;

@Service
public class SatelliteServiceImp implements SatelliteService {
    private final Map<String, SatelliteDto> satelliteMap = new ConcurrentHashMap<>();

    public void save(String name, SatelliteDto data) {
        satelliteMap.put(name.toLowerCase(), data);
    }

    public Collection<SatelliteDto> getAll() {
        return satelliteMap.values();
    }

    public boolean isReady() {
        return satelliteMap.size() >= 3;
    }
}

