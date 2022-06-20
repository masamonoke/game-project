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
        data.getMainMapCanvas().getGraphicsContext2D().setFill(Color.BLACK);
        data.getMainMapCanvas().getGraphicsContext2D().fillRect(0, 0,
                data.getMainMapCanvas().getWidth(), data.getMainMapCanvas().getHeight());
        data.getMainMapCanvas().getGraphicsContext2D().fill();


        for (int i = 1; i < data.getTilemap().getRowSize() - 2; i++) {
            for (int j = 1; j < data.getTilemap().getColSize() - 2; j++) {

                if (data.getTilemap().getMatrix()[i][j].getType() != TileType.Wall) {
                    drawWalls(i, j);
                }
            }
        }

    }

    private void drawWalls(int row, int col) {
        for (int i = row - 1; i < row + 2; i++) {
            for (int j = col - 1; j < col + 2; j++) {
                Image image = ViewConfig.getInstance().getTileTypeImageMap().get(data.getTilemap().getMatrix()[i][j].getType());
                data.getMainMapCanvas().getGraphicsContext2D().drawImage(image, j * data.getTileSize(), i * data.getTileSize());
            }
        }
    }
}
