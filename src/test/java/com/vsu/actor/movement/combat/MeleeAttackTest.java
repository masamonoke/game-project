package com.vsu.actor.movement.combat;

import com.vsu.actor.model.Character;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeleeAttackTest {
    @Test
    void attackTest() {
        var char1 = new Character(  "char1");
        var char2 = new Character("char2");
        char1.setPos(52, 43);
        char1.setCursorPos(52, 60);
        char2.setPos(52, 46);
        var lightAttack = new LightAttack();
        var result = lightAttack.apply(char1);
        assertTrue(result.getDamageData().isDamaged(char2.getPos()));
        char2.setPos(52, 94);

        result = lightAttack.apply(char1);
        assertFalse(result.getDamageData().isDamaged(char2.getPos()));

        //если char2 находится прямо сзади char1
        char2.setPos(52, 42);
        assertFalse(result.getDamageData().isDamaged(char2.getPos()));

        var heavyAttack = new HeavyAttack();
        result = heavyAttack.apply(char1);
        char2.setPos(52, 90);
        assertTrue(result.getDamageData().isDamaged(char2.getPos()));
    }
}