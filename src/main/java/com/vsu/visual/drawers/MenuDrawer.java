package com.vsu.visual.drawers;

import com.vsu.grid.maze.MazeGenAlgorithms;
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
                new BackgroundImage(data.getImageCache().getImageByPath("/img/menu/menuStatic.png"),
                        null,
                        null,
                        BackgroundPosition.CENTER,
                        null)
        );
        Canvas canvas = new Canvas(700, 600);
        currentPane = new Pane(canvas);
        currentPane.setBackground(background);
        //TODO:  переделать под относительное расположение
        drawGif(data.getImageCache().getImageByPath("/img/menu/eye.gif"), 75, 90);
        drawGif(data.getImageCache().getImageByPath("/img/menu/monster.gif"), 60, 435);
        drawTitle(canvas, data.getImageCache().getImageByPath("/img/menu/gametitle.png"));
        initButtons();
        Scene menu = new Scene(currentPane, 700, 600);
        data.getStage().setScene(menu);
        data.getStage().show();
    }

    private void drawGif(Image img, int x, int y) {
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
                //TODO: переделать под относительное расположение
                gc.drawImage(img, 60, 80);
            }
        };
        timer.schedule(timerTask, 100, 100);
    }

    ViewController controller = new ViewController();
    //TODO: переделать под относительное расположение
    private void initButtons() {
        Button startButton = new Button();
        drawButton(startButton, data.getImageCache().getImageByPath("/img/menu/buttons/buttonStartOn.png"),
                data.getImageCache().getImageByPath("/img/menu/buttons/buttonStartOff.png"), 250, 200);
        startButton.onActionProperty().set(actionEvent -> {
            //TODO:Второй тип алгоритма не работает,только BackTracking (С)
            controller.generateMaze(MazeGenAlgorithms.RandomWalk, data.getGrid());
            GameDrawer drawer = new GameDrawer(data);
            drawer.draw();
        });
        Button settingsButton = new Button();
        drawButton(settingsButton, data.getImageCache().getImageByPath("/img/menu/buttons/buttonConfOn.png"),
                data.getImageCache().getImageByPath("/img/menu/buttons/buttonConfOff.png"), 250, 280);
        settingsButton.onActionProperty().set(actionEvent -> {
            //TODO: settings page
        });
        Button exitButton = new Button();
        drawButton(exitButton, data.getImageCache().getImageByPath("/img/menu/buttons/buttonExitOn.png"),
                data.getImageCache().getImageByPath("/img/menu/buttons/buttonExitOff.png"), 250, 360);
        exitButton.onActionProperty().set(actionEvent -> {
            System.exit(0);
        });

        currentPane.getChildren().add(startButton);
        currentPane.getChildren().add(settingsButton);
        currentPane.getChildren().add(exitButton);
    }

    //TODO: optimize
    private void drawButton(Button button, Image onHoverImg, Image unHoverImg, int x, int y) {
        button.setBackground(
                new Background(new BackgroundImage(unHoverImg, BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setPadding(Insets.EMPTY);
        //TODO: переделать в относительный размер
        button.setPrefSize(215, 50);

        button.setOnMouseEntered(mouseEvent -> {
            button.setBackground(new Background(new BackgroundImage(onHoverImg,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT)));
        });
        button.setOnMouseExited(mouseEvent -> {
            button.setBackground(new Background(new BackgroundImage(unHoverImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        });
    }
}
