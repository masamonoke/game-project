package com.vsu.service.handler;

import com.vsu.actor.model.Actor;
import com.vsu.actor.movement.Crouch;
import com.vsu.actor.movement.Jump;
import com.vsu.actor.movement.Movement;
import com.vsu.actor.movement.MovementResult;
import com.vsu.service.game.Game;

public class StateChangingMovementHandler extends MovementHandler {

    public StateChangingMovementHandler() {
        tag = "Jump;Crouch";
    }

    @Override
    public AfterMovementDrawData handle(Actor actor, Movement movement, Game game) {
        if (!(movement instanceof Jump) && !(movement instanceof Crouch)) {
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
        return AfterMovementDrawData.builder().movementResult(result).finisherResult(finisher).build();
    }
}
