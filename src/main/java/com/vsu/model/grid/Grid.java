package com.vsu.model.grid;


import com.vsu.model.Tile;
import com.vsu.model.grid.PlaneTopology;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
public class Grid {
    @Setter
    int rowSize;
    @Setter
    int colSize;

    Tile[][] matrix;

    public Grid(int rowSize, int colSize) {
        this.rowSize = rowSize;
        this.colSize = colSize;
        matrix = new Tile[rowSize][colSize];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = new Tile(i, j, new PlaneTopology(), 1);
            }
        }
    }

    public void setMatrix(int rowSize, int colSize) {
        matrix = new Tile[rowSize][colSize];
    }
}
