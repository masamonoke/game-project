package com.vsu.actor.movement;

import com.vsu.actor.model.ActorMobilityState;
import com.vsu.actor.model.Character;

import static com.vsu.App.logger;

public class Dash extends Movement {
    public Dash() {
        type = MovementType.Dash;
    }

    @Override
    public MovementResult apply(Character character) {
        var distance = 10 * (character.getAgility() + character.getTrickery() + character.getStrength());
        movementResult = MovementResult
                .builder()
                .newMoveLocation(character.getPos()
                        .getMiddlePointFromDistanceAndEndPoint(character.getCursorPos(), distance))
                .damageBonus(character.getAgility())
                .speedBonus(character.getAgility() * 3)
                //вообще говоря нужно проверить, что персонаж не под эффектом контроля
                // или же тогда дэш снимает все эффекты контроля и тогда все ок
                .newActorMobilityState(ActorMobilityState.Sprinting)
                .build();
        logger.info(character + " made dash to " + movementResult.getNewMoveLocation()
                + " and got additional damage bonus " + movementResult.getDamageBonus() +
                ", speed bonus " + movementResult.getSpeedBonus());
        return movementResult;
    }
}
