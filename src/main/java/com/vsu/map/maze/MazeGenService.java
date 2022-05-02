package com.vsu.map.maze;

import static com.vsu.map.model.TilemapDirection2D.*;

public class MazeGenService {
    public static Position getVector2ByDirection(Position pos, Position direction, int rowCount, int colCount) {
        int row = pos.row + direction.row;
        int col = pos.col + direction.col;

        if (row < 0)
            row = 0;
        if (col < 0)
            col = 0;

        if (row >= rowCount)
            row--;
        if (col >= colCount)
            col--;

        return new Position(row, col);
    }
}
