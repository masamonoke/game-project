package com.vsu.visual.drawers;

import com.vsu.model.Character;
import com.vsu.visual.CharacterMovement;
import com.vsu.visual.ViewConfig;
import com.vsu.visual.VisualData;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CharacterDrawer extends Drawer {
    @NonNull
    private Character character;
    private boolean isMovementApplied;

    public CharacterDrawer(VisualData data, Character character) {
        super(data);
        this.character = character;
        isMovementApplied = false;
    }

    @Override
    public void draw() {
        data.getCamera().setLayoutX((-data.getWindowWidth() >> 1) + character.getPos().col * data.getTileSize());
        data.getCamera().setLayoutY((-data.getWindowHeight() >> 1) + character.getPos().row * data.getTileSize());
        redraw(data.getCharacterCanvas(),Direction.East);
        if (!isMovementApplied) {
            CharacterMovement movement = new CharacterMovement(data);
            Button button = movement.apply(data.getCharacterCanvas(), this);
            data.getCurrentPane().getChildren().add(button);
            isMovementApplied = true;
        }
    }


    public void redraw(Canvas canvas, Direction direction) {
        canvas.getGraphicsContext2D().clearRect(0, 0, data.getCharacterSize(), data.getCharacterSize());
        switch (direction) {
            case North -> {
                canvas.getGraphicsContext2D().drawImage(ViewConfig.getINSTANCE().getImageCache()
                                .getImageByPath("/img/character/W.gif"), 0, 0);
            }
            case South -> {
                canvas.getGraphicsContext2D().drawImage(ViewConfig.getINSTANCE().getImageCache()
                                .getImageByPath("/img/character/S.gif"), 0, 0);
            }
            case West -> {
                canvas.getGraphicsContext2D().drawImage(ViewConfig.getINSTANCE().getImageCache()
                                .getImageByPath("/img/character/A.gif"), 0, 0);
            }
            case East -> {
                canvas.getGraphicsContext2D().drawImage(ViewConfig.getINSTANCE().getImageCache()
                                .getImageByPath("/img/character/D.gif"), 0, 0);
            }
        }
        canvas.setLayoutX(character.getPos().col * data.getTileSize());
        canvas.setLayoutY(character.getPos().row * data.getTileSize());
    }
}
