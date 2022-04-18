package com.vsu.actor.model;

import com.vsu.actor.movement.combat.DamageArea;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Actor {
    private int hp;
    private int stamina;
    private int mana;
    private int speed;
    private int damage;
    private int noise;
    private DamageArea damageArea;
}
