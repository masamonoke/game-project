package com.vsu;

import com.vsu.map.model.Tilemap;
import com.vsu.visual.ViewConfig;
import com.vsu.visual.ImageCache;
import com.vsu.visual.drawers.MenuDrawer;
import com.vsu.visual.VisualData;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class App extends Application {
    public static final Logger logger = Logger.getLogger(App.class.getName());
    @Override
    public void start(Stage stage) {
        Rectangle2D screen = Screen.getPrimary().getBounds();
        PropertyConfigurator.configure(getClass().getResource("/log4j.properties"));
        VisualData data = new VisualData(new ImageCache(), new Stage(),
                new Tilemap(ViewConfig.getInstance().getMapRowCount(), ViewConfig.getInstance().getMapColCount()),48,48,screen.getWidth(),screen.getHeight());
        MenuDrawer menuDrawer = new MenuDrawer(data);
        logger.info("App starts");
        menuDrawer.draw();
    }

    public static void main(String[] args) {
        launch();
    }
}