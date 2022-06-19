package com.vsu.actor.movement.combat.damage;


import com.vsu.actor.movement.combat.damage.dataset.Damage;
import com.vsu.map.Vector2;
import com.vsu.utils.GeometryUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class DamageData {
    private Damage damage;
    @Getter
    private double damageDone;

    public boolean isDamaged(Vector2 point) {
        var cartesian = GeometryUtils.fromScreenToCartesianCoordinates(point);
        return damage.isDamaged(cartesian);
    }
}
