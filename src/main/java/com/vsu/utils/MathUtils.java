package com.vsu.utils;

public class MathUtils {
    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        var factor = (long) Math.pow(10, places);
        value = value * factor;
        var tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
