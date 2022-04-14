package com.vsu.visual;

import javafx.scene.Camera;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
public class VisualData {
    @NonNull
    private final ImageCache imageCache;
    @NonNull
    private final Stage stage;
    private Camera camera;
    private Pane currentPane;
}
