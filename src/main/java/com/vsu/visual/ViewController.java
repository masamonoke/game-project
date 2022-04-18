package com.vsu.visual;

import com.vsu.grid.maze.MazeGenAlgorithms;
import com.vsu.grid.maze.MazeGenerationFactory;
import com.vsu.grid.maze.MazeGenerationStrategy;
import com.vsu.grid.model.TileType;
import com.vsu.grid.model.Grid;
import com.vsu.grid.GridService;

public class ViewController {
    //TODO:почему карта генерируется в визуале? (С)
    public void generateMaze(MazeGenAlgorithms algorithm, Grid grid) {
        MazeGenerationFactory mazeGenerationFactory = new MazeGenerationFactory();
        MazeGenerationStrategy strategy = mazeGenerationFactory.getStrategy(algorithm);
        GridService gridService = new GridService();
        gridService.generateMaze(grid, strategy);
    }
}
