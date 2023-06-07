package com.example.nuclear.model;

public class VectorToGrenade {

    private double x, y;

    public VectorToGrenade(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void normalize() {
        double mag = Math.sqrt(x * x + y * y);
        if (mag != 0) {
            x = x / mag;
            y = y / mag;
        }
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

    public void multiply(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }
}
