package com.vsu.actor.interfaces;

import com.vsu.actor.model.Actor;
import com.vsu.actor.movement.Movement;

public interface Moveable {
    void move(Movement movement, Actor actor);

    void restoreResources(Actor actor);
}
