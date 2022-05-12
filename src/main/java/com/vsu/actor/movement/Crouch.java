package com.vsu.actor.movement;

import com.vsu.actor.model.ActorMobilityState;
import com.vsu.actor.model.Character;

import static com.vsu.App.logger;

public class Crouch extends Movement {
    public Crouch() {
        type = MovementType.Crouch;
    }

    @Override
    public MovementResult apply(Character character) {
        logger.info(character + " is crouched");
        //возможно увеличить урон от определенных типов оружия (кинжалы), пока персонаж находится в этом состоянии
        movementResult = MovementResult
                .builder()
                .newActorMobilityState(ActorMobilityState.Crouching)
                .damageBonus(character.getPhysicalDamage() * character.getTrickery() & character.getAgility())
                .build();
        return movementResult;
    }
}
