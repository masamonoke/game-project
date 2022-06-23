package com.vsu.actor.movement.combat;

import com.vsu.actor.interfaces.HumanEquipable;
import com.vsu.actor.interfaces.HumanWalkable;
import com.vsu.actor.interfaces.KnightMoveable;
import com.vsu.actor.interfaces.MasterMoveable;
import com.vsu.actor.model.Actor;
import com.vsu.actor.movement.Crouch;
import com.vsu.actor.movement.Jump;
import com.vsu.actor.movement.combat.combo.ComboTree;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class ComboTreeTest {
    @Test
    void traverseTest1() throws IOException {
       var c = new ComboTree();
       var character = new Actor("Lolek", new HumanEquipable(), new HumanWalkable(1), new MasterMoveable());
       var lightAttack = new LightAttack();
       c.traverseTree(lightAttack, character);
       c.traverseTree(lightAttack, character);
       c.traverseTree(lightAttack, character);
       c.finisher(character);
       assertTrue(c.getCur().isLeaf());
    }

    @Test
    void traverseTest2() throws IOException {
        var c = new ComboTree();
        var character = new Actor("Lolek", new HumanEquipable(), new HumanWalkable(1), new MasterMoveable());

        c.traverseTree(new Jump(), character);
        c.traverseTree(new HeavyAttack(), character);
        c.traverseTree(new HeavyAttack(), character);
        c.finisher(character);
        assertTrue(c.getCur().isLeaf());
    }

    @Test
    void traverseTest3() throws IOException {
        var c = new ComboTree();
        var character = new Actor("Lolek", new HumanEquipable(), new HumanWalkable(1), new MasterMoveable());
        c.traverseTree(new LightAttack(), character);
        c.traverseTree(new LightAttack(), character);
        c.traverseTree(new Jump(), character);
        c.finisher(character);
        assertFalse(c.getCur().isLeaf());
    }

    @Test
    void traverseTest4() throws IOException {
        var c = new ComboTree();
        var character = new Actor("Lolek", new HumanEquipable(), new HumanWalkable(1), new MasterMoveable());

        c.traverseTree(new Crouch(), character);
        c.traverseTree(new LightAttack(), character);
        c.traverseTree(new LightAttack(), character);
        c.finisher(character);
        assertTrue(c.getCur().isLeaf());
        c.traverseTree(new HeavyAttack(), character);
        assertFalse(c.getCur().isLeaf());
    }

    @Test
    void traverseTest5() throws IOException {
        var c = new ComboTree();
        var character = new Actor("Lolek", new HumanEquipable(), new HumanWalkable(1), new MasterMoveable());
        c.traverseTree(new Action(), character);
        c.traverseTree(new HeavyAttack(), character);
        c.traverseTree(new LightAttack(), character);
        c.finisher(character);
        assertTrue(c.getCur().isLeaf());
    }

    @Test
    void traverseTest6() throws IOException {
        var c = new ComboTree();
        var character = new Actor("Lolek", new HumanEquipable(), new HumanWalkable(1), new MasterMoveable());

        c.traverseTree(new LightAttack(), character);
        c.traverseTree(new LightAttack(), character);
        c.traverseTree(new HeavyAttack(), character);
        c.finisher(character);
        assertTrue(c.getCur().isLeaf());
    }

    @Test
    void traverseTest7() throws IOException {
        var c = new ComboTree();
        var character = new Actor("Lolek", new HumanEquipable(), new HumanWalkable(1), new MasterMoveable());

        c.traverseTree(new LightAttack(), character);
        c.traverseTree(new LightAttack(), character);
        c.traverseTree(new LightAttack(), character);
        c.traverseTree(new LightAttack(), character);
        c.finisher(character);
        assertTrue(c.getCur().isLeaf());
    }

    @Test
   void traverseTestNullMovement() throws IOException {
       var c = new ComboTree();
       var character = new Actor("Lolek", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
       assertNull(c.traverseTree(null, character));
    }

    @Test
    void traverseWrongCombosetTest() throws IOException {
        var c = new ComboTree("src/main/resources/combat/testcomboset1.txt");
        var character = new Actor("Lolek", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
        c.traverseTree(new LightAttack(), character);
    }

    @Test
    void wrongFilePathCombosetTest() throws IOException {
        var filename = "src/main/resources/combat/testcomboset3.txt";
        var file = new File(filename);
        Files.deleteIfExists(file.toPath());
        var c = new ComboTree(filename);
        var character = new Actor("Lolek", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
        c.traverseTree(new LightAttack(), character);
        Files.deleteIfExists(file.toPath());
    }

    @Test
    void timerTest() throws IOException, InterruptedException {
        var c = new ComboTree();
        var character = new Actor("Lolek", new HumanEquipable(), new HumanWalkable(1), new KnightMoveable());
        var lightAttack = new LightAttack();
        c.traverseTree(lightAttack, character);
        Thread.sleep(2001);
        c.traverseTree(lightAttack, character);
        c.finisher(character);
        assertTrue(c.getCur().isRoot());

        c.traverseTree(lightAttack, character);
        Thread.sleep(1500);
        c.traverseTree(lightAttack, character);
        Thread.sleep(1500);
        c.traverseTree(lightAttack, character);
        c.finisher(character);
        assertTrue(c.getCur().isLeaf());
    }
}