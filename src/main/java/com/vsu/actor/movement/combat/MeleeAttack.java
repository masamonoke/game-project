package com.vsu.actor.movement.combat;

import com.vsu.actor.model.Character;
import com.vsu.actor.movement.Movement;
import com.vsu.actor.movement.MovementResult;
import com.vsu.actor.movement.combat.damage.DamageData;
import com.vsu.actor.movement.combat.damage.dataset.SectorDamageDataset;
import com.vsu.utils.GeometryUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class MeleeAttack extends Movement {
    private double sectorHalfAngle;
    private double radius;

    @Override
    public MovementResult apply(Character character) {
        if (character.getCursorPos() == null) {
            return null;
        }
        var cartesianCharacterPos = GeometryUtils.fromScreenToCartesianCoordinates(character.getPos());
        var cartesianCursorPos = GeometryUtils.fromScreenToCartesianCoordinates(character.getCursorPos()).subtract(cartesianCharacterPos);
        var cursorAngle = GeometryUtils.findVectorAngleByPoint(cartesianCursorPos) * 180 / Math.PI;
        var radius = 50;
        var damageDone = character.getPhysicalDamage() + character.getModificationDamage();
        var damage = new DamageData(new SectorDamageDataset(alpha(cursorAngle), beta(cursorAngle), radius,
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
