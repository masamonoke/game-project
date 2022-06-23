package com.vsu.actor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vsu.actor.effect.Stats;
import com.vsu.actor.effect.TemporaryStatusEffect;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TemporaryStatusEffectTest {
    @Test
    void readJsonTest() throws IOException {
        var mapper = new ObjectMapper();
        var effect = mapper.readValue(
                Paths.get("src/main/resources/json/blessedBuffStatusEffect.json").toFile(),
                TemporaryStatusEffect.class);
        assertEquals(effect.getStats().getAgility(), 10);
        assertEquals(effect.getStats().getIntelligence(), 10);
        assertEquals(effect.getStats().getSpeed(), 10);
        assertEquals(effect.getStats().getToxic(), -10);
        assertEquals(effect.getStats().getTrickery(), 10);
        assertEquals(effect.getStats().getNoise(), -10);
        assertEquals(effect.getStats().getStrength(), 10);
        assertEquals(effect.getEffectTime(), 5000);
        assertEquals(effect.getDamageBonus(), 3);
    }

    @Test
    void addTest() {
        var stats = new Stats(1, 1, 1, 1, 1, 1, 1);
        var effect1 = new TemporaryStatusEffect(stats, 5000, 10, 100000000);
        stats = new Stats(5, 5, 5, 5, 5, 5, 5);
        var effect2 = new TemporaryStatusEffect(stats, 5000, 10, 1124235613);
        effect1.add(effect2);
        stats = new Stats(6, 6, 6, 6, 6, 6, 6);
        assertEquals(effect1.getStats(), stats);
        assertEquals(effect1.getEffectTime(), effect2.getEffectTime());
        assertEquals(effect1.getDamageBonus(), 20);
        assertNotEquals(effect1.getApplyTime(), effect2.getApplyTime());
    }

    @Test
    void deepCopyTest() {
        var stats = new Stats(1, 1, 1, 1, 1, 1, 1);
        var effect1 = new TemporaryStatusEffect(stats, 5000, 10, 100000000);
        var effect2 = new TemporaryStatusEffect(effect1);
        assertEquals(effect1.getEffectTime(), effect2.getEffectTime());
        assertEquals(effect1.getDamageBonus(), effect2.getDamageBonus());
        assertEquals(effect1.getStats(), effect2.getStats());
        assertEquals(effect1.getApplyTime(), effect2.getApplyTime());
    }
}