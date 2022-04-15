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
        Canvas canvas = new Canvas(data.getGrid().getColSize() * data.getTileSize(),
                data.getGrid().getRowSize() * data.getTileSize());
        for (int i = 0; i < data.getGrid().getRowSize(); i++) {
            for (int j = 0; j < data.getGrid().getColSize(); j++) {
                Image image = ViewConfig.getINSTANCE().getTileTypeImageMap().get(data.getGrid().getMatrix()[i][j].getType());
                canvas.getGraphicsContext2D()
                        .drawImage(image, j * data.getTileSize(), i * data.getTileSize());
            }
        }
        data.setCurrentPane(new Pane(canvas));
        data.setCamera(new PerspectiveCamera());

        Canvas charCanvas = new Canvas(50, 50);
        data.setCharacterCanvas(charCanvas);
        data.getCurrentPane().getChildren().add(charCanvas);

        GameService gameService = new GameService();
        data.setCharacter(gameService.initCharacter(data.getGrid()));
        CharacterDrawer drawer = new CharacterDrawer(data, data.getCharacter());
        drawer.draw();

        Scene scene = new Scene(data.getCurrentPane(), data.getWindowWidth(), data.getWindowHeight());
        scene.setCamera(data.getCamera());
        data.getStage().setScene(scene);
        data.getStage().show();
    }
}
