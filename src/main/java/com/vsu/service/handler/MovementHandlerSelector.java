package com.vsu.service.handler;

import com.vsu.actor.model.Actor;
import com.vsu.actor.movement.Movement;
import com.vsu.game.Game;

import java.util.ArrayList;
import java.util.List;

import static com.vsu.App.logger;

public class MovementHandlerSelector extends MovementHandler {

    private final List<MovementHandler> movementHandlers;

    public MovementHandlerSelector() {
        movementHandlers = new ArrayList<>();
        movementHandlers.add(new DashHandler());
        movementHandlers.add(new MeleeAttackHandler());
        movementHandlers.add(new StateChangingMovementHandler());
        movementHandlers.add(new ActionMovementHandler());
    }

    @Override
    public AfterMovementDrawData handle(Actor actor, Movement movement, Game game) {
        var handler = movementHandlers.stream().filter(m -> m.tag.contains(movement.toString()))
                .findFirst().orElse(null);
        if (handler != null) {
            return handler.handle(actor, movement, game);
        } else {
            logger.error("Handler for movement " + movement + " not found: " + this.getClass());
        }
        return null;
    }
}
