package com.vsu.actor.movement.combat;

import com.vsu.actor.model.Character;
import com.vsu.actor.movement.Movement;
import com.vsu.actor.movement.MovementResult;
import com.vsu.actor.movement.MovementType;

import static com.vsu.App.logger;

public class LightAttack extends Movement {
    private String tag;
    public LightAttack() {
        type = MovementType.LightAttack;
    }

    public LightAttack(String tag) {
        type = MovementType.LightAttack;
        this.tag = tag;
    }

    @Override
    public MovementResult apply(Movement prev, Character character) {
        //вернуть нанесенный урон, урон вычислить из статов персонажа
        logger.info(character + " made light attack");
        return movementResult;
    }
}
