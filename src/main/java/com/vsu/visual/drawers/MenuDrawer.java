package com.vsu.visual.drawers;

import com.vsu.maze_generation.MazeGenAlgorithms;
import com.vsu.visual.ViewConfig;
import com.vsu.visual.ViewController;
import com.vsu.visual.VisualData;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import lombok.RequiredArgsConstructor;

import java.util.Timer;
import java.util.TimerTask;

@RequiredArgsConstructor
public class MenuDrawer extends Drawer {
    private Pane currentPane;

    public MenuDrawer(VisualData data) {
        super(data);
    }

    @Override
    public void draw() {
        Background background = new Background(
                new BackgroundImage(ViewConfig.getINSTANCE().getImageCache().getImageByPath("/img/menu/menuStatic.png"),
                        null,
                        null,
                        BackgroundPosition.CENTER,
                        null)
        );
        Canvas canvas = new Canvas(700, 600);
        currentPane = new Pane(canvas);
        currentPane.setBackground(background);
        //TODO:  переделать под относительное расположение
        drawGif(ViewConfig.getINSTANCE().getImageCache().getImageByPath("/img/menu/eye.gif"),
                data.getWindowWidth()/9.3, data.getWindowHeight()/6.7);
        drawGif(ViewConfig.getINSTANCE().getImageCache().getImageByPath("/img/menu/monster.gif"),
                data.getWindowWidth()/11.7, data.getWindowHeight()/1.4);
        drawTitle(canvas, ViewConfig.getINSTANCE().getImageCache().getImageByPath("/img/menu/gameTitle.png"));
        initButtons();

        Scene menu = new Scene(currentPane, data.getWindowWidth(), data.getWindowHeight());
        data.getStage().setScene(menu);
        data.getStage().show();
    }

    private void drawGif(Image img, double x, double y) {
        ImageView imageView = new ImageView(img);
        currentPane.getChildren().add(imageView);
        imageView.setX(x);
        imageView.setY(y);
    }

    private void drawTitle(Canvas canvas, Image img) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                gc.setGlobalAlpha(0.02);
                gc.drawImage(img, data.getWindowWidth()/11.7, data.getWindowHeight()/7.5);
            }
        };
        timer.schedule(timerTask, 100, 100);
    }

    ViewController controller = new ViewController();
    private void initButtons() {
        Button startButton = new Button();
        drawButton(startButton,ViewConfig.getINSTANCE().getImageCache().getImageByPath
                        ("/img/menu/buttons/buttonStartOn.png"),
                ViewConfig.getINSTANCE().getImageCache().getImageByPath
                        ("/img/menu/buttons/buttonStartOff.png"),
                data.getWindowWidth()/2.8, data.getWindowHeight()/3.0);

        startButton.onActionProperty().set(actionEvent -> {
            //TODO:Второй тип алгоритма не работает,только BackTracking (С)
            controller.generateMaze(MazeGenAlgorithms.RandomWalk, data.getGrid());
            GameDrawer drawer = new GameDrawer(data);
            drawer.draw();
        });

        Button settingsButton = new Button();
        drawButton(settingsButton, ViewConfig.getINSTANCE().getImageCache().getImageByPath
                        ("/img/menu/buttons/buttonConfOn.png"),
                ViewConfig.getINSTANCE().getImageCache().getImageByPath
                        ("/img/menu/buttons/buttonConfOff.png"),
                data.getWindowWidth()/2.8, data.getWindowHeight()/2.15);

        settingsButton.onActionProperty().set(actionEvent -> {
            //TODO: settings page
        });

        Button exitButton = new Button();
        drawButton(exitButton, ViewConfig.getINSTANCE().getImageCache().getImageByPath
                        ("/img/menu/buttons/buttonExitOn.png"),
                ViewConfig.getINSTANCE().getImageCache().getImageByPath
                        ("/img/menu/buttons/buttonExitOff.png"),
                data.getWindowWidth()/2.8, data.getWindowHeight()/1.7);

        exitButton.onActionProperty().set(actionEvent -> {
            System.exit(0);
        });

        currentPane.getChildren().add(startButton);
        currentPane.getChildren().add(settingsButton);
        currentPane.getChildren().add(exitButton);
    }

    //TODO: optimize
    private void drawButton(Button button, Image onHoverImg, Image unHoverImg, double x, double y) {
        button.setBackground(
                new Background(new BackgroundImage(unHoverImg, BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setPadding(Insets.EMPTY);
        button.setPrefSize(data.getWindowWidth()/3.3, data.getWindowHeight()/12.);

        button.setOnMouseEntered(mouseEvent -> {
            button.setBackground(new Background(new BackgroundImage(onHoverImg,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT)));
        });
        button.setOnMouseExited(mouseEvent -> {
            button.setBackground(new Background(new BackgroundImage(unHoverImg,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT)));
        });
    }
}
