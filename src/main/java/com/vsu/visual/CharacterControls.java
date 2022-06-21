package com.vsu.visual;

import com.vsu.map.model.TileType;
import com.vsu.visual.drawers.CharacterDrawer;
import com.vsu.visual.drawers.Direction;
import com.vsu.visual.drawers.ConfigMapDrawer;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import lombok.AllArgsConstructor;

import static com.vsu.App.logger;
import static com.vsu.map.model.TilemapDirection2D.Position;

@AllArgsConstructor
public class CharacterControls {
    private VisualData data;

    public Button apply(Canvas canvas, CharacterDrawer drawer) {
        Button control = new Button();
        control.setOnKeyPressed(keyEvent -> {
            Direction direction = null;
            Position pos = new Position(data.getCharacter().getTilePos().row, data.getCharacter().getTilePos().col);
            Position newPos = null;
            ConfigMapDrawer configMapDrawer = new ConfigMapDrawer(data);
            if (!data.isInPause()) {
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
                    case M -> {
                        data.setInPause(true);
                        configMapDrawer.draw();
                    }
                }
            } else {
                switch (keyEvent.getCode()) {
                    case M -> {
                        data.setInPause(false);
                        configMapDrawer.clear();
                    }
                }
            }
            if (direction != null &&
                    !data.getTilemap().getMatrix()[newPos.row][newPos.col].getType().equals(TileType.Wall)
            ) {
                data.getCharacter().setTilePos(newPos);
                drawer.redraw(canvas, direction);
                logger.info("Character now is on " + data.getTilemap().getMatrix()[newPos.row][newPos.col] + " tile");
            }
        });
        return control;
    }
}