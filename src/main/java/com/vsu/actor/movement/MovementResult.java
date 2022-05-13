package com.vsu.actor.movement;

import com.vsu.actor.model.ActorMobilityState;
import com.vsu.actor.movement.combat.damage.DamageData;
import com.vsu.map.Vector2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class MovementResult {
    private DamageData damageData;
    private Vector2 newMoveLocation;
    private ActorMobilityState newActorMobilityState;
    private int damageBonus;
    private int speedBonus;
    private int speedPenalty;
}
