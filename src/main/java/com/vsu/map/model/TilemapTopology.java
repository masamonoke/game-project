package com.vsu.map.model;


public interface TilemapTopology {

    public Tile north(Tile source, Tilemap tilemap);
    public Tile south(Tile source, Tilemap tilemap);
    public Tile west(Tile source, Tilemap tilemap);
    public Tile east(Tile source, Tilemap tilemap);

    public void initGrid(Tilemap tilemap, int rowCount, int columnCount);
}
