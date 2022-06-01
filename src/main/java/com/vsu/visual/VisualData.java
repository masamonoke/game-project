package com.vsu.visual;


import com.vsu.actor.model.Character;
import com.vsu.map.model.Tilemap;
import javafx.scene.Camera;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.*;

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

    private int tileSize = 48;
    private int characterSize = 48;
    private double windowWidth = 700;
    private double windowHeight = 600;
    private Character character;

    private Canvas characterCanvas;
    private Canvas mainMapCanvas;
    private Canvas configCanvas;
    private boolean inPause;
}
