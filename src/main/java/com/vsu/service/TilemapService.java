package com.vsu.service;

import com.vsu.map.maze.MazeGenerationStrategy;
import com.vsu.map.model.Tilemap;
import com.vsu.map.model.Tile;
import com.vsu.map.model.TileType;
import com.vsu.map.model.TilemapTopology;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.vsu.map.model.TilemapDirection2D.*;

public class TilemapService {

    public void initGrid(Tilemap tilemap, int rowCount, int columnCount, TilemapTopology archetype) {
        archetype.initGrid(tilemap, rowCount, columnCount);
    }

    public void generateMaze(Tilemap tilemap, MazeGenerationStrategy strategy) {
        strategy.generate(tilemap);
    }

    public void fillWithPavements(Tilemap tilemap) {
        for (int y = 0; y < tilemap.getColSize(); y++) {
            for (int x = 0; x < tilemap.getRowSize(); x++) {
                tilemap.getMatrix()[x][y].setType(TileType.Pavement);
                tilemap.getMatrix()[x][y].setRoot(false);
                tilemap.getMatrix()[x][y].setDest(false);
                tilemap.getMatrix()[x][y].setPath(false);
            }
        }
    }

    public void fillWithWalls(Tilemap tilemap) {
        for (int i = 0; i < tilemap.getRowSize(); i++) {
            for (int j = 0; j < tilemap.getColSize(); j++) {
                Tile tile = tilemap.getMatrix()[i][j];
                if (tile.getType() == TileType.Room) {
                    continue;
                }
                tile.setType(TileType.Wall);
            }
        }
    }

    public void genMazeByLayout(Tilemap tilemap, HashSet<Position> layout) {
        int i = 0;
        for (Position pos : layout) {
            i++;
            tilemap.getMatrix()[pos.row][pos.col].setType(TileType.Pavement);
        }
    }


    public List<Tile> getNeighbours(Tilemap tilemap, Tile tile) {
        Tile tmp;
        List<Tile> neighbours = new ArrayList<>();

        tmp = tile.north(tilemap);
        if (tmp != null) {
            neighbours.add(tmp);
        }
        tmp = tile.south(tilemap);
        if (tmp != null) {
            neighbours.add(tmp);
        }
        tmp = tile.west(tilemap);
        if (tmp != null) {
            neighbours.add(tmp);
        }
        tmp = tile.east(tilemap);
        if (tmp != null) {
            neighbours.add(tmp);
        }

        return neighbours;
    }
}
