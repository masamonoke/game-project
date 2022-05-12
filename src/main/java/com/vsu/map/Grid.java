package com.vsu.map;

public class Grid {
    private final double[][] matrix;

    public Grid(int width, int height) {
        matrix = new double[height][width];
    }

    public int width() {
        return matrix[0].length;
    }

    public int height() {
        return matrix.length;
    }
}
