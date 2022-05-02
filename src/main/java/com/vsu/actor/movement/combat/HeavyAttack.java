package com.vsu.actor.movement.combat;

import com.vsu.actor.model.Character;
import com.vsu.actor.movement.Movement;
import com.vsu.actor.movement.MovementResult;
import com.vsu.actor.movement.MovementType;

import static com.vsu.App.logger;

public class HeavyAttack extends Movement {
    public HeavyAttack() {
        type = MovementType.HeavyAttack;
    }

    @Override
    public MovementResult apply(Movement prev, Character character) {
        logger.info(character + " made heavy attack");
        return null;
    }
}
