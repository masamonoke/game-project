package com.vsu.actor.effect;

import com.vsu.actor.model.Actor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//по-идее должен через какое-то время спадать
@Getter
@Setter
@AllArgsConstructor
public class TemporaryStatusEffect {
    protected Stats stats;
    protected int effectTime;
    protected int damageBonus;
    protected long applyTime;

    public TemporaryStatusEffect(TemporaryStatusEffect copyFrom) {
        stats = new Stats(copyFrom.stats);
        effectTime = copyFrom.effectTime;
        damageBonus = copyFrom.damageBonus;
        applyTime = copyFrom.applyTime;
    }

    public TemporaryStatusEffect() {
        stats = new Stats();
    }

    public void apply(Actor actor) {
        int value;
        value = actor.getToxic() + stats.getToxic();
        actor.setToxic(Math.max(value, 1));
        value = actor.getStrength() + stats.getStrength();
        actor.setStrength(Math.max(value, 1));
        value = actor.getIntelligence() + stats.getIntelligence();
        actor.setIntelligence(Math.max(value, 1));
        value = actor.getAgility() + stats.getAgility();
        actor.setAgility(Math.max(value, 1));
        value = actor.getNoise() + stats.getNoise();
        actor.setNoise(Math.max(value, 1));
        value = actor.getTrickery() + stats.getTrickery();
        actor.setTrickery(Math.max(value, 1));
        value = actor.getSpeed() + stats.getSpeed();
        actor.setSpeed(Math.max(value, 1));
        actor.setModificationDamage(damageBonus);
    }

    public void add(TemporaryStatusEffect other) {
        stats.add(other.stats);
        effectTime = other.effectTime;
        damageBonus += other.damageBonus;
    }
}
