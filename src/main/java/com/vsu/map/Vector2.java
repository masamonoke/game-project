package com.vsu.map;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class Vector2 {
    public double x;
    public double y;

    public static Vector2 zero = new Vector2(0, 0);
    public static Vector2 up = new Vector2(0, 1);
    public static Vector2 down = new Vector2(0, -1);
    public static Vector2 left = new Vector2(-1, 0);
    public static Vector2 right = new Vector2(1, 0);
    public static Vector2 upRight = new Vector2(1, 1);
    public static Vector2 upLeft = new Vector2(-1, 1);
    public static Vector2 downRight = new Vector2(1, -1);
    public static Vector2 downLeft = new Vector2(-1, -1);

    public Vector2 add(double value) {
        return new Vector2(this.x + value, this.y + value);
    }

    public Vector2 add(Vector2 other) {
        return new Vector2(this.x + other.x, this.y + other.y);
    }

    public Vector2 subtract(Vector2 other) {
        return new Vector2(this.x - other.x, this.y - other.y);
    }

    public Vector2 divide(double val) {
        return new Vector2(this.x / val, this.y / val);
    }

    public Vector2 multiply(double val) {
        return new Vector2(this.x * val, this.y * val);
    }

    public void setCoords(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean lessThan(double val) {
        return this.x < val && this.y < val;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
