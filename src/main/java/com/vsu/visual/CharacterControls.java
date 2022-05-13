package com.vsu.visual;

import com.vsu.map.model.TileType;
import com.vsu.visual.drawers.CharacterDrawer;
import com.vsu.visual.drawers.Direction;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import lombok.AllArgsConstructor;

import static com.vsu.App.logger;
import static com.vsu.map.model.TilemapDirection2D.*;

@AllArgsConstructor
public class CharacterControls {
    private VisualData data;
    public Button apply(Canvas canvas, CharacterDrawer drawer) {
        Button control = new Button();
        control.setOnKeyPressed(keyEvent -> {
            Direction direction = null;
            Position pos = new Position(data.getCharacter().getTilePos().row, data.getCharacter().getTilePos().col);
            Position newPos = null;
            switch (keyEvent.getCode()) {
                case A -> {
                    direction = Direction.West;
                    newPos = pos.add(Position.left);
                }
                case D -> {
                    direction = Direction.East;
                    newPos = pos.add(Position.right);
                }
                case W -> {
                    direction = Direction.North;
                    newPos = pos.add(Position.up);
                }
                case S -> {
                    direction = Direction.South;
                    newPos = pos.add(Position.down);
                }
            }
            if (direction != null &&
                    !data.getTilemap().getMatrix()[newPos.row][newPos.col].getType().equals(TileType.Wall)
            ) {
                data.getCamera()
                        .setLayoutX((-data.getWindowWidth() >> 1) + newPos.col * data.getTileSize());
                data.getCamera()
                        .setLayoutY((-data.getWindowHeight() >> 1) + newPos.row * data.getTileSize());
                data.getCharacter().setTilePos(newPos);
                drawer.redraw(canvas, direction);
                logger.info("Character now is on " + data.getTilemap().getMatrix()[newPos.row][newPos.col] + " tile");
            }
        });
        return control;
    }
}
