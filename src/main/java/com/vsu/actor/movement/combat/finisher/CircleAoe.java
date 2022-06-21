package com.vsu.actor.movement.combat.finisher;

import com.vsu.actor.model.Actor;
import com.vsu.actor.movement.MovementResult;
import com.vsu.actor.movement.combat.damage.DamageData;
import com.vsu.actor.movement.combat.damage.dataset.CircleDamage;
import com.vsu.utils.GeometryUtils;

import static com.vsu.App.logger;

public class CircleAoe extends Finisher {

    public CircleAoe() {
        super();
        finisherType = FinisherType.CircleAoe;
    }

    @Override
    public MovementResult apply(Actor actor) {
        var cartesianCharacterPos = GeometryUtils.fromScreenToCartesianCoordinates(actor.getPos());
        var damageDone = actor.getPhysicalDamage() + actor.getModificationDamage();
        var radius = 200;
        var damage = new DamageData(new CircleDamage(radius, cartesianCharacterPos), damageDone);
        movementResult = MovementResult
                .builder()
                .movementType(movementType)
                .damageData(damage)
                .build();
        log(actor);
        return movementResult;
    }

    @Override
    public String toString() {
        return finisherType.toString();
    }

    @Override
    public void log(Actor actor) {
        logger.info(actor + " made " + this);
    }
}
