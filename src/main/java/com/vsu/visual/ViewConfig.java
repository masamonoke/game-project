package com.vsu.visual;

import com.vsu.model.TileType;
import javafx.scene.paint.Color;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ViewConfig {
    @Getter
    private static final ViewConfig INSTANCE = new ViewConfig();
    private Map<TileType, String> tileTypeImagePathMap;
    //тестовый мап
    private Map<TileType, Color> tileTypeColorMap;
    private final int rowCount;
    private final int colCount;
    private final int width;
    private final int height;

    private ViewConfig() {
        //TODO: замапить картинки с типом тайлов
        tileTypeImagePathMap = new HashMap<>();

        tileTypeColorMap = new HashMap<>();
        tileTypeColorMap.put(TileType.Room, Color.PALEGOLDENROD);
        tileTypeColorMap.put(TileType.Lake, Color.CADETBLUE);
        tileTypeColorMap.put(TileType.Wall, Color.BLACK);
        tileTypeColorMap.put(TileType.Pavement, Color.WHITE);
        tileTypeColorMap.put(TileType.Swamp, Color.PALEVIOLETRED);
        tileTypeColorMap.put(TileType.Forest, Color.GREENYELLOW);

        rowCount = 39;
        colCount = 39;

        //TODO: Это размеры сцены? (С)
        width = 700;
        height = 600;
    }
}
