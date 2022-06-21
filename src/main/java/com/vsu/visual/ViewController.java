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
}
