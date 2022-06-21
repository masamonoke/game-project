package com.vsu.actor.movement.combat.damage.dataset;

import com.vsu.map.Vector2;
import com.vsu.utils.GeometryUtils;
import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public class CircleDamage implements Damage {
    protected double radius;
    @Setter
    protected Vector2 center;

    @Override
    public boolean isDamaged(Vector2 p) {
        return GeometryUtils.isInCircle(p, center, radius);
    }
}
