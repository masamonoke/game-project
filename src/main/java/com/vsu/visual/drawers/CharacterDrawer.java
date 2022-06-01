package com.vsu.visual.drawers;

import com.vsu.actor.model.Character;
import com.vsu.visual.CharacterControls;
import com.vsu.visual.ViewConfig;
import com.vsu.visual.VisualData;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
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
        Image image = data.getImageCache().getImageByPath("/img/character/move.gif");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0,data.getTileSize() , data.getTileSize() );

        switch (direction) {
            case North -> {
               drawRotatedImage(gc,image,-90,0,0);

            }
            case South -> {
                drawRotatedImage(gc,image,90,0,0);

            }
            case West -> {
                drawRotatedImage(gc,image,180,0,0);

            }
            case East -> {
                canvas.getGraphicsContext2D().drawImage(data.getImageCache().getImageByPath("/img/character/move.gif"),0,0);
            }
        }
        canvas.setLayoutX(character.getTilePos().col * data.getTileSize());
        canvas.setLayoutY(character.getTilePos().row * data.getTileSize());
    }
    private void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy) {
        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, angle, tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2);
        gc.drawImage(image, tlpx, tlpy);
        gc.restore(); //
    }

    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
}
