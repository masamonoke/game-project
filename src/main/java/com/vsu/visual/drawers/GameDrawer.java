package com.vsu.visual.drawers;

import com.vsu.service.GameService;
import com.vsu.visual.ViewConfig;
import com.vsu.visual.VisualData;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GameDrawer extends Drawer {
   private VisualData data;

    @Override
    public void draw() {
        data.setMainMapCanvas(new Canvas(data.getTilemap().getColSize() * data.getTileSize(),
                data.getTilemap().getRowSize() * data.getTileSize()));
        data.setConfigCanvas(new Canvas(data.getWindowWidth()/2,data.getWindowHeight()/2));
        data.setCharacterCanvas(new Canvas(data.getTileSize(),data.getTileSize()));

        data.setCurrentPane(new Pane(data.getMainMapCanvas()));


        GameService gameService = new GameService();

        data.setCharacter(gameService.initCharacter(data.getTilemap()));
        data.setCamera(new PerspectiveCamera());

        CharacterDrawer drawer = new CharacterDrawer(data,data.getCharacter());
        MainMapDrawer mapDrawer = new MainMapDrawer(data);

        mapDrawer.draw();
        drawer.draw();

        data.getCurrentPane().getChildren().add(data.getCharacterCanvas());
        data.getCurrentPane().getChildren().add(data.getConfigCanvas());


        Scene scene = new Scene(data.getCurrentPane(), data.getWindowWidth(), data.getWindowHeight());

        scene.setCamera(data.getCamera());
        data.getStage().setScene(scene);
        data.getStage().setFullScreen(true);
        data.getStage().show();
    }
}
