package com.vsu.actor.movement;

import com.vsu.actor.effect.TemporaryStatusEffect;
import com.vsu.actor.model.Actor;
import com.vsu.actor.model.ActorMobilityState;

import static com.vsu.App.logger;

public class Crouch extends Movement {
    public Crouch() {
        price = 1;
        movementType = MovementType.Crouch;
    }

    @Override
    public MovementResult apply(Actor actor) {
        log(actor);
        //возможно увеличить урон от определенных типов оружия (кинжалы), пока персонаж находится в этом состоянии
        var effect = new TemporaryStatusEffect();
        effect.setDamageBonus(actor.getPhysicalDamage() * actor.getTrickery() & actor.getAgility());
        movementResult = MovementResult
                .builder()
                .movementType(movementType)
                .newActorMobilityState(ActorMobilityState.Crouching)
                .statusEffect(effect)
                .build();
        return movementResult;
    }

    @Override
    public void log(Actor actor) {
        logger.info(actor + " is crouched");
    }

}
