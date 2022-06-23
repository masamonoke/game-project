package com.vsu.utils;

public class MathUtils {
    //round(3.67, 1)=3.7, round(3.67, 0)=4.0, при значениях .5 округление происходит в большую сторону
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
