package com.vsu.actor.effect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

class TemporaryStatusEffectTest {
    @Test
    void readJson() throws IOException {
        var mapper = new ObjectMapper();
        var statusEffect = mapper.readValue(Paths.get("src/main/resources/json/blessedBuffStatusEffect.json").toFile(),
                TemporaryStatusEffect.class);
    }
}