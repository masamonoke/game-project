package com.vsu.actor.interfaces;

import com.vsu.actor.model.Actor;
import com.vsu.actor.movement.Dash;
import com.vsu.actor.movement.Movement;
import com.vsu.actor.movement.combat.HeavyAttack;
import com.vsu.actor.movement.combat.LightAttack;

import java.util.ArrayList;
import java.util.List;

public class KnightMoveable implements Moveable {
    private final List<Movement> movements;
    private long prevTime;

    public KnightMoveable() {
        movements = new ArrayList<>();
        movements.add(new LightAttack());
        movements.add(new HeavyAttack());
        movements.add(new Dash());
        prevTime = 0;
    }

    @Override
    public void move(Movement movement, Actor actor) {
        if (!movements.contains(movement)) {
            return;
        }
        var res = movement.apply(actor);
        movement.takeResources(actor);
    }

    //восстановление ресурсов каждую секунду
    @Override
    public void restoreResources(Actor actor) {
        var curTime = System.currentTimeMillis();
        if (curTime - prevTime >= 1000) {
            var staminaChange = (int) (2 * actor.getStrength() / 0.5);
            actor.changeStamina(staminaChange);
            var mpChange = (int) (2 * actor.getIntelligence() / 0.5);
            actor.changeMp(mpChange);
            var hpChange = 2;
            actor.changeHp(hpChange);
        }
        prevTime = curTime;
    }
}
