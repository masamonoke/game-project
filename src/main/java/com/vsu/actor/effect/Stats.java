package com.vsu.actor.effect;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Stats {
    private int toxic;
    private int strength;
    private int intelligence;
    private int agility;
    private int noise;
    private int trickery;
    private int speed;

    public Stats(Stats copyFrom) {
        toxic = copyFrom.toxic;
        strength = copyFrom.strength;
        intelligence = copyFrom.intelligence;
        agility = copyFrom.agility;
        noise = copyFrom.noise;
        trickery = copyFrom.trickery;
        speed = copyFrom.speed;
    }

    public Stats add(Stats other) {
        toxic += other.getToxic();
        strength += other.getStrength();
        intelligence += other.getIntelligence();
        agility += other.getAgility();
        noise += other.getNoise();
        trickery += other.getTrickery();
        speed += other.getSpeed();
        return this;
    }
}
