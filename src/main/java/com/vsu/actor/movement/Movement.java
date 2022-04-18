package com.vsu.actor.movement;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class Movement {
    private Movement next;
    protected MovementType type;

    public abstract Movement move(Movement prev);

    protected enum MovementType {
        Attack,
        HeavyAttack,
        LightAttack,
        Jump,
        Dash,
        Crouch
    }
}
