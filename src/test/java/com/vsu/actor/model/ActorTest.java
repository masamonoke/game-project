package com.vsu.actor.model;

import com.vsu.actor.effect.BlessingBuff;
import com.vsu.actor.interfaces.HumanEquipable;
import com.vsu.actor.interfaces.HumanWalkable;
import com.vsu.actor.interfaces.KnightMoveable;
import com.vsu.actor.movement.combat.LightAttack;
import com.vsu.map.Vector2;
import com.vsu.service.game.Game;
import com.vsu.service.handler.MovementHandlerSelector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ActorTest {
    @Test
    void actorWalkUp1() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
        var startPos = actor.getPos();
        actor.walkUp(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(0, 1)));
    }

    @Test
    void actorWalkUp2() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(2), new KnightMoveable());
        var startPos = actor.getPos();
        actor.walkUp(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(0, 2)));
    }

    @Test
    void actorWalkUp3() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(0.3), new KnightMoveable());
        var startPos = actor.getPos();
        actor.walkUp(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(0, 0.3)));
        actor.walkUp(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(0, 0.6)));
    }

    @Test
    void actorWalkDown1() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
        var startPos = actor.getPos();
        actor.walkDown(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(0, -1)));
    }

    @Test
    void actorWalkDown2() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(2), new KnightMoveable());
        var startPos = actor.getPos();
        actor.walkDown(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(0, -2)));
    }

    @Test
    void actorWalkDown3() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(0.3), new KnightMoveable());
        var startPos = actor.getPos();
        actor.walkDown(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(0, -0.3)));
        actor.walkDown(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(0, -0.6)));
    }

    @Test
    void actorWalkLeft1() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
        var startPos = actor.getPos();
        actor.walkLeft(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(-1, 0)));
    }

    @Test
    void actorWalkLeft2() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(2), new KnightMoveable());
        var startPos = actor.getPos();
        actor.walkLeft(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(-2, 0)));
    }

    @Test
    void actorWalkLeft3() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(0.3), new KnightMoveable());
        var startPos = actor.getPos();
        actor.walkLeft(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(-0.3, 0)));
        actor.walkLeft(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(-0.6, 0)));
    }

    @Test
    void actorWalkRight1() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
        var startPos = actor.getPos();
        actor.walkRight(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(1, 0)));
    }

    @Test
    void actorWalkRight2() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(2), new KnightMoveable());
        var startPos = actor.getPos();
        actor.walkRight(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(2, 0)));
    }

    @Test
    void actorWalkRight3() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(0.3), new KnightMoveable());
        var startPos = actor.getPos();
        actor.walkRight(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(0.3, 0)));
        actor.walkRight(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(0.6, 0)));
    }

    @Test
    void actorWalkUpRight1() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
        var startPos = actor.getPos();
        actor.walkUpRight(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(1, 1)));
    }

    @Test
    void actorWalkUpRight2() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(2), new KnightMoveable());
        var startPos = actor.getPos();
        actor.walkUpRight(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(2, 2)));
    }

    @Test
    void actorWalkUpRight3() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(0.3), new KnightMoveable());
        var startPos = actor.getPos();
        actor.walkUpRight(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(0.3, 0.3)));
        actor.walkUpRight(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(0.6, 0.6)));
    }

    @Test
    void actorWalkUpLeft1() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
        var startPos = actor.getPos();
        actor.walkUpLeft(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(-1, 1)));
    }

    @Test
    void actorWalkUpLeft2() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(2), new KnightMoveable());
        var startPos = actor.getPos();
        actor.walkUpLeft(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(-2, 2)));
    }

    @Test
    void actorWalkUpLeft3() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(0.3), new KnightMoveable());
        var startPos = actor.getPos();
        actor.walkUpLeft(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(-0.3, 0.3)));
        actor.walkUpLeft(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(-0.6, 0.6)));
    }

    @Test
    void actorWalkDownLeft1() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
        var startPos = actor.getPos();
        actor.walkDownLeft(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(-1, -1)));
    }

    @Test
    void actorWalkDownLeft2() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(2), new KnightMoveable());
        var startPos = actor.getPos();
        actor.walkDownLeft(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(-2, -2)));
    }

    @Test
    void actorWalkDownLeft3() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(0.3), new KnightMoveable());
        var startPos = actor.getPos();
        actor.walkDownLeft(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(-0.3, -0.3)));
        actor.walkDownLeft(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(-0.6, -0.6)));
    }

    @Test
    void actorWalkDownRight1() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
        var startPos = actor.getPos();
        actor.walkDownRight(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(1, -1)));
    }

    @Test
    void actorWalkDownRight2() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(2), new KnightMoveable());
        var startPos = actor.getPos();
        actor.walkDownRight(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(2, -2)));
    }

    @Test
    void actorWalkDownRight3() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(0.3), new KnightMoveable());
        var startPos = actor.getPos();
        actor.walkDownRight(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(0.3, -0.3)));
        actor.walkDownRight(actor);
        assertEquals(actor.getPos(), startPos.add(new Vector2(0.6, -0.6)));
    }

    @Test
    void applyStatusEffectTest1() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(0.3), new KnightMoveable());
        var effect = new BlessingBuff();
        actor.applyEffect(effect);
        var s1 = actor.getTemporaryEffect().getStats();
        var s2 = effect.getStats().add(actor.getDefaultStats());
        assertNotEquals(s1, s2);
    }

    @Test
    void applyStatusEffectTest2() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(0.3), new KnightMoveable());
        var effect = new BlessingBuff();
        actor.applyEffect(effect);
        var s1 = actor.getTemporaryEffect().getStats();
        var s2 = effect.getStats().add(actor.getDefaultStats());
        assertNotEquals(s1, s2);
    }

    @Test
    void endStatusEffect1() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(0.3), new KnightMoveable());
        var effect = new BlessingBuff();
        actor.applyEffect(effect);
        try {
            Thread.sleep(effect.getEffectTime());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        actor.endEffect();
        assertEquals(actor.getStats(), actor.getDefaultStats());
    }

    @Test
    void endStatusEffect2() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(0.3), new KnightMoveable());
        var effect = new BlessingBuff();
        actor.applyEffect(effect);
        actor.endEffect();
        assertNotEquals(actor.getStats(), actor.getDefaultStats());
    }

    @Test
    void resourcesTest() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(0.3), new KnightMoveable());
        var selector = new MovementHandlerSelector();
        var game = new Game();
        selector.handle(actor, new LightAttack(), game);
        assertEquals(15, actor.getStamina());
        selector.handle(actor, new LightAttack(), game);
        assertEquals(10, actor.getStamina());
        selector.handle(actor, new LightAttack(), game);
        assertEquals(5, actor.getStamina());
        selector.handle(actor, new LightAttack(), game);
        assertEquals(1, actor.getStamina());
    }
}