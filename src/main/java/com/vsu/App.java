package com.vsu;

import com.vsu.visual.ImageCache;
import com.vsu.visual.drawers.MenuDrawer;
import com.vsu.visual.VisualData;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        VisualData data = new VisualData(new ImageCache(), new Stage());
        MenuDrawer menuDrawer = new MenuDrawer(data);
        menuDrawer.draw();
    }

    public static void main(String[] args) {
        launch();
    }
}