package com.vsu.actor.movement;

import com.vsu.actor.model.Actor;

public class Crouch extends Movement {
    private Actor actor;

    public Crouch() {
        type = MovementType.Crouch;
    }

    @Override
    public Movement move(Movement prev) {
        if (!(prev.type.equals(MovementType.Attack))) {
            //TODO: рассчитать от уровня экипировки или задать константы
            actor.setNoise(actor.getNoise() - 10);
            actor.setSpeed(actor.getSpeed() - 10);
            actor.setDamage(actor.getDamage() + 200);
        }
        return null;
    }
}
