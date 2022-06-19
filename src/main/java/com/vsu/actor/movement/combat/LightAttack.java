package com.vsu.actor.movement.combat;

import com.vsu.actor.model.Actor;
import com.vsu.actor.movement.MovementResult;
import com.vsu.actor.movement.MovementType;

import static com.vsu.App.logger;

public class LightAttack extends MeleeAttack {
    //TODO: константы убрать
    public LightAttack() {
        super(70, 50);
        price = 5;
        movementType = MovementType.LightAttack;
    }

    public LightAttack(double sectorHalfAngle, double radius) {
        super(sectorHalfAngle, radius);
        movementType = MovementType.LightAttack;
    }

    @Override
    public MovementResult apply(Actor actor) {
        movementResult = super.apply(actor);
        if (movementResult != null) {
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
