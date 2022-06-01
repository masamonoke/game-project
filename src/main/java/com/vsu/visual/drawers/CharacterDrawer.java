package com.vsu.visual.drawers;

import com.vsu.actor.model.Character;
import com.vsu.visual.CharacterControls;
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
        data.getCamera().setLayoutX(-data.getWindowWidth()/2
                + character.getTilePos().col * data.getTileSize());
        data.getCamera().setLayoutY(-data.getWindowHeight()/2
                + character.getTilePos().row * data.getTileSize());
        redraw(data.getCharacterCanvas(),Direction.East);
        if (!isMovementApplied) {
            CharacterControls movement = new CharacterControls(data);
            Button button = movement.apply(data.getCharacterCanvas(), this);
            data.getCurrentPane().getChildren().add(button);
            isMovementApplied = true;
        }
    }


    public void redraw(Canvas canvas, Direction direction) {
        canvas.getGraphicsContext2D().clearRect(0, 0,data.getTileSize() , data.getTileSize() );
        switch (direction) {
            case North -> {
                canvas.getGraphicsContext2D().drawImage(data.getImageCache().getImageByPath("img/character/static.png"),0,0);
                //  canvas.getGraphicsContext2D().rotate(90);
            }
            case South -> {
                canvas.getGraphicsContext2D().drawImage(data.getImageCache().getImageByPath("/img/character/S.gif"),
                        10, 0);
            }
            case West -> {
                canvas.getGraphicsContext2D().drawImage(data.getImageCache().getImageByPath("/img/character/A.gif"),
                        10, 0);
            }
            case East -> {
                canvas.getGraphicsContext2D().drawImage(data.getImageCache().getImageByPath("/img/character/D.gif"),
                        10, 0);
            }
        }
        canvas.setLayoutX(character.getTilePos().col * data.getTileSize());
        canvas.setLayoutY(character.getTilePos().row * data.getTileSize());
    }
}
