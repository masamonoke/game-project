package com.vsu.actor.movement.combat.finisher;

import com.vsu.actor.effect.BlessingBuff;
import com.vsu.actor.model.Actor;
import com.vsu.actor.movement.MovementResult;

import static com.vsu.App.logger;

public class StrengthBuff extends Finisher {
    public StrengthBuff() {
        super();
        finisherType = FinisherType.StrengthBuff;
    }

    @Override
    public MovementResult apply(Actor actor) {
        log(actor);
        var buff = new BlessingBuff();
        actor.applyEffect(buff);
        return MovementResult.builder().build();
    }

    @Override
    public String toString() {
        return finisherType.toString();
    }

    @Override
    public void log(Actor actor) {
        logger.info(actor + " got " + this);
    }
}
