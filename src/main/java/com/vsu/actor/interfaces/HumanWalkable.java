package com.vsu.actor.interfaces;

import com.vsu.actor.model.Actor;
import com.vsu.map.Vector2;
import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class HumanWalkable implements Walkable {
    private double step;

    public void walkUp(Actor actor) {
        var val = Vector2.up.multiply(step);
        actor.setPos(actor.getPos().add(val));
    }

    public void walkDown(Actor actor) {
        var val = Vector2.down.multiply(step);
        actor.setPos(actor.getPos().add(val));
    }

    public void walkLeft(Actor actor) {
        var val = Vector2.left.multiply(step);
        actor.setPos(actor.getPos().add(val));
    }

    public void walkRight(Actor actor) {
        var val = Vector2.right.multiply(step);
        actor.setPos(actor.getPos().add(val));
    }

    public void walkUpRight(Actor actor) {
        var val = Vector2.up.add(Vector2.right).multiply(step);
        actor.setPos(actor.getPos().add(val));
    }

    public void walkUpLeft(Actor actor) {
        var val = Vector2.up.add(Vector2.left).multiply(step);
        actor.setPos(actor.getPos().add(val));
    }

    public void walkDownRight(Actor actor) {
        var val = Vector2.down.add(Vector2.right).multiply(step);
        actor.setPos(actor.getPos().add(val));
    }

    public void walkDownLeft(Actor actor) {
        var val = Vector2.down.add(Vector2.left).multiply(step);
        actor.setPos(actor.getPos().add(val));
    }
}
