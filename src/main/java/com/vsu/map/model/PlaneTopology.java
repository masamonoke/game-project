package com.vsu.map.model;

public class PlaneTopology implements TilemapTopology {
    @Override
    public Tile north(Tile source, Tilemap tilemap) {
        return source.row - 1 < 0 ? null : tilemap.getMatrix()[source.row - 1][source.col];
    }

    @Override
    public Tile south(Tile source, Tilemap tilemap) {
        return source.row + 1 >= tilemap.getMatrix().length ? null : tilemap.getMatrix()[source.row + 1][source.col];
    }

    @Override
    public Tile west(Tile source, Tilemap tilemap) {
        return source.col - 1 < 0 ? null : tilemap.getMatrix()[source.row][source.col - 1];
    }

    @Override
    public Tile east(Tile source, Tilemap tilemap) {
        return source.col + 1 >= tilemap.getMatrix()[0].length ? null : tilemap.getMatrix()[source.row][source.col + 1];
    }

    @Override
    public void initGrid(Tilemap tilemap, int rowCount, int columnCount) {
        tilemap.resize(rowCount, columnCount);
        tilemap.setRowSize(rowCount);
        tilemap.setColSize(columnCount);
        for (int i = 0; i < tilemap.getRowSize(); i++) {
            for (int j = 0; j < tilemap.getColSize(); j++) {
                tilemap.getMatrix()[i][j] = new Tile(i, j, new PlaneTopology(), 0);
            }
        }
    }
}
