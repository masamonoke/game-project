package com.vsu.visual.drawers;

import com.vsu.map.maze.GenerateRoom;
import com.vsu.map.maze.MazeGenAlgorithms;
import com.vsu.visual.ViewController;
import com.vsu.visual.VisualData;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
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
        data.getStage().setFullScreen(true);
        data.getStage().setWidth(data.getWindowWidth());
        data.getStage().setHeight(data.getWindowHeight());

        Background background = new Background(
                new BackgroundImage(data.getImageCache().getImageByPath("/img/menu/menuStatic.png"),
                        null,
                        null,
                        BackgroundPosition.CENTER,
                        null)
        );
        Canvas canvas = new Canvas(data.getWindowWidth(), data.getWindowHeight());
        currentPane = new Pane(canvas);
        currentPane.setBackground(background);

        drawGif(data.getImageCache().getImageByPath("/img/menu/eye.gif"), data.getWindowWidth()/3.37, data.getWindowHeight()/7.80);
        drawGif(data.getImageCache().getImageByPath("/img/menu/monster.gif"), data.getWindowWidth()/3.44, data.getWindowHeight()/1.48);
        drawTitle(canvas, data.getImageCache().getImageByPath("/img/menu/gametitle.png"));
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
                gc.drawImage(img, data.getWindowWidth()/3.5, data.getWindowHeight()/8.75);
            }
        };
        timer.schedule(timerTask, 100, 100);
    }

    ViewController controller = new ViewController();

    private void initButtons() {
        Button startButton = new Button();
        drawButton(startButton, data.getImageCache().getImageByPath("/img/menu/buttons/buttonStartOn.png"),
                data.getImageCache().getImageByPath("/img/menu/buttons/buttonStartOff.png"), data.getWindowWidth()/2.4, data.getWindowHeight()/3.5);
        startButton.onActionProperty().set(actionEvent -> {
            GenerateRoom generateRoom = new GenerateRoom(data.getTilemap(), 8, 8, 4, 4);
            controller.generateMaze(MazeGenAlgorithms.RandomWalk, data.getTilemap());
            GameDrawer drawer = new GameDrawer(data);
            generateRoom.generate(data.getTilemap());
            drawer.draw();
        });
        Button settingsButton = new Button();
        drawButton(settingsButton, data.getImageCache().getImageByPath("/img/menu/buttons/buttonConfOn.png"),
                data.getImageCache().getImageByPath("/img/menu/buttons/buttonConfOff.png"),
                data.getWindowWidth()/2.4, data.getWindowHeight()/2.5);
        settingsButton.onActionProperty().set(actionEvent -> {
            //TODO: settings page
        });
        Button exitButton = new Button();
        drawButton(exitButton, data.getImageCache().getImageByPath("/img/menu/buttons/buttonExitOn.png"),
                data.getImageCache().getImageByPath("/img/menu/buttons/buttonExitOff.png"),
                data.getWindowWidth()/2.4, data.getWindowHeight()/1.94);
        exitButton.onActionProperty().set(actionEvent -> {
            System.exit(0);
        });

        currentPane.getChildren().add(startButton);
        currentPane.getChildren().add(settingsButton);
        currentPane.getChildren().add(exitButton);
    }


    private void drawButton(Button button, Image onHoverImg, Image unHoverImg, double x,double y) {
        button.setBackground(
                new Background(new BackgroundImage(unHoverImg, BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setPadding(Insets.EMPTY);
        button.setPrefSize(215, 50);

        button.setOnMouseEntered(mouseEvent -> {
            button.setBackground(new Background(new BackgroundImage(onHoverImg,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT)));
        });
        button.setOnMouseExited(mouseEvent -> {
            button.setBackground(new Background(new BackgroundImage(unHoverImg, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        });
    }
}
