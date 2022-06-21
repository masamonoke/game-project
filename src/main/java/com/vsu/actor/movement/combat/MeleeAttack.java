package com.vsu.actor.movement.combat;

import com.vsu.actor.model.Actor;
import com.vsu.actor.movement.Movement;
import com.vsu.actor.movement.MovementResult;
import com.vsu.actor.movement.combat.damage.DamageData;
import com.vsu.actor.movement.combat.damage.dataset.SectorDamage;
import com.vsu.utils.GeometryUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class MeleeAttack extends Movement {
    private double sectorHalfAngle;
    private double radius;

    @Override
    public MovementResult apply(Actor actor) {
        if (actor.getCursorPos() == null) {
            return null;
        }
        var cartesianCharacterPos = GeometryUtils.fromScreenToCartesianCoordinates(actor.getPos());
        var cartesianCursorPos = GeometryUtils.fromScreenToCartesianCoordinates(actor.getCursorPos()).subtract(cartesianCharacterPos);
        var cursorAngle = GeometryUtils.findVectorAngleByPoint(cartesianCursorPos) * 180 / Math.PI;
        //TODO: от константы избавиться
        var radius = 50;
        var damageDone = actor.getPhysicalDamage() + actor.getModificationDamage() + actor.getStrength() + actor.getAgility();
        var damage = new DamageData(new SectorDamage(alpha(cursorAngle), beta(cursorAngle), radius,
                cartesianCharacterPos), damageDone);
        movementResult = MovementResult
                .builder()
                .damageData(damage)
                .build();
        return movementResult;
    }

    private double alpha(double angle) {
        return angle - sectorHalfAngle;
    }

    private double beta(double angle) {
        return angle + sectorHalfAngle;
    }
}
