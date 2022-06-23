package com.vsu.service.handler;

import com.vsu.actor.model.Actor;
import com.vsu.actor.movement.Movement;
import com.vsu.service.game.Game;

public abstract class MovementHandler {
    protected String tag;
    public abstract AfterMovementDrawData handle(Actor actor, Movement movement, Game game);
}
