package com.vsu.actor.movement;


import com.vsu.actor.effect.TemporaryStatusEffect;
import com.vsu.actor.model.Actor;
import com.vsu.actor.model.ActorMobilityState;

import static com.vsu.App.logger;


public class Jump extends Movement {
    public Jump() {
        price = 3;
        movementType = MovementType.Jump;
    }

    @Override
    public MovementResult apply(Actor actor) {
        //возможно добавить увеличение урона и увеличить область урона
        var effect = new TemporaryStatusEffect();
        effect.setDamageBonus(actor.getPhysicalDamage() +
                actor.getStrength() + actor.getAgility() + actor.getModificationDamage());
        log(actor);
        movementResult = MovementResult
                .builder()
                .movementType(movementType)
                .newActorMobilityState(ActorMobilityState.Jumping)
                .statusEffect(effect)
                .build();
        return movementResult;
    }

    @Override
    public void log(Actor actor) {
        logger.info(actor + " jumped");
    }
}
