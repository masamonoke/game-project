package com.vsu.actor.movement;

import com.vsu.actor.effect.TemporaryStatusEffect;
import com.vsu.actor.model.ActorMobilityState;
import com.vsu.actor.movement.combat.damage.DamageData;
import com.vsu.map.Vector2;
import lombok.*;

//при добавлении новых мувов нужно будет обновлять этот класс
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovementResult {
    private DamageData damageData;
    private Vector2 newMoveLocation;
    private ActorMobilityState newActorMobilityState;
    private MovementType movementType;
    private TemporaryStatusEffect statusEffect;

    public MovementResult(MovementResult copyFrom) {
        damageData = copyFrom.damageData;
        movementType = copyFrom.movementType;
        newMoveLocation = copyFrom.newMoveLocation;
        newActorMobilityState = copyFrom.newActorMobilityState;
        statusEffect = copyFrom.statusEffect;
    }

}
