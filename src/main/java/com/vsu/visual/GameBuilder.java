package com.vsu.visual;

import com.vsu.model.Character;
import com.vsu.model.Tile;
import com.vsu.model.TileType;

import java.util.Arrays;

import static com.vsu.model.Direction2D.*;

public class GameBuilder {
    public void initGame(VisualData data) {
        initCharacter(data);
    }

    private void initCharacter(VisualData data) {
        Tile t = Arrays.stream(data.getGrid()
                        .getMatrix()[0]).filter(tile -> !tile.getType().equals(TileType.Wall)).findFirst()
                .orElseThrow(null);
        data.setCharacter(new Character("Lolek", new Position(t.row, t.col)));
    }
}
