package com.vsu.utils;

import com.vsu.actor.movement.combat.HeavyAttack;
import com.vsu.actor.movement.combat.LightAttack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeNodeTest {
    @Test
    void test1() {
        var root = new TreeNode(new LightAttack("A"));
        var n1 = root.add(new LightAttack("B"));
        var n2 = n1.add(new LightAttack("C"));
        var n3 = n1.add(new HeavyAttack());
        var found = root.find(new LightAttack()).find(new HeavyAttack());
        assertTrue(found.isLeaf());
        assertTrue(root.isRoot());
        assertFalse(n1.isLeaf());
        assertFalse(n1.isRoot());
    }
}