package com.example.topsecret.dto;

public class PositionDto {
    private double x;
    private double y;

    public PositionDto() {
    }

    public PositionDto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
