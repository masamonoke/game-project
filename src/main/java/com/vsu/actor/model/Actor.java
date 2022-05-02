package com.vsu.actor.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Actor {
    protected int hp;
    protected int mp;
    protected int stamina;
    protected int speed;
    protected int physicalDamage;
    protected int magicalDamage;
}
