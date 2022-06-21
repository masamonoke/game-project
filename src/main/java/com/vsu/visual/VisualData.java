package com.vsu.visual;


import com.vsu.actor.model.Actor;
import com.vsu.map.model.Tilemap;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class VisualData {
    @NonNull
    private ImageCache imageCache;
    @NonNull
    private final Stage stage;
    @NonNull
    private Tilemap tilemap;
    @NonNull
    private int tileSize;
    @NonNull
    private int characterSize;
    @NonNull
    private double windowWidth;
    @NonNull
    private double windowHeight;
    private Actor actor;

    private Pane currentPane;
    private Canvas characterCanvas;
    private Canvas mainMapCanvas;
    private Canvas configCanvas;
    private boolean inPause;
}
