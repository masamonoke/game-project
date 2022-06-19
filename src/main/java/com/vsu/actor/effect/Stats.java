package com.vsu.actor.effect;

import com.vsu.ShallowCopyable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Stats implements ShallowCopyable {
    private int toxic;
    private int strength;
    private int intelligence;
    private int agility;
    private int noise;
    private int trickery;
    private int speed;

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

    public Stats deepCopy() {
        var stats = new Stats();
        stats.toxic = this.toxic;
        stats.strength = this.strength;
        stats.intelligence = this.intelligence;
        stats.agility = this.agility;
        stats.noise = this.noise;
        stats.trickery = this.trickery;
        stats.speed = this.speed;
        return stats;
    }
}
