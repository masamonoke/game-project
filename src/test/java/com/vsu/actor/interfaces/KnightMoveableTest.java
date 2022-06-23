package com.vsu.actor.interfaces;

import com.vsu.actor.model.Actor;
import com.vsu.actor.movement.combat.LightAttack;
import com.vsu.service.game.Game;
import com.vsu.service.handler.MovementHandlerSelector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KnightMoveableTest {
    @Test
    void restoreResourcesTest() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
        var selector = new MovementHandlerSelector();
        var game = new Game();
        selector.handle(actor, new LightAttack(), game);
        actor.restoreResources(actor);
        assertEquals(actor.getStamina(), 19);
        actor.restoreResources(actor);
        //секунда не прошла
        assertEquals(actor.getStamina(), 19);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        actor.restoreResources(actor);
        assertEquals(actor.getStamina(), 20);
    }
}