package com.vsu.service.handler;

import com.vsu.actor.interfaces.HumanEquipable;
import com.vsu.actor.interfaces.HumanWalkable;
import com.vsu.actor.interfaces.KnightMoveable;
import com.vsu.actor.model.Actor;
import com.vsu.actor.model.ActorStatus;
import com.vsu.actor.movement.Crouch;
import com.vsu.actor.movement.Jump;
import com.vsu.actor.movement.combat.Action;
import com.vsu.actor.movement.combat.HeavyAttack;
import com.vsu.actor.movement.combat.LightAttack;
import com.vsu.game.Game;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MovementHandlerSelectorTest {
    @Test
    void handleTest1() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
        actor.setPos(50, 50);
        var selector = new MovementHandlerSelector();
        var game = new Game();
        var enemy = new Actor("enemy", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
        enemy.setPos(49, 49);
        game.getActors().add(enemy);
        selector.handle(actor, new LightAttack(), game);
        assertEquals(enemy.getHp(), 16);
        selector.handle(actor, new LightAttack(), game);
        assertEquals(enemy.getHp(), 12);
        selector.handle(actor, new LightAttack(), game);
        assertEquals(enemy.getHp(), 8);
        selector.handle(actor, new LightAttack(), game);
        assertEquals(enemy.getStatus(), ActorStatus.Dead);
    }

    @Test
    void handleTest2() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
        actor.setPos(50, 50);
        var selector = new MovementHandlerSelector();
        var game = new Game();
        var enemy1 = new Actor("enemy1", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
        enemy1.setPos(49, 49);
        var enemy2 = new Actor("enemy2", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
        enemy2.setPos(25, 25);
        var enemy3 = new Actor("enemy3", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
        enemy3.setPos(51, 49);
        game.getActors().addAll(List.of(new Actor[]{enemy1, enemy2, enemy3}));
        selector.handle(actor, new LightAttack(), game);
        assertEquals(enemy1.getHp(), 16);
        assertEquals(enemy2.getHp(), 16);
        selector.handle(actor, new LightAttack(), game);
        assertEquals(enemy1.getHp(), 12);
        assertEquals(enemy2.getHp(), 12);
        selector.handle(actor, new LightAttack(), game);
        assertEquals(enemy1.getHp(), 8);
        assertEquals(enemy2.getHp(), 8);
        selector.handle(actor, new LightAttack(), game);
        assertEquals(enemy1.getStatus(), ActorStatus.Dead);
        assertEquals(enemy2.getStatus(), ActorStatus.Dead);
        assertEquals(enemy3.getStatus(), ActorStatus.Alive);
    }

    @Test
    void handleTest3() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
        var selector = new MovementHandlerSelector();
        var game = new Game();
        selector.handle(actor, new Jump(), game);
        selector.handle(actor, new HeavyAttack(), game);
        selector.handle(actor, new HeavyAttack(), game);
        assertTrue(game.getComboTree().getCur().isLeaf());
    }

    @Test
    void handleTest4() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
        var selector = new MovementHandlerSelector();
        var game = new Game();
        selector.handle(actor, new Crouch(), game);
        selector.handle(actor, new LightAttack(), game);
        selector.handle(actor, new LightAttack(), game);
        assertTrue(game.getComboTree().getCur().isLeaf());
    }

    @Test
    void handleTest5() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
        var selector = new MovementHandlerSelector();
        var game = new Game();
        selector.handle(actor, new LightAttack(), game);
        selector.handle(actor, new HeavyAttack(), game);
        selector.handle(actor, new LightAttack(), game);
        assertTrue(game.getComboTree().getCur().isLeaf());
    }

    @Test
    void handleTest6() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
        var selector = new MovementHandlerSelector();
        var game = new Game();
        selector.handle(actor, new HeavyAttack(), game);
        selector.handle(actor, new HeavyAttack(), game);
        selector.handle(actor, new LightAttack(), game);
        assertTrue(game.getComboTree().getCur().isLeaf());
    }

    @Test
    void handleTest7() {
        var actor = new Actor("actor", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
        var selector = new MovementHandlerSelector();
        var game = new Game();
        selector.handle(actor, new Action(), game);
        selector.handle(actor, new HeavyAttack(), game);
        selector.handle(actor, new LightAttack(), game);
        assertTrue(game.getComboTree().getCur().isLeaf());
    }
}