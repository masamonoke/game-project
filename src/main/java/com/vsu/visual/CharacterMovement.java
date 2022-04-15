package com.vsu.visual;


import com.vsu.visual.drawers.CharacterDrawer;
import com.vsu.visual.drawers.Direction;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import lombok.AllArgsConstructor;

import static com.vsu.model.Direction2D.*;

@AllArgsConstructor
public class CharacterMovement {
    VisualData data;


    public Button apply(Canvas canvas, CharacterDrawer drawer) {
        Button control = new Button();
        control.setOnKeyPressed(keyEvent -> {
            Direction direction = null;
            Position pos = new Position(data.getCharacter().getPos().row, data.getCharacter().getPos().col);
            Position newPos = null;
            //TODO:нужна обработка коллизий со стенкой (С)
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
                data.getCamera().setLayoutX((-ViewConfig.getINSTANCE().getWindowWidth() >> 1) + newPos.col * data.getTileSize());
                data.getCamera().setLayoutY((-ViewConfig.getINSTANCE().getWindowHeight() >> 1) + newPos.row * data.getTileSize());
                data.getCharacter().setPos(newPos);
                drawer.redraw(canvas, direction);
            }
        });
        return control;
    }
}
