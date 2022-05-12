package com.vsu.map;

import com.vsu.utils.GeometryUtils;
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

    //TODO: test
    public Vector2 subtract(Vector2 other) {
        return new Vector2(this.x - other.x, this.y - other.y);
    }

    public void setCoords(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setCoords(Vector2 vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    public Vector2 getMiddlePointFromDistanceAndEndPoint(Vector2 cartesianVector, int distance) {
        var radian = GeometryUtils.findVectorAngleByPoint(cartesianVector);
        var destinationX = distance * Math.cos(radian) + this.x;
        var destinationY = distance * Math.sin(radian) + this.y;
        return new Vector2(destinationX, destinationY);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
