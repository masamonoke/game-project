package com.vsu.service.handler;

import com.vsu.actor.model.Actor;
import com.vsu.actor.movement.Dash;
import com.vsu.actor.movement.Movement;
import com.vsu.actor.movement.MovementResult;
import com.vsu.service.game.Game;
import com.vsu.utils.GeometryUtils;

public class DashHandler extends MovementHandler {

    public DashHandler() {
        tag = "Dash";
    }

    @Override
    public AfterMovementDrawData handle(Actor actor, Movement movement, Game game) {
        if (!(movement instanceof Dash)) {
            return null;
        }
        var result = actor.getComboTree().traverseTree(movement, actor);
        if (result == null) {
            return null;
        }
        actor.applyEffect(result.getStatusEffect());
        MovementResult finisher = null;
        if (actor.getComboTree().getFinisher() != null) {
            finisher = actor.getComboTree().finisher(actor);
            actor.applyEffect(finisher.getStatusEffect());
        }
        var step = ((actor.getStrength() + actor.getAgility()) * 2);
        if (step > 15) {
            step = 15;
        }
        var path = GeometryUtils.getPathToDestination(actor.getPos(), result.getNewMoveLocation(), step);
        return AfterMovementDrawData.builder().movementResult(result).path(path).finisherResult(finisher).build();
    }
}
