package com.vsu.service.handler;

import com.vsu.actor.model.Actor;
import com.vsu.actor.movement.Movement;
import com.vsu.actor.movement.MovementResult;
import com.vsu.actor.movement.combat.MeleeAttack;
import com.vsu.service.game.Game;
import com.vsu.utils.MathUtils;

import java.util.ArrayList;

import static com.vsu.App.logger;

public class MeleeAttackHandler extends MovementHandler {

    public MeleeAttackHandler() {
        tag = "LightAttack;HeavyAttack";
    }

    @Override
    public AfterMovementDrawData handle(Actor actor, Movement movement, Game game) {
        if (!(movement instanceof MeleeAttack)) {
            return null;
        }
        var result = actor.getComboTree().traverseTree(movement, actor);
        if (result == null) {
            return null;
        }
        MovementResult finisher = null;
        if (actor.getComboTree().getFinisher() != null) {
            finisher = actor.getComboTree().finisher(actor);
        }
        var diedActors = new ArrayList<Actor>();
        var damagedActors = new ArrayList<Actor>();
        for (var c : game.getActors()) {
            if (result.getDamageData().isDamaged(c.getPos())) {
                var dmg = (int) MathUtils.round(result.getDamageData().getDamageDone(), 0);
                c.changeHp(-dmg);
                damagedActors.add(actor);
                logger.info(actor + " damaged " + c + " and " + c + " have " + c.getHp() + " hp left");
                if (c.getHp() <= 0) {
                    logger.info(c + " died");
                    diedActors.add(c);
                }
            }
        }
        game.getActors().removeAll(diedActors);
        return AfterMovementDrawData
                .builder()
                .movementResult(result)
                .diedActors(diedActors)
                .damagedActors(damagedActors)
                .finisherResult(finisher)
                .build();
    }
}
