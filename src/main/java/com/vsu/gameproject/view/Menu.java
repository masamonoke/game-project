package com.vsu.gameproject.view;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.Pane;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Timer;
import java.util.TimerTask;

@RequiredArgsConstructor
public class Menu {
    @NonNull
    private ViewData data;
    private Pane currentPane;

    public void draw() {
        Background background = new Background(
                new BackgroundImage(data.getImageCache().getImageByPath("/img/menu/menuStatic.png"),
                        null,
                        null,
                        BackgroundPosition.CENTER,
                        null)
        );
        Canvas canvas = new Canvas();
        currentPane = new Pane(canvas);
        currentPane.setBackground(background);
        //TODO:  переделать под относительное расположение
        drawGif(data.getImageCache().getImageByPath("/img/menu/eye.gif"), 75, 90);
        drawGif(data.getImageCache().getImageByPath("/img/menu/monster.gif"), 60, 435);
        //drawTitle(canvas, data.getImageCache().getImageByPath("/img/menu/gametitle.png"));

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
}
