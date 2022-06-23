package com.vsu.actor.interfaces;

import com.vsu.actor.model.Actor;
import com.vsu.actor.movement.Movement;
import com.vsu.actor.movement.MovementResult;
import com.vsu.actor.movement.combat.combo.ComboTree;

public interface Moveable {
    MovementResult move(Movement movement, Actor actor);

    void restoreResources(Actor actor);

    ComboTree getComboTree();
}
