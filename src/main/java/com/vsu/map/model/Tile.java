package com.vsu.map.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Tile {
    @EqualsAndHashCode.Exclude final TilemapTopology archetype;
    @Getter
    TileType type;
    @Getter @Setter
    int weight;
    public int row;
    public int col;
    @Getter @Setter
    @EqualsAndHashCode.Exclude boolean isPath;
    @Getter @Setter
    boolean isRoot;
    @Getter @Setter
    boolean isDest;



    public Tile(int row, int col, TilemapTopology archetype, int weight) {
        this.row = row;
        this.col = col;
        this.archetype = archetype;
        type = TileType.Pavement;
        this.weight = weight;
        isPath = false;
    }

    public Tile north(Tilemap tilemap) {
        return archetype.north(this, tilemap);
    }

    public Tile south(Tilemap tilemap) {
        return archetype.south(this, tilemap);
    }

    public Tile west(Tilemap tilemap) {
        return archetype.west(this, tilemap);
    }

    public Tile east(Tilemap tilemap) {
        return archetype.east(this, tilemap);
    }

    @Override
    public String toString() {
        return String.format("%s - (row: %d, col: %d) weight: %d", type, row, col, weight);
    }

    public void setType(TileType type) {
        this.type = type;
        weight = type.ordinal();
    }
}
