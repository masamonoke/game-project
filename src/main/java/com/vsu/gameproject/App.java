package com.vsu.gameproject;

import com.vsu.gameproject.view.ImageCache;
import com.vsu.gameproject.view.Menu;
import com.vsu.gameproject.view.ViewData;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        ViewData data = new ViewData(new ImageCache(), new Stage());
        Menu menu = new Menu(data);
        menu.draw();
    }

    public static void main(String[] args) {
        launch();
    }
}