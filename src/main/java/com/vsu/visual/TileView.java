package com.vsu.visual;

import com.vsu.model.Tile;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TileView {
    private Tile tile;
    private int size;
    private final Rectangle rectangle;
    private final StackPane stackPane;
    private final GridView gridView;
    private Image img;

    public Color color;

}
