package com.vsu.actor.movement.combat;

import com.vsu.actor.model.Actor;
import com.vsu.actor.movement.MovementResult;
import com.vsu.actor.movement.MovementType;

import static com.vsu.App.logger;

public class HeavyAttack extends MeleeAttack {
    //TODO: константы убрать
    public HeavyAttack() {
        super(90, 50);
        price = 7;
        movementType = MovementType.HeavyAttack;
    }

    public HeavyAttack(double sectorHalfAngle, double radius) {
        super(sectorHalfAngle, radius);
    }

    @Override
    public MovementResult apply(Actor actor) {
        movementResult = super.apply(actor);
        if (movementResult != null) {
            var damageDone = movementResult.getDamageData().getDamageDone() * 2;
            movementResult.getDamageData().setDamageDone(damageDone);
            movementResult.setMovementType(movementType);
            log(actor);
        } else {
            logger.debug(actor + " made " + movementType);
        }
        return movementResult;
    }

    @Override
    public void log(Actor actor) {
        logger.info(actor + " made" + movementType + ": " + movementResult.getDamageData().getDamageDone());
    }

    @Override
    public boolean takeResources(Actor actor) {
        return super.takeResources(actor);
    }
}
