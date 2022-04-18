package com.vsu.grid;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Vector2 {
    private double x;
    private double y;

    //TODO: возможно подредактировать сдвиги
    public static Vector2  zero = new Vector2(0, 0);
    public static Vector2 up = new Vector2(-1, 0);
    public static Vector2 down = new Vector2(1, 0);
    public static Vector2 left = new Vector2(0, -1);
    public static Vector2 right = new Vector2(0, 1);

    public Vector2 add(double value) {
        return new Vector2(this.x + value, this.y + value);
    }

    public Vector2 add(Vector2 other) {
        return new Vector2(this.x + other.x, this.y + other.y);
    }
}
