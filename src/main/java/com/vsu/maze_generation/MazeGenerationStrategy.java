package com.vsu.maze_generation;

import com.vsu.model.grid.Grid;
import com.vsu.model.Tile;
import com.vsu.model.TileType;
import com.vsu.service.TileTypeGenerator;
import lombok.Getter;

@Getter
public abstract class MazeGenerationStrategy {

    MazeGenAlgorithms type;
    boolean isSimpleMaze;
    TileTypeGenerator tileTypeGenerator;

    public MazeGenerationStrategy(boolean isSimpleMaze) {
        this.isSimpleMaze = isSimpleMaze;
        if (!isSimpleMaze) {
            tileTypeGenerator = new TileTypeGenerator();
        }
    }

    public abstract void generate(Grid grid);

    public void setPathTileType(Tile tile) {
        if (isSimpleMaze) {
            tile.setType(TileType.Pavement);
        } else {
            tile.setType(tileTypeGenerator.generate(tile.row, tile.col));
        }
    }
}
