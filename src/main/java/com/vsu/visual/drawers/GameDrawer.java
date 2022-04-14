package com.vsu.visual.drawers;

import com.vsu.model.TileType;
import com.vsu.model.grid.Grid;
import com.vsu.visual.VisualData;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GameDrawer implements Drawer {
    Grid grid;
    VisualData data;

    //TODO: 48 скорее всего размер картинки
    public void draw() {
        Canvas canvas = new Canvas(grid.getColSize() * 48, grid.getRowSize() * 48);
        for (int i = 0; i < grid.getRowSize(); i++) {
            for (int j = 0; j < grid.getColSize(); j++) {
                if (grid.getMatrix()[i][j].getType().equals(TileType.Wall)) {
                    canvas.getGraphicsContext2D()
                            .drawImage(data.getImageCache().getImageByPath("/img/underground/block.png"),
                                    i * 48, j * 48);
                }
                if (grid.getMatrix()[i][j].getType().equals(TileType.Pavement)) {
                    canvas.getGraphicsContext2D()
                            .drawImage(data.getImageCache().getImageByPath("/img/underground/none.png"),
                                    i * 48, j * 48);
                }
            }
        }
        data.setCurrentPane(new Pane(canvas));
        data.setCamera(new PerspectiveCamera());
        //TODO: 700 и 600 достать из глобального конфига
        Scene scene = new Scene(data.getCurrentPane(), 700, 600);
        scene.setCamera(data.getCamera());
        data.getStage().setScene(scene);
        data.getStage().show();
    }
}
