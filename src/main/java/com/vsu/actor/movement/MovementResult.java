package com.vsu.actor.movement;

import com.vsu.actor.model.ActorMobilityState;
import com.vsu.actor.movement.combat.Damage;
import com.vsu.map.Vector2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class MovementResult {
    private Damage damageDone;
    private Vector2 newMoveLocation;
    private ActorMobilityState newActorMobilityState;
    private int newDamage;
    private int newSpeed;

    public MovementResult() {
        damageDone = null;
        newMoveLocation = null;
        newActorMobilityState = null;
        newDamage = 0;
        newSpeed = 0;
    }
}
