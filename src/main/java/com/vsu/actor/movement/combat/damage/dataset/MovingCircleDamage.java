package com.vsu.actor.movement.combat.damage.dataset;

import com.vsu.map.Vector2;
import lombok.Getter;

@Getter
public class MovingCircleDamage extends CircleDamage {
    private final Vector2 startPos;
    private final Vector2 endPos;

    public MovingCircleDamage(Vector2 startPos, Vector2 endPos, double radius) {
        super(radius, startPos);
        this.startPos = startPos;
        this.endPos = endPos;
    }

}
