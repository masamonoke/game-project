package com.vsu.map.maze;

import com.vsu.map.model.Tilemap;
import com.vsu.map.model.Tile;
import com.vsu.map.model.TileType;
import com.vsu.map.TilemapService;

import java.util.*;

public class BacktrackingStrategy extends MazeGenerationStrategy {

    public BacktrackingStrategy() {
        super(false);
        type = MazeGenAlgorithms.Backtracking;
    }

    @Override
    public void generate(Tilemap tilemap) {

        TilemapService tilemapService = new TilemapService();
        tilemapService.fillWithWalls(tilemap);

        Stack<Tile> stack = new Stack<>();
        List<Tile> neighbours = new ArrayList<>();
        Set<Tile> visited = new HashSet<>();

        Tile current = tilemap.getMatrix()[0][0];
        stack.push(current);
        visited.add(current);

        while (!stack.isEmpty()) {
            getNeighbours(tilemap, current, neighbours);
            neighbours.removeIf(visited::contains);
            if (neighbours.isEmpty()) {
                current = stack.pop();
                continue;
            }

            Tile randomNeighbour = neighbours.get((int) (Math.random() * neighbours.size()));

            if (randomNeighbour.getType() != TileType.Room) {
                super.setPathTileType(randomNeighbour);
                removeWallBetween(tilemap, current, randomNeighbour);
                current = randomNeighbour;
                stack.push(randomNeighbour);
            }

            visited.add(randomNeighbour);

        }
    }

    private void getNeighbours(Tilemap tilemap, Tile current, List<Tile> neighbours) {
        neighbours.clear();
        Tile tmp;

        tmp = current.north(tilemap);
        if (tmp != null) {
            neighbours.add(tmp.row % 2 != 0 ? tilemap.getMatrix()[tmp.row - 1][tmp.col] : tmp);
        }

        tmp = current.south(tilemap);
        if (tmp != null) {
            neighbours.add(tmp.row % 2 != 0 ? tilemap.getMatrix()[tmp.row + 1][tmp.col] : tmp);
        }

        tmp = current.west(tilemap);
        if (tmp != null) {
            neighbours.add(tmp.col % 2 != 0 ? tilemap.getMatrix()[tmp.row][tmp.col - 1] : tmp);
        }

        tmp = current.east(tilemap);
        if (tmp != null) {
            neighbours.add(tmp.col % 2 != 0 ? tilemap.getMatrix()[tmp.row][tmp.col + 1] : tmp);
        }
    }

    private void removeWallBetween(Tilemap tilemap, Tile a, Tile b) {
        int i = a.row;
        int j = a.col;

        if (a.row < b.row) {
            i++;
        } else if (a.col < b.col) {
            j++;
        } else if (a.row > b.row) {
            i--;
        } else if (a.col > b.col) {
            j--;
        }

        Tile tile = tilemap.getMatrix()[i][j];
        if (tile.getType() == TileType.Room) {
            return;
        }

        super.setPathTileType(tile);
    }
}
