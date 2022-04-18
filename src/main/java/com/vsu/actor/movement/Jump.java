package com.vsu.actor.movement;


public class Jump extends Movement {
    public Jump() {
        type = MovementType.Jump;
    }

    @Override
    public Movement move(Movement prev) {
        return null;
    }
}
