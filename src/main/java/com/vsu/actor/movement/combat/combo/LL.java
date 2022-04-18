package com.vsu.actor.movement.combat.combo;

import com.scalified.tree.TreeNode;
import com.scalified.tree.multinode.ArrayMultiTreeNode;
import com.vsu.actor.movement.Movement;
import com.vsu.actor.movement.combat.LightAttack;

public class LL implements Moveset {
    public TreeNode<Movement> getMoveset() {
        TreeNode<Movement> root = new ArrayMultiTreeNode<>(new LightAttack());
        TreeNode<Movement> n1 = new ArrayMultiTreeNode<>(new LightAttack());
        root.add(n1);
        return root;
    }
}
