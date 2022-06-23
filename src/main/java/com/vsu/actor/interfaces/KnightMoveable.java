package com.vsu.actor.interfaces;

import com.vsu.actor.model.Actor;
import com.vsu.actor.movement.Dash;
import com.vsu.actor.movement.Movement;
import com.vsu.actor.movement.MovementResult;
import com.vsu.actor.movement.combat.HeavyAttack;
import com.vsu.actor.movement.combat.LightAttack;

import java.util.ArrayList;
import java.util.List;

import static com.vsu.App.logger;

public class KnightMoveable extends MasterMoveable implements Moveable {
    private final List<Movement> movements;

    public KnightMoveable() {
        movements = new ArrayList<>();
        movements.add(new LightAttack());
        movements.add(new HeavyAttack());
        movements.add(new Dash());
    }

    @Override
    public MovementResult move(Movement movement, Actor actor) {
        if (!movements.contains(movement)) {
            logger.info(movement + " cannot be used by " + actor);
            return null;
        }
        if (!movement.takeResources(actor)) {
            return null;
        }
        return movement.apply(actor);
    }

    //восстановление ресурсов каждую секунду
    @Override
    public void restoreResources(Actor actor) {
        super.restoreResources(actor);
    }
}
