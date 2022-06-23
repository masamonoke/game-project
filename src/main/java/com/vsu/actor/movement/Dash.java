package com.vsu.actor.movement;

import com.vsu.actor.effect.Stats;
import com.vsu.actor.effect.TemporaryStatusEffect;
import com.vsu.actor.model.Actor;
import com.vsu.actor.model.ActorMobilityState;
import com.vsu.utils.GeometryUtils;

import static com.vsu.App.logger;

public class Dash extends Movement {
    public Dash() {
        price = 3;
        movementType = MovementType.Dash;
    }

    @Override
    public MovementResult apply(Actor actor) {
        var distance = 10 * (actor.getAgility() + actor.getTrickery() + actor.getStrength());
        var effect = new TemporaryStatusEffect();
        effect.setDamageBonus(actor.getAgility());
        effect.setStats(Stats.builder().speed(actor.getAgility() * 3).build());
        movementResult = MovementResult
                .builder()
                .movementType(movementType)
                .newMoveLocation(GeometryUtils.getMiddlePointFromOriginToPoint(actor.getPos(),
                        actor.getCursorPos(), distance))
                .statusEffect(effect)
                //вообще говоря нужно проверить, что персонаж не под эффектом контроля
                // или же тогда дэш снимает все эффекты контроля и тогда все ок
                .newActorMobilityState(ActorMobilityState.Sprinting)
                .build();
        log(actor);
        return movementResult;
    }

    @Override
    public void log(Actor actor) {
        logger.info(actor + " made dash to " + movementResult.getNewMoveLocation()
                + " and got additional damage bonus " + movementResult.getStatusEffect().getDamageBonus() +
                ", speed bonus " + movementResult.getStatusEffect().getStats().getSpeed());
    }

}
