package com.vsu.service;

import com.vsu.actor.model.Character;
import com.vsu.map.model.Tile;
import com.vsu.map.model.TileType;
import com.vsu.map.model.Tilemap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.vsu.map.model.TilemapDirection2D.*;

//TODO: implement
public class GameService {
    public void initGame(Tilemap tilemap) {}

    public Character initCharacter(Tilemap tilemap) {
        List<Tile> validTiles = new ArrayList<>();
        for (int i = 0; i < tilemap.getRowSize(); i++) {
            for (int j = 0; j < tilemap.getColSize(); j++) {
                if (!tilemap.getMatrix()[i][j].getType().equals(TileType.Wall)) {
                    validTiles.add(tilemap.getMatrix()[i][j]);
                }
            }
        }
        Tile t = validTiles.get(new Random().nextInt(validTiles.size() - 1));
        return Character.builder().name("Lolek").tilePos(new Position(t.row, t.col)).build();
    }
}
