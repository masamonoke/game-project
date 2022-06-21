package com.vsu.visual.drawers;

import com.vsu.map.model.TileType;
import com.vsu.visual.ViewConfig;
import com.vsu.visual.VisualData;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MainMapDrawer extends Drawer {
    private VisualData data;

    @Override
    public void draw() {
        int startX = (int) (data.getCharacter().getTilePos().col - (data.getWindowWidth() / (2 * data.getTileSize())));
        int startY = (int) (data.getCharacter().getTilePos().row - (data.getWindowHeight() / (2 * data.getTileSize())));
        System.out.println(data.getWindowWidth() + "+" + data.getWindowHeight());
        for (int i = -1; i < (data.getWindowWidth() / (data.getTileSize()))+1; i++) {
            for (int j = -1; j < ((data.getWindowHeight()) / (data.getTileSize()))+1; j++) {
                if ((((j + startY) >= 0) && (j + startY < ViewConfig.getInstance().getMapRowCount())) &&
                        (((i + startX) >= 0) && (i + startX < ViewConfig.getInstance().getMapColCount()))) {
                            data.getMainMapCanvas().getGraphicsContext2D().drawImage(
                                    ViewConfig.getInstance().getTileTypeImageMap().get(data.getTilemap().getMatrix()[(j + startY)][i + startX].getType())
                                    , (data.getWindowWidth() / 2 - (data.getCharacter().getTilePos().col - startX) * data.getTileSize()) + data.getTileSize() * i,
                                    (data.getWindowHeight() / 2 - (data.getCharacter().getTilePos().row - startY) * data.getTileSize()) + data.getTileSize() * j);
                        }

            }
        }
    }
}
