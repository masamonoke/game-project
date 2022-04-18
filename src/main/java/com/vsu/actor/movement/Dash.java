package com.vsu.actor.movement;

public class Dash extends Movement {
    public Dash() {
        type = MovementType.Dash;
    }

    @Override
    public Movement move(Movement prev) {
        return null;
    }
}
