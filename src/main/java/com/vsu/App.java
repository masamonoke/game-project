package com.vsu;

import com.vsu.grid.model.Grid;
import com.vsu.visual.ImageCache;
import com.vsu.visual.ViewConfig;
import com.vsu.visual.drawers.MenuDrawer;
import com.vsu.visual.VisualData;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class App extends Application {
    public static final Logger logger = Logger.getLogger(App.class.getName());
    @Override
    public void start(Stage stage) {
        PropertyConfigurator.configure(getClass().getResource("/log4j.properties"));
        VisualData data = new VisualData(new Stage(),
                new Grid(ViewConfig.getINSTANCE().getMapRowCount(), ViewConfig.getINSTANCE().getMapColCount()));
        MenuDrawer menuDrawer = new MenuDrawer(data);
        logger.info("App starts");
        menuDrawer.draw();
    }

    public static void main(String[] args) {
        launch();
    }
}