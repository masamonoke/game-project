package com.vsu.service.handler;

import com.vsu.actor.model.Actor;
import com.vsu.actor.movement.MovementResult;
import com.vsu.map.Vector2;
import lombok.Builder;

import java.util.List;

//данные для отрисовки
@Builder
public class AfterMovementDrawData {
    private MovementResult movementResult;
    private MovementResult finisherResult;
    private List<Actor> diedActors;
    private List<Actor> damagedActors;
    private List<Vector2> path;
}
