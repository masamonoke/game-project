package com.vsu.visual;

import com.vsu.actor.model.Actor;
import com.vsu.map.model.Tilemap;
import javafx.scene.Camera;
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
    private Camera camera;
    private Pane currentPane;
    //TODO: задавать это значение из конструктора
    private int tileSize = 48;
    private int characterSize = 48;
    private int windowWidth = 700;
    private int windowHeight = 600;

    private Canvas characterCanvas;
    private Actor actor;

}
