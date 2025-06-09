package com.example.topsecret.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.topsecret.dto.PositionDto;
import com.example.topsecret.exception.IncompleteDataException;
import com.example.topsecret.service.TopSecretService;

import org.springframework.stereotype.Service;

@Service
public class TopSecretServiceImpl implements TopSecretService {

    private static final Map<String, PositionDto> SATELLITE_POSITIONS = Map.of(
        "kenobi",    new PositionDto(-500.0, -200.0),
        "skywalker", new PositionDto( 100.0, -100.0),
        "sato",      new PositionDto( 500.0,  100.0)
    );

    @Override
    public PositionDto getLocation(Map<String, Double> distances) throws IncompleteDataException {
        if (distances == null || distances.size() < 3) {
            throw new IncompleteDataException("Not enough satellites");
        }

        List<String> names = new ArrayList<>(distances.keySet());
        String name1 = names.get(0);
        String name2 = names.get(1);
        String name3 = names.get(2);

        if (!SATELLITE_POSITIONS.containsKey(name1) ||
            !SATELLITE_POSITIONS.containsKey(name2) ||
            !SATELLITE_POSITIONS.containsKey(name3)) {
            throw new IncompleteDataException("Cannot determine position");
        }

        PositionDto p1 = SATELLITE_POSITIONS.get(name1);
        PositionDto p2 = SATELLITE_POSITIONS.get(name2);
        PositionDto p3 = SATELLITE_POSITIONS.get(name3);

        double d1 = distances.get(name1);
        double d2 = distances.get(name2);
        double d3 = distances.get(name3);

        double x1 = p1.getX(), y1 = p1.getY();
        double x2 = p2.getX(), y2 = p2.getY();
        double x3 = p3.getX(), y3 = p3.getY();

        double a = 2 * (x1 - x2);
        double b = 2 * (y1 - y2);
        double c = d1 * d1 - d2 * d2 + x2 * x2 - x1 * x1 + y2 * y2 - y1 * y1;

        double d = 2 * (x1 - x3);
        double e = 2 * (y1 - y3);
        double f = d1 * d1 - d3 * d3 + x3 * x3 - x1 * x1 + y3 * y3 - y1 * y1;

        double denominator = a * e - b * d;
        if (denominator == 0) {
            throw new IncompleteDataException("Cannot determine position");
        }

        double x = (c * e - b * f) / denominator;
        double y = (a * f - c * d) / denominator;

        return new PositionDto(x, y);
    }

    @Override
    public String getMessage(List<List<String>> messages) throws IncompleteDataException {
        if (messages == null || messages.isEmpty()) {
            throw new IncompleteDataException("Inconsistent message length");
        }

        int length = messages.get(0).size();
        for (List<String> msgList : messages) {
            if (msgList.size() != length) {
                throw new IncompleteDataException("Inconsistent message length");
            }
        }

        List<String> reconstructed = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            String word = "";
            for (List<String> msgList : messages) {
                String fragment = msgList.get(i);
                if (fragment != null && !fragment.isBlank()) {
                    word = fragment;
                    break;
                }
            }
            if (word.isBlank()) {
                throw new IncompleteDataException("Cannot determine message");
            }
            reconstructed.add(word);
        }

        return String.join(" ", reconstructed);
    }
}