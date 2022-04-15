package com.vsu.visual;

import com.vsu.model.Character;
import com.vsu.model.Tile;
import com.vsu.model.TileType;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

import static com.vsu.model.Direction2D.*;

public class GameBuilder {
    public void initGame(VisualData data) {
        initCharacter(data);
    }

    private void initCharacter(VisualData data) {
        List<Tile> validTiles = new ArrayList<>();
        for (int i = 0; i < data.getGrid().getRowSize(); i++) {
            for (int j = 0; j < data.getGrid().getColSize(); j++) {
                if (!data.getGrid().getMatrix()[i][j].getType().equals(TileType.Wall)) {
                    validTiles.add(data.getGrid().getMatrix()[i][j]);
                }
            }
        }
        Tile t = validTiles.get(new Random().nextInt(validTiles.size() - 1));
        data.setCharacter(new Character("Lolek", new Position(t.row, t.col)));
    }
}
