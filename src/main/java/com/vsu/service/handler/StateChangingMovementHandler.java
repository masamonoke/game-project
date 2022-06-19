package com.vsu.service.handler;

import com.vsu.actor.model.Actor;
import com.vsu.actor.movement.Crouch;
import com.vsu.actor.movement.Jump;
import com.vsu.actor.movement.Movement;
import com.vsu.actor.movement.MovementResult;
import com.vsu.game.Game;

public class StateChangingMovementHandler extends MovementHandler {

    public StateChangingMovementHandler() {
        tag = "Jump;Crouch";
    }

    @Override
    public AfterMovementDrawData handle(Actor actor, Movement movement, Game game) {
        if (!(movement instanceof Jump) && !(movement instanceof Crouch)) {
            return null;
        }
        if (!movement.takeResources(actor)) {
            return null;
        }
        var result = game.getComboTree().traverseTree(movement, actor);
        actor.applyEffect(result.getStatusEffect());
        MovementResult finisher = null;
        if (game.getComboTree().getFinisher() != null) {
            finisher = game.getComboTree().finisher(actor);
            actor.applyEffect(finisher.getStatusEffect());
        }
        return AfterMovementDrawData.builder().movementResult(result).finisherResult(finisher).build();
    }
}
