package com.example.topsecret.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.topsecret.dto.PositionDto;
import com.example.topsecret.dto.SatelliteDto;
import com.example.topsecret.dto.TopSecretRequestDto;
import com.example.topsecret.dto.TopSecretResponseDto;
import com.example.topsecret.exception.IncompleteDataException;
import com.example.topsecret.service.TopSecretService;
import com.example.topsecret.service.impl.SatelliteServiceImp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topsecret")
public class TopSecretController {

    private final TopSecretService topSecretService;
    private final SatelliteServiceImp satelliteService;

    public TopSecretController(TopSecretService topSecretService, SatelliteServiceImp satelliteService) {
        this.topSecretService = topSecretService;
        this.satelliteService = satelliteService;
    }

    @PostMapping
    public ResponseEntity<TopSecretResponseDto> getTopSecret(@RequestBody TopSecretRequestDto request) {
        try {
            Map<String, Double> distances = request.getSatellites().stream()
                    .collect(Collectors.toMap(SatelliteDto::getName, SatelliteDto::getDistance));

            List<List<String>> messages = request.getSatellites().stream()
                    .map(SatelliteDto::getMessage)
                    .collect(Collectors.toList());

            PositionDto position = topSecretService.getLocation(distances);
            String message = topSecretService.getMessage(messages);

            TopSecretResponseDto response = new TopSecretResponseDto(position, message);
            return ResponseEntity.ok(response);
        } catch (IncompleteDataException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/topsecret_split/{satellite_name}")
    public ResponseEntity<Void> saveSatellite(@PathVariable("satellite_name") String name,
            @RequestBody SatelliteDto data) {
        data.setName(name);
        satelliteService.save(name, data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/topsecret_split")
    public ResponseEntity<?> getMessageAndPosition() {
        if (!satelliteService.isReady()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Not enough information"));
        }

        List<SatelliteDto> satellites = satelliteService.getAll().stream()
                .collect(Collectors.toList());

        if (satellites.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Satellite not found"));
        }

        Map<String, Double> distances = satellites.stream()
                .collect(Collectors.toMap(SatelliteDto::getName, SatelliteDto::getDistance));

        List<List<String>> messages = satellites.stream()
                .map(SatelliteDto::getMessage)
                .toList();

        PositionDto position = topSecretService.getLocation(distances);
        String message = topSecretService.getMessage(messages);

        Map<String, Object> response = Map.of(
                "position", Map.of("x", position.getX(), "y", position.getY()),
                "message", message);

        return ResponseEntity.ok(response);
    }

}
