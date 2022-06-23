package com.vsu.map.maze;

import java.util.ArrayList;
import java.util.List;

public class MazeGenerationFactory {

    List<MazeGenerationStrategy> strategies;

    public MazeGenerationFactory() {
        strategies = new ArrayList<>();
        strategies.add(new BacktrackingStrategy());
        strategies.add(new RandomWalkStrategy());
        strategies.add(new WithoutWallsMap());
    }

    public MazeGenerationStrategy getStrategy(MazeGenAlgorithms strategy) {
        return strategies.stream().filter(s -> strategy.equals(s.getType())).findFirst().orElse(null);
    }
}
