package com.vsu.actor.movement;


import com.vsu.actor.model.ActorMobilityState;
import com.vsu.actor.model.Character;

import static com.vsu.App.logger;


public class Jump extends Movement {
    public Jump() {
        type = MovementType.Jump;
    }

    @Override
    public MovementResult apply(Character character) {
        //возможно добавить увеличение урона и увеличить область урона
        logger.info(character + " jumped");
        movementResult = MovementResult
                .builder()
                .newActorMobilityState(ActorMobilityState.Jumping)
                .damageBonus(character.getPhysicalDamage() +
                        character.getStrength() + character.getAgility() + character.getModificationDamage())
                .build();
        return movementResult;
    }
}
