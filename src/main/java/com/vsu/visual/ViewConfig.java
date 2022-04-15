package com.vsu.visual;

import com.vsu.model.TileType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ViewConfig {
    @Getter
    private static final ViewConfig INSTANCE = new ViewConfig();
    private Map<TileType, Image> tileTypeImagePathMap;
    //тестовый мап
    private Map<TileType, Color> tileTypeColorMap;
    private final ImageCache imageCache;
    private final int rowCount;
    private final int colCount;
    private final int windowWidth;
    private final int windowHeight;

    private ViewConfig() {
        this.imageCache = new ImageCache();

        tileTypeImagePathMap = new HashMap<>();
        tileTypeImagePathMap.put(TileType.Room, this.imageCache.getImageByPath("/img/underground/room.png"));
        tileTypeImagePathMap.put(TileType.Lake, this.imageCache.getImageByPath("/img/underground/lake.png"));
        tileTypeImagePathMap.put(TileType.Wall, this.imageCache.getImageByPath("/img/underground/wall.png"));
        tileTypeImagePathMap.put(TileType.Pavement, this.imageCache.getImageByPath("/img/underground/pavement.png"));
        tileTypeImagePathMap.put(TileType.Swamp, this.imageCache.getImageByPath("/img/underground/swamp.png"));
        tileTypeImagePathMap.put(TileType.Forest, this.imageCache.getImageByPath("/img/underground/forest.png"));


        tileTypeColorMap = new HashMap<>();
        tileTypeColorMap.put(TileType.Room, Color.PALEGOLDENROD);
        tileTypeColorMap.put(TileType.Lake, Color.CADETBLUE);
        tileTypeColorMap.put(TileType.Wall, Color.BLACK);
        tileTypeColorMap.put(TileType.Pavement, Color.WHITE);
        tileTypeColorMap.put(TileType.Swamp, Color.PALEVIOLETRED);
        tileTypeColorMap.put(TileType.Forest, Color.GREENYELLOW);

        rowCount = 39;
        colCount = 39;

        windowWidth = 700;
        windowHeight = 600;
    }
}
