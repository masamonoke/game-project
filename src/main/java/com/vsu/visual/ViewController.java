package com.vsu.visual;

import com.vsu.map.maze.MazeGenAlgorithms;
import com.vsu.map.maze.MazeGenerationFactory;
import com.vsu.map.maze.MazeGenerationStrategy;
import com.vsu.map.model.TileType;
import com.vsu.map.model.Tilemap;
import com.vsu.service.TilemapService;

public class ViewController {

    public void generateMaze(MazeGenAlgorithms algorithm, Tilemap tilemap) {
        MazeGenerationFactory mazeGenerationFactory = new MazeGenerationFactory();
        MazeGenerationStrategy strategy = mazeGenerationFactory.getStrategy(algorithm);
        TilemapService tilemapService = new TilemapService();
        tilemapService.generateMaze(tilemap, strategy);
    }

    public void fillWithPavements(Tilemap tilemap) {
        for (int i = 0; i < tilemap.getRowSize(); i++) {
            for (int j = 0; j < tilemap.getColSize(); j++) {
                tilemap.getMatrix()[i][j].setType(TileType.Pavement);
            }
        }
    }

}
