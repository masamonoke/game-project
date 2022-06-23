package com.vsu.actor.interfaces;

import com.vsu.actor.model.Actor;
import com.vsu.actor.movement.*;
import com.vsu.actor.movement.combat.Action;
import com.vsu.actor.movement.combat.HeavyAttack;
import com.vsu.actor.movement.combat.LightAttack;
import com.vsu.actor.movement.combat.combo.ComboTree;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

import static com.vsu.App.logger;

public class MasterMoveable implements Moveable {
    private final List<Movement> movements;
    private long prevTime;
    protected ComboTree comboTree;

    @SneakyThrows
    public MasterMoveable() {
        movements = new ArrayList<>();
        movements.add(new LightAttack());
        movements.add(new HeavyAttack());
        movements.add(new Dash());
        movements.add(new Action());
        movements.add(new Jump());
        movements.add(new Crouch());
        prevTime = 0;
        comboTree = new ComboTree("src/main/resources/combat/updatedComboset.txt");
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

    @Override
    public ComboTree getComboTree() {
        return comboTree;
    }
}
