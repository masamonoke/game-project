package com.vsu.visual.drawers;

import com.vsu.model.TileType;
import com.vsu.visual.ViewConfig;
import com.vsu.visual.VisualData;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GameDrawer extends Drawer {
    VisualData data;

    @Override
    public void draw() {
        Canvas canvas = new Canvas(data.getGrid().getColSize() * data.getTileSize(),
                data.getGrid().getRowSize() * data.getTileSize());
        for (int i = 0; i < data.getGrid().getRowSize(); i++) {
            for (int j = 0; j < data.getGrid().getColSize(); j++) {
                if (data.getGrid().getMatrix()[i][j].getType().equals(TileType.Wall)) {
                    canvas.getGraphicsContext2D()
                            .drawImage(ViewConfig.getINSTANCE().getTileTypeImagePathMap().get(TileType.Wall),
                                    i * data.getTileSize(), j * data.getTileSize());
                }
                if (data.getGrid().getMatrix()[i][j].getType().equals(TileType.Pavement)) {
                    canvas.getGraphicsContext2D()
                            .drawImage(ViewConfig.getINSTANCE().getTileTypeImagePathMap().get(TileType.Pavement),
                                    i * data.getTileSize(), j * data.getTileSize());
                }
                if (data.getGrid().getMatrix()[i][j].getType().equals(TileType.Room)) {
                    canvas.getGraphicsContext2D()
                            .drawImage(ViewConfig.getINSTANCE().getTileTypeImagePathMap().get(TileType.Room),
                                    i * data.getTileSize(), j * data.getTileSize());
                }
                if (data.getGrid().getMatrix()[i][j].getType().equals(TileType.Swamp)) {
                    canvas.getGraphicsContext2D()
                            .drawImage(ViewConfig.getINSTANCE().getTileTypeImagePathMap().get(TileType.Swamp),
                                    i * data.getTileSize(), j * data.getTileSize());
                }
                if (data.getGrid().getMatrix()[i][j].getType().equals(TileType.Forest)) {
                    canvas.getGraphicsContext2D()
                            .drawImage(ViewConfig.getINSTANCE().getTileTypeImagePathMap().get(TileType.Forest),
                                    i * data.getTileSize(), j * data.getTileSize());
                }

                if (data.getGrid().getMatrix()[i][j].getType().equals(TileType.Lake)) {
                    canvas.getGraphicsContext2D()
                            .drawImage(ViewConfig.getINSTANCE().getTileTypeImagePathMap().get(TileType.Lake),
                                    i * data.getTileSize(), j * data.getTileSize());
                }
            }
        }
        data.setCurrentPane(new Pane(canvas));
        data.setCamera(new PerspectiveCamera());

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
