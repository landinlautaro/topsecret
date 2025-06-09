package com.example.topsecret.service;

import java.util.List;
import java.util.Map;

import com.example.topsecret.dto.PositionDto;
import com.example.topsecret.exception.IncompleteDataException;

public interface TopSecretService {
    PositionDto getLocation(Map<String, Double> distances) throws IncompleteDataException;
    String getMessage(List<List<String>> messages) throws IncompleteDataException;
}
