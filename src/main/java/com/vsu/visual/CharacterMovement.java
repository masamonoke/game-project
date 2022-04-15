package com.vsu.visual;


import com.vsu.model.grid.Grid;
import com.vsu.visual.drawers.CharacterDrawer;
import com.vsu.visual.drawers.Direction;
import javafx.scene.Camera;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import lombok.AllArgsConstructor;

import static com.vsu.model.Direction2D.*;

@AllArgsConstructor
public class CharacterMovement {
    VisualData data;

    //TODO:зачем здесь камера,если на есть в data? (С)
    public Button apply(Grid grid, Camera camera, Canvas canvas, CharacterDrawer drawer) {
        Button control = new Button();
        control.setOnKeyPressed(keyEvent -> {
            Direction direction = null;
            Position pos = new Position(data.getCharacter().getPos().row, data.getCharacter().getPos().col);
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
            if (direction != null) {
                data.getCamera().setLayoutX(-350 + newPos.col * data.getTileSize());
                data.getCamera().setLayoutY(-300 + newPos.row * data.getTileSize());
                data.getCharacter().setPos(newPos);
                drawer.redraw(canvas, direction);
            }
        });
        return control;
    }
}
