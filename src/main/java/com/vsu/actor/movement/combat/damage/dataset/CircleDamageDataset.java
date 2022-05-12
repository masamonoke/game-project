package com.vsu.actor.movement.combat.damage.dataset;

import com.vsu.map.Vector2;
import com.vsu.utils.GeometryUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class CircleDamageDataset implements DamageDataset {
    public double radius;
    public Vector2 center;

    @Override
    public boolean isDamaged(Vector2 p) {
        return GeometryUtils.isInCircle(p, center, radius);
    }
}
