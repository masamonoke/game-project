package com.vsu.map.maze;

import com.vsu.map.maze.dungeon.RoomGenerator;
import com.vsu.map.maze.dungeon.parameters.DungeonGenParameters;
import com.vsu.map.model.Tilemap;
import com.vsu.map.TilemapService;

import java.util.HashSet;

import static com.vsu.map.model.TilemapDirection2D.*;

public class RandomWalkStrategy extends MazeGenerationStrategy {

    public RandomWalkStrategy() {
        super(false);
        type = MazeGenAlgorithms.RandomWalk;
    }

    @Override
    public void generate(Tilemap tilemap) {
        TilemapService tilemapService = new TilemapService();
        tilemapService.fillWithWalls(tilemap);

        DungeonGenParameters parameters = DungeonGenParameters.builder()
                .randomWalkRooms(true)
                .minRoomWidth(13)
                .minRoomHeight(13)
                .startRandomlyEachIteration(true)
                .iterations(30)
                .offset(2)
                .walkLength(50)
                .build();

        HashSet<Position> layout = new RoomGenerator(new Position(0, 0),
                tilemap.getRowSize() - 1, tilemap.getColSize() - 1, parameters).createRooms();

        genMazeByLayout(tilemap, layout);
    }

    private void genMazeByLayout(Tilemap tilemap, HashSet<Position> layout) {
        int i = 0;
        for (Position pos : layout) {
            i++;
            super.setPathTileType(tilemap.getMatrix()[pos.row][pos.col]);
        }
    }
}
