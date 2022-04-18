package com.vsu.actor.movement.combat.combo;

import com.scalified.tree.TreeNode;
import com.scalified.tree.multinode.ArrayMultiTreeNode;
import com.vsu.actor.movement.Movement;
import com.vsu.actor.movement.combat.HeavyAttack;
import com.vsu.actor.movement.combat.LightAttack;

public class LH implements Moveset {
    @Override
    public TreeNode<Movement> getMoveset() {
        TreeNode<Movement> root = new ArrayMultiTreeNode<>(new LightAttack());
        TreeNode<Movement> n1 = new ArrayMultiTreeNode<>(new HeavyAttack());
        root.add(n1);
        return root;
    }
}
