package com.vsu.actor.movement;

import com.vsu.actor.model.Character;

public class Dash extends Movement {
    public Dash() {
        type = MovementType.Dash;
    }

    @Override
    public MovementResult apply(Movement prev, Character character) {
        //вычислить направление, куда смотрит персонаж, потом рассчитать координату, на которую нужно сделать сдвиг
        return null;
    }
}
