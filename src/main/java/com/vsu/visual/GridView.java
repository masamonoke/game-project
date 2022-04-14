package com.vsu.visual;

import com.vsu.model.grid.Grid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GridView {
    private Grid grid;
    private TileView[][] matrix;

    public GridView(Grid grid) {
        this.grid = grid;
        matrix = new TileView[grid.getRowSize()][grid.getColSize()];
    }
}
