package com.vsu.service.handler;

import com.vsu.actor.model.Actor;
import com.vsu.actor.movement.Movement;
import com.vsu.actor.movement.MovementResult;
import com.vsu.actor.movement.combat.Action;
import com.vsu.service.game.Game;

//todo: переделать в соответствии с добавленными экшенами
public class ActionMovementHandler extends MovementHandler {
    public ActionMovementHandler() {
        tag = "Action";
    }

    @Override
    public AfterMovementDrawData handle(Actor actor, Movement movement, Game game) {
        if (!(movement instanceof Action)) {
            return null;
        }
        var result = actor.getComboTree().traverseTree(movement, actor);
        if (result == null) {
            return null;
        }
        MovementResult finisher = null;
        if (actor.getComboTree().getFinisher() != null) {
            finisher = actor.getComboTree().finisher(actor);
            actor.applyEffect(finisher.getStatusEffect());
        }
        return AfterMovementDrawData.builder().movementResult(result).finisherResult(finisher).build();
    }
}
