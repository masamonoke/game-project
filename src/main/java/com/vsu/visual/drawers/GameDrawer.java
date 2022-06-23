package com.vsu.visual.drawers;

import com.vsu.service.game.GameService;
import com.vsu.visual.VisualData;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GameDrawer extends Drawer {
   private VisualData data;

    @Override
    public void draw() {
        data.setMainMapCanvas(new Canvas(data.getWindowWidth(),data.getWindowHeight()));
        data.setConfigCanvas(new Canvas(data.getWindowWidth()/2,data.getWindowHeight()/2));
        data.setCharacterCanvas(new Canvas(data.getTileSize(),data.getTileSize()));

        data.setCurrentPane(new Pane(data.getMainMapCanvas()));


        GameService gameService = new GameService();

        data.setActor(gameService.initCharacter(data.getTilemap()));

        CharacterDrawer drawer = new CharacterDrawer(data,data.getActor());
        drawer.draw();

        data.getCurrentPane().getChildren().add(data.getCharacterCanvas());
        data.getCurrentPane().getChildren().add(data.getConfigCanvas());


        Scene scene = new Scene(data.getCurrentPane(), data.getWindowWidth(), data.getWindowHeight());
        scene.setFill(Color.BLACK);
        data.getStage().setScene(scene);
        data.getStage().setFullScreen(true);
        data.getStage().show();
    }
}
