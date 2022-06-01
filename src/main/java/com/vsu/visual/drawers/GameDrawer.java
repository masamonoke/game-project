package com.vsu.visual.drawers;

import com.vsu.service.GameService;
import com.vsu.visual.ViewConfig;
import com.vsu.visual.VisualData;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GameDrawer extends Drawer {
    VisualData data;

    @Override
    public void draw() {
        Canvas canvas = new Canvas(data.getTilemap().getColSize() * data.getTileSize(),
                data.getTilemap().getRowSize() * data.getTileSize());
        for (int i = 0; i < data.getTilemap().getRowSize(); i++) {
            for (int j = 0; j < data.getTilemap().getColSize(); j++) {
                Image image = ViewConfig.getInstance().getTileTypeImageMap().get(data.getTilemap().getMatrix()[i][j].getType());
                canvas.getGraphicsContext2D()
                        .drawImage(image, j * data.getTileSize(), i * data.getTileSize());
            }
        }
        data.setCurrentPane(new Pane(canvas));
        data.setCamera(new PerspectiveCamera());

        Canvas charCanvas = new Canvas(data.getTileSize(), data.getTileSize());
        data.setCharacterCanvas(charCanvas);
        data.getCurrentPane().getChildren().add(charCanvas);
        GameService gameService = new GameService();
        data.setCharacter(gameService.initCharacter(data.getTilemap()));

        CharacterDrawer drawer = new CharacterDrawer(data, data.getCharacter());
        drawer.draw();
        data.setConfigCanvas(new Canvas(data.getWindowWidth(),data.getWindowHeight()));

        Scene scene = new Scene(data.getCurrentPane(), data.getWindowWidth(), data.getWindowHeight());
        scene.setCamera(data.getCamera());
        data.getStage().setScene(scene);
        data.getStage().setFullScreen(true);
        data.getStage().show();
    }
}
