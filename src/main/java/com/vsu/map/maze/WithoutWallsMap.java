package com.vsu.map.maze;

import com.vsu.map.model.Tilemap;
import com.vsu.service.TilemapService;

public class WithoutWallsMap extends MazeGenerationStrategy {

    public WithoutWallsMap() {
        super(false);
        type = MazeGenAlgorithms.WithoutWallsMap;
    }

    @Override
    public void generate(Tilemap tilemap) {
        TilemapService tilemapService = new TilemapService();
        tilemapService.fillWithWalls(tilemap);
        for (int i = 0; i < tilemap.getColSize(); i++) {
            for ( int j = 0; j < tilemap.getRowSize(); j++) {
                super.setPathTileType(tilemap.getMatrix()[j][i]);
            }
        }
    }
}
