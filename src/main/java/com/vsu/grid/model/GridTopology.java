package com.vsu.grid.model;


public interface GridTopology {

    public Tile north(Tile source, Grid grid);
    public Tile south(Tile source, Grid grid);
    public Tile west(Tile source, Grid grid);
    public Tile east(Tile source, Grid grid);

    public void initGrid(Grid grid, int rowCount, int columnCount);
}
