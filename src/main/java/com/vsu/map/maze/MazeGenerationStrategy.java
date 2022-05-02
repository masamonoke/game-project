package com.vsu.map.maze;

import com.vsu.map.model.Tilemap;
import com.vsu.map.model.Tile;
import com.vsu.map.model.TileType;
import com.vsu.map.TileTypeGenerator;
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

    public abstract void generate(Tilemap tilemap);

    public void setPathTileType(Tile tile) {
        if (isSimpleMaze) {
            tile.setType(TileType.Pavement);
        } else {
            tile.setType(tileTypeGenerator.generate(tile.row, tile.col));
        }
    }
}
