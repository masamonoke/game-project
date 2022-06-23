package com.vsu.actor.movement.combat;

import com.vsu.actor.interfaces.HumanEquipable;
import com.vsu.actor.interfaces.HumanWalkable;
import com.vsu.actor.interfaces.KnightMoveable;
import com.vsu.actor.model.Actor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//todo: добавить тестов
class MeleeAttackTest {
    @Test
    void attackTest() {
        var char1 = new Actor( "char1", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
        var char2 = new Actor("char2", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
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