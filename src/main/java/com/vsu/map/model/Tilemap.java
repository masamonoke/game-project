package com.vsu.map.model;


import lombok.Getter;
import lombok.Setter;

@Getter
public class Tilemap {
    @Setter
    int rowSize;
    @Setter
    int colSize;

    Tile[][] matrix;

    public Tilemap(int rowSize, int colSize) {
        this.rowSize = rowSize;
        this.colSize = colSize;
        matrix = new Tile[rowSize][colSize];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = new Tile(i, j, new PlaneTopology(), 1);
            }
        }
    }

    public void resize(int rowSize, int colSize) {
        matrix = new Tile[rowSize][colSize];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = new Tile(i, j, new PlaneTopology(), 1);
            }
        }
    }
}
