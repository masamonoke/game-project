package com.vsu.actor.movement.combat.combo;

import com.scalified.tree.TreeNode;
import com.scalified.tree.multinode.ArrayMultiTreeNode;
import com.vsu.actor.movement.Crouch;
import com.vsu.actor.movement.Jump;
import com.vsu.actor.movement.Movement;
import com.vsu.actor.movement.combat.Action;
import com.vsu.actor.movement.combat.HeavyAttack;
import com.vsu.actor.movement.combat.LightAttack;
import lombok.Getter;


@Getter
public class ComboTree {
    private final TreeNode<Movement> root;

    public ComboTree() {
        root = new ArrayMultiTreeNode<>(null);
        TreeNode<Movement> lightAttackStartBranch = new ArrayMultiTreeNode<>(new LightAttack());
        TreeNode<Movement> heavyAttackStartBranch = new ArrayMultiTreeNode<>(new HeavyAttack());
        TreeNode<Movement> actionStartBranch = new ArrayMultiTreeNode<>(new Action());
        TreeNode<Movement> crouchStartBranch = new ArrayMultiTreeNode<>(new Crouch());
        TreeNode<Movement> jumpStartBranch = new ArrayMultiTreeNode<>(new Jump());
        root.add(lightAttackStartBranch);
        root.add(heavyAttackStartBranch);
        root.add(actionStartBranch);
        root.add(crouchStartBranch);
        root.add(jumpStartBranch);
        lightAttackStartBranch.add(new LL().getMoveset());
        lightAttackStartBranch.add(new HL().getMoveset());
        heavyAttackStartBranch.add(new HL().getMoveset());
        jumpStartBranch.add(new HH().getMoveset());
        crouchStartBranch.add(new LL().getMoveset());
    }
}
