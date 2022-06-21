package com.vsu.actor.movement.combat;

import com.vsu.actor.model.Actor;
import com.vsu.actor.movement.Movement;
import com.vsu.actor.movement.MovementResult;
import com.vsu.actor.movement.MovementType;

import static com.vsu.App.logger;

public class Action extends Movement {

    public Action() {
        price = 0;
        movementType = MovementType.Action;
    }

    //todo: доделать - добавить экшенов, например, покрыть оружие ядом, съесть плюшку и тд
    @Override
    public MovementResult apply(Actor actor) {
        log(actor);
        return MovementResult.builder().build();
    }

    @Override
    public void log(Actor actor) {
        logger.info(actor + " made " + this);
    }
}
