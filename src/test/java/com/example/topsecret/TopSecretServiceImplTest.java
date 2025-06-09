package com.example.topsecret;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.example.topsecret.dto.PositionDto;
import com.example.topsecret.service.impl.TopSecretServiceImpl;

public class TopSecretServiceImplTest {

    private final TopSecretServiceImpl service = new TopSecretServiceImpl();

    @Test
    void getLocation_returnsExpectedPosition() {
        Map<String, Double> distances = Map.of(
                "kenobi", 538.5164807134504,
                "skywalker", 141.4213562373095,
                "sato", 509.9019513592785
        );

        PositionDto position = service.getLocation(distances);

        assertEquals(0.0, position.getX(), 0.0001);
        assertEquals(0.0, position.getY(), 0.0001);
    }

    @Test
    void getMessage_reconstructsMessage() {
        List<List<String>> messages = List.of(
                List.of("", "es", "", "mensaje", ""),
                List.of("este", "", "un", "", "secreto"),
                List.of("", "es", "", "", "secreto")
        );

        String message = service.getMessage(messages);

        assertEquals("este es un mensaje secreto", message);
    }
}