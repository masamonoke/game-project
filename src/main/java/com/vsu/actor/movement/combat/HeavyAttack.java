package com.vsu.actor.movement.combat;

import com.vsu.actor.model.Character;
import com.vsu.actor.movement.MovementResult;
import com.vsu.actor.movement.MovementType;

import static com.vsu.App.logger;

public class HeavyAttack extends MeleeAttack {
    public HeavyAttack() {
        super(90, 50);
        type = MovementType.HeavyAttack;
    }

    @Override
    public MovementResult apply(Character character) {
        movementResult = super.apply(character);
        if (movementResult != null) {
            var damageDone = movementResult.getDamageData().getDamageDone() * 2;
            movementResult.getDamageData().setDamageDone(damageDone);
            logger.info(character + " made" + type + ": " + movementResult.getDamageData().getDamageDone());
        } else {
            logger.debug(character + " made " + type);
        }
        return movementResult;
    }
}
