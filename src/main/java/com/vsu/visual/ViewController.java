package com.vsu.visual;

import com.vsu.maze_generation.MazeGenAlgorithms;
import com.vsu.maze_generation.MazeGenerationFactory;
import com.vsu.maze_generation.MazeGenerationStrategy;
import com.vsu.model.TileType;
import com.vsu.model.grid.Grid;
import com.vsu.service.GridService;

public class ViewController {
    //TODO:почему карта генерируется в визуале? (С)
    public void generateMaze(MazeGenAlgorithms algorithm, Grid grid) {
        MazeGenerationFactory mazeGenerationFactory = new MazeGenerationFactory();
        MazeGenerationStrategy strategy = mazeGenerationFactory.getStrategy(algorithm);
        GridService gridService = new GridService();
        gridService.generateMaze(grid, strategy);
    }
}
