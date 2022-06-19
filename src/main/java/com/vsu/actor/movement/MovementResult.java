package com.vsu.actor.movement;

import com.vsu.ShallowCopyable;
import com.vsu.actor.effect.TemporaryStatusEffect;
import com.vsu.actor.model.ActorMobilityState;
import com.vsu.actor.movement.combat.damage.DamageData;
import com.vsu.map.Vector2;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovementResult implements ShallowCopyable {
    private DamageData damageData;
    private Vector2 newMoveLocation;
    private ActorMobilityState newActorMobilityState;
    private MovementType movementType;
    //TODO: бонусы и пенальти заменить на один класс StatusEffect
    //TODO: damageBonus можно расчитать исходя из StatusEffect
    private TemporaryStatusEffect statusEffect;

    //todo: добавить StatusEffect и тест
    public MovementResult deepCopy() {
        var other = new MovementResult();
        other.damageData = this.damageData;
        other.movementType = this.movementType;
        other.newMoveLocation = this.newMoveLocation;
        other.newActorMobilityState = this.newActorMobilityState;
        return other;
    }
}
