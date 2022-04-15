package com.vsu.visual;

import com.vsu.model.Character;
import com.vsu.model.grid.Grid;
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
    private final Stage stage;
    @NonNull
    private Grid grid;
    private Camera camera;
    private Pane currentPane;
    //TODO: задавать это значение из конструктора
    private int tileSize = 48;
    private int characterSize = 48;
    private int windowWidth = 700;
    private int windowHeight = 600;

    private Canvas characterCanvas;
    private Character character;

}
