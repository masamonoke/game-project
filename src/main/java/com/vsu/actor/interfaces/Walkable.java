package com.vsu.actor.interfaces;

import com.vsu.actor.model.Actor;

public interface Walkable {
    void walkUp(Actor actor);
    void walkDown(Actor actor);
    void walkLeft(Actor actor);
    void walkRight(Actor actor);
    void walkUpRight(Actor actor);
    void walkUpLeft(Actor actor);
    void walkDownRight(Actor actor);
    void walkDownLeft(Actor actor);
}
