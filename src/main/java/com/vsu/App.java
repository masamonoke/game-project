package com.vsu;

import com.vsu.model.grid.Grid;
import com.vsu.visual.GameBuilder;
import com.vsu.visual.ImageCache;
import com.vsu.visual.ViewConfig;
import com.vsu.visual.drawers.MenuDrawer;
import com.vsu.visual.VisualData;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        VisualData data = new VisualData(new ImageCache(), new Stage(),
                new Grid(ViewConfig.getINSTANCE().getRowCount(), ViewConfig.getINSTANCE().getColCount()));
        GameBuilder gameBuilder = new GameBuilder();
        gameBuilder.initGame(data);
        MenuDrawer menuDrawer = new MenuDrawer(data);
        menuDrawer.draw();
    }

    public static void main(String[] args) {
        launch();
    }
}