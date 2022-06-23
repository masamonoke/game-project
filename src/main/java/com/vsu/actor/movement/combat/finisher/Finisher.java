package com.vsu.actor.movement.combat.finisher;

import com.vsu.actor.movement.Movement;
import com.vsu.actor.movement.MovementType;

public abstract class Finisher extends Movement {
    protected FinisherType finisherType;

    public Finisher() {
        movementType = MovementType.Finisher;
    }

    @Override
    public String toString() {
        return finisherType.toString();
    }
}
