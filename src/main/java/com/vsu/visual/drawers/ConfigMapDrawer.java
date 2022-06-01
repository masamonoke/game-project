package com.vsu.visual.drawers;

import com.vsu.visual.ViewConfig;
import com.vsu.visual.VisualData;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfigMapDrawer extends Drawer {
    private  VisualData data;

    @Override
    public void draw() {
        for (int i = 0; i < data.getTilemap().getRowSize(); i++) {
            for (int j = 0; j < data.getTilemap().getColSize(); j++) {
                Image image = ViewConfig.getInstance().getTileTypeImageMap().get(data.getTilemap().getMatrix()[i][j].getType());
                data.getConfigCanvas().getGraphicsContext2D()
                        .drawImage(image, 600 / data.getTilemap().getRowSize() * j, 400 / data.getTilemap().getColSize() * i);
            }
        }
        data.getConfigCanvas().getGraphicsContext2D().setFill(Color.RED);
        data.getConfigCanvas().getGraphicsContext2D().fillRect(
                600 / data.getTilemap().getRowSize() * data.getCharacter().getTilePos().col,
                400 / data.getTilemap().getColSize() * data.getCharacter().getTilePos().row,
                600 / data.getTilemap().getRowSize(),400 / data.getTilemap().getColSize()
        );
        data.getConfigCanvas().getGraphicsContext2D().fill();

        data.getConfigCanvas().setLayoutX(data.getCharacterCanvas().getLayoutX()-
                data.getConfigCanvas().getWidth()/2+data.getTileSize()/2);
        data.getConfigCanvas().setLayoutY(data.getCharacterCanvas().getLayoutY()-
                data.getConfigCanvas().getHeight()/2+data.getTileSize()/2);
    }

    public void clear() {
        data.getConfigCanvas().getGraphicsContext2D().clearRect(0,0,
                data.getConfigCanvas().getWidth(),data.getConfigCanvas().getHeight());
    }
}
