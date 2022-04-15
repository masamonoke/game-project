package com.vsu.service;

import com.vsu.model.Character;
import com.vsu.model.Direction2D;
import com.vsu.model.Tile;
import com.vsu.model.TileType;
import com.vsu.model.grid.Grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.vsu.model.Direction2D.*;

//TODO: implement
public class GameService {
    public void initGame(Grid grid) {
    }

    public Character initCharacter(Grid grid) {
        List<Tile> validTiles = new ArrayList<>();
        for (int i = 0; i < grid.getRowSize(); i++) {
            for (int j = 0; j < grid.getColSize(); j++) {
                if (!grid.getMatrix()[i][j].getType().equals(TileType.Wall)) {
                    validTiles.add(grid.getMatrix()[i][j]);
                }
            }
        }
        Tile t = validTiles.get(new Random().nextInt(validTiles.size() - 1));
        return new Character("Lolek", new Position(t.row, t.col));
    }
}