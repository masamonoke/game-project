package com.vsu.actor.movement.combat.damage;


import com.vsu.actor.movement.combat.damage.dataset.DamageDataset;
import com.vsu.map.Vector2;
import com.vsu.utils.GeometryUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DamageData {
    private DamageDataset damageDataset;
    private double damageDone;

    public boolean isDamaged(Vector2 point) {
        var cartesian = GeometryUtils.fromScreenToCartesianCoordinates(point);
        return damageDataset.isDamaged(cartesian);
    }
}
