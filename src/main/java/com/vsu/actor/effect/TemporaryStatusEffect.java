package com.vsu.actor.effect;

import com.vsu.ShallowCopyable;
import com.vsu.actor.model.Actor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//по-идее должен через какое-то время спадать
@Getter
@Setter
@AllArgsConstructor
public class TemporaryStatusEffect implements ShallowCopyable {
    protected Stats stats;
    protected int effectTime;
    protected int damageBonus;
    protected long applyTime;

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

    @Override
    public TemporaryStatusEffect deepCopy() {
        var stats = new Stats();
        stats = this.stats.deepCopy();
        var effect = new TemporaryStatusEffect();
        effect.setStats(stats);
        effect.setEffectTime(this.effectTime);
        effect.setDamageBonus(this.damageBonus);
        effect.setApplyTime(this.applyTime);
        return effect;
    }
}
