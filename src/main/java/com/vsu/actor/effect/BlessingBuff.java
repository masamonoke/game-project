package com.vsu.actor.effect;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;

public class BlessingBuff extends TemporaryStatusEffect {
    public BlessingBuff() {
        var mapper = new ObjectMapper();
        TemporaryStatusEffect statusEffect;
        try {
            statusEffect = mapper.readValue
                    (Paths.get("src/main/resources/json/blessedBuffStatusEffect.json").toFile(),
                    TemporaryStatusEffect.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.stats = new Stats(statusEffect.stats);
        this.effectTime = statusEffect.effectTime;
        this.damageBonus = statusEffect.damageBonus;
        this.applyTime = System.currentTimeMillis();
    }
}
