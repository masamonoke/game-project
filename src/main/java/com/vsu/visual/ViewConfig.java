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
    private Map<TileType, Image> tileTypeImageMap;
    //тестовый мап
    private Map<TileType, Color> tileTypeColorMap;
    private final ImageCache imageCache;
    private final int mapRowCount;
    private final int mapColCount;
    private ViewConfig() {
        this.imageCache = new ImageCache();

        tileTypeImageMap = new HashMap<>();
        tileTypeImageMap.put(TileType.Room, this.imageCache.getImageByPath("/img/underground/room.png"));
        tileTypeImageMap.put(TileType.Lake, this.imageCache.getImageByPath("/img/underground/lake.png"));
        tileTypeImageMap.put(TileType.Wall, this.imageCache.getImageByPath("/img/underground/wall.png"));
        tileTypeImageMap.put(TileType.Pavement, this.imageCache.getImageByPath("/img/underground/pavement.png"));
        tileTypeImageMap.put(TileType.Swamp, this.imageCache.getImageByPath("/img/underground/swamp.png"));
        tileTypeImageMap.put(TileType.Forest, this.imageCache.getImageByPath("/img/underground/forest.png"));

        tileTypeColorMap = new HashMap<>();
        tileTypeColorMap.put(TileType.Room, Color.PALEGOLDENROD);
        tileTypeColorMap.put(TileType.Lake, Color.CADETBLUE);
        tileTypeColorMap.put(TileType.Wall, Color.BLACK);
        tileTypeColorMap.put(TileType.Pavement, Color.WHITE);
        tileTypeColorMap.put(TileType.Swamp, Color.PALEVIOLETRED);
        tileTypeColorMap.put(TileType.Forest, Color.GREENYELLOW);

        //TODO:Зачем ограничивать?(C)
        //TODO: Вообще  этот блок лучше перенести в data и размер окна и размеры карты можно менять (С)
        mapRowCount = 99;
        mapColCount = 99;
    }
}
