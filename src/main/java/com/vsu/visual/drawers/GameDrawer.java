package com.vsu.visual.drawers;

import com.vsu.model.TileType;
import com.vsu.visual.VisualData;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GameDrawer extends Drawer {
    VisualData data;

    //TODO: 48 скорее всего размер картинки
    //TODO: доставать картинки из ViewConfig
    @Override
    public void draw() {
        Canvas canvas = new Canvas(data.getGrid().getColSize() * data.getTileSize(),
                data.getGrid().getRowSize() * data.getTileSize());
        for (int i = 0; i < data.getGrid().getRowSize(); i++) {
            for (int j = 0; j < data.getGrid().getColSize(); j++) {
                //TODO: добавить биомов
                if (data.getGrid().getMatrix()[i][j].getType().equals(TileType.Wall)) {
                    canvas.getGraphicsContext2D()
                            .drawImage(data.getImageCache().getImageByPath("/img/underground/block.png"),
                                    i * 48, j * 48);
                }
                if (data.getGrid().getMatrix()[i][j].getType().equals(TileType.Pavement)) {
                    canvas.getGraphicsContext2D()
                            .drawImage(data.getImageCache().getImageByPath("/img/underground/exit.png"),
                                    i * data.getTileSize(), j * data.getTileSize());
                }
            }
        }
        data.setCurrentPane(new Pane(canvas));
        data.setCamera(new PerspectiveCamera());
        //TODO: 700 и 600 достать из глобального конфига
        Canvas charCanvas = new Canvas(50, 50);
        data.setCharacterCanvas(charCanvas);
        data.getCurrentPane().getChildren().add(charCanvas);
        CharacterDrawer drawer = new CharacterDrawer(data, data.getCharacter());
        drawer.draw();

        Scene scene = new Scene(data.getCurrentPane(), 700, 600);
        scene.setCamera(data.getCamera());
        data.getStage().setScene(scene);
        data.getStage().show();
    }
}
