package com.vsu.visual;

import com.vsu.maze_generation.MazeGenAlgorithms;
import com.vsu.maze_generation.MazeGenerationFactory;
import com.vsu.maze_generation.MazeGenerationStrategy;
import com.vsu.model.TileType;
import com.vsu.model.grid.Grid;
import com.vsu.service.GridService;

public class ViewController {

    public void generateMaze(MazeGenAlgorithms algorithm, Grid grid) {
        MazeGenerationFactory mazeGenerationFactory = new MazeGenerationFactory();
        MazeGenerationStrategy strategy = mazeGenerationFactory.getStrategy(algorithm);
        GridService gridService = new GridService();
        gridService.generateMaze(grid, strategy);
    }

    public void fillWithPavements(Grid grid) {
        for (int i = 0; i < grid.getRowSize(); i++) {
            for (int j = 0; j < grid.getColSize(); j++) {
                grid.getMatrix()[i][j].setType(TileType.Pavement);
            }
        }
    }

}
