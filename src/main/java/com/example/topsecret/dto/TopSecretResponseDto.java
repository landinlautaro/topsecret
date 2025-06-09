package com.example.topsecret.dto;

public class TopSecretResponseDto {
    private PositionDto position;
    private String message;

    public TopSecretResponseDto() {
    }

    public TopSecretResponseDto(PositionDto position, String message) {
        this.position = position;
        this.message = message;
    }

    public PositionDto getPosition() {
        return position;
    }

    public void setPosition(PositionDto position) {
        this.position = position;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
