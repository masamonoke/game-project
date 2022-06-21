package com.vsu.visual.drawers;

import com.vsu.visual.ViewConfig;
import com.vsu.visual.VisualData;
import javafx.scene.paint.Color;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfigMapDrawer extends Drawer {
    private  VisualData data;

    @Override
    public void draw() {

        for (int i = 0; i < data.getTilemap().getRowSize(); i++) {
            for (int j = 0; j < data.getTilemap().getColSize(); j++) {
                data.getConfigCanvas().getGraphicsContext2D().setFill(ViewConfig.getInstance().
                        getTileTypeColorMap().get(data.getTilemap().getMatrix()[i][j].getType()));
                data.getConfigCanvas().getGraphicsContext2D().fillRect(
                        data.getConfigCanvas().getWidth()/data.getTilemap().getRowSize()*j,
                        data.getConfigCanvas().getHeight() /data.getTilemap().getColSize()*i,
                        data.getConfigCanvas().getWidth()/data.getTilemap().getRowSize(),
                        data.getConfigCanvas().getHeight() /data.getTilemap().getColSize());
                data.getConfigCanvas().getGraphicsContext2D().fill();
            }
        }
        data.getConfigCanvas().getGraphicsContext2D().setFill(Color.RED);
        data.getConfigCanvas().getGraphicsContext2D().fillRect(
                data.getConfigCanvas().getWidth() / data.getTilemap().getRowSize() * data.getCharacter().getTilePos().col,
                data.getConfigCanvas().getHeight() / data.getTilemap().getColSize() * data.getCharacter().getTilePos().row,
                data.getConfigCanvas().getWidth() / data.getTilemap().getRowSize(),
                data.getConfigCanvas().getHeight() / data.getTilemap().getColSize()
        );
        data.getConfigCanvas().getGraphicsContext2D().fill();

        data.getConfigCanvas().setLayoutX(data.getCharacterCanvas().getLayoutX()-data.getWindowWidth()/4);
        data.getConfigCanvas().setLayoutY(data.getCharacterCanvas().getLayoutY()-data.getWindowHeight()/4);
    }

    public void clear() {
        data.getConfigCanvas().getGraphicsContext2D().clearRect(0,0,
                data.getConfigCanvas().getWidth(),data.getConfigCanvas().getHeight());
    }
}
