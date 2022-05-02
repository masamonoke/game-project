package com.vsu.actor.movement.combat;

import com.vsu.actor.model.Character;
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
    void traverseTest() throws IOException {
       var c = new ComboTree();
       var character = new Character("Lolek");
       var lightAttack = new LightAttack();
       c.traverse(lightAttack, character);
       c.traverse(lightAttack, character);
       assertTrue(c.traverse(lightAttack, character).isLeaf());


       c.traverse(new Jump(), character);
       c.traverse(new HeavyAttack(), character);
       assertTrue(c.traverse(new HeavyAttack(), character).isLeaf());

       c.traverse(new LightAttack(), character);
       c.traverse(new LightAttack(), character);
       assertFalse(c.traverse(new Jump(), character).isLeaf());

       c.traverse(new Crouch(), character);
       c.traverse(new LightAttack(), character);
       assertTrue(c.traverse(new LightAttack(), character).isLeaf());
       assertFalse(c.traverse(new HeavyAttack(), character).isLeaf());
    }

    @Test
   void traverseTestNullMovement() throws IOException {
       var c = new ComboTree();
       var character = new Character("Lolek");
       assertNull(c.traverse(null, character));
    }

    @Test
    void traverseWrongCombosetTest() throws IOException {
        var c = new ComboTree("src/main/resources/combat/testcomboset1.txt");
        var character = new Character("Lolek");
        c.traverse(new LightAttack(), character);
    }

    @Test
    void lowerCaseCombosetTest() throws IOException {
        var c = new ComboTree("src/main/resources/combat/testcomboset2.txt");
        var character = new Character("Lolek");
        var lightAttack = new LightAttack();
        c.traverse(lightAttack, character);
        c.traverse(lightAttack, character);
        var endMovement = c.traverse(lightAttack, character);
        assertFalse(endMovement.isRoot());
        assertTrue(endMovement.isLeaf());

        var heavyAttack = new HeavyAttack();
        c.traverse(heavyAttack, character);
        c.traverse(lightAttack, character);
        endMovement = c.traverse(heavyAttack, character);
        assertFalse(endMovement.isRoot());
        assertTrue(endMovement.isLeaf());
    }

    @Test
    void wrongFilePathCombosetTest() throws IOException {
        var filename = "src/main/resources/combat/testcomboset3.txt";
        var file = new File(filename);
        Files.deleteIfExists(file.toPath());
        var c = new ComboTree(filename);
        var character = new Character("Lolek");
        c.traverse(new LightAttack(), character);
        Files.deleteIfExists(file.toPath());
    }
}