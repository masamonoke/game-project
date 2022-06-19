package com.vsu.actor.movement.combat.combo;

import com.vsu.actor.movement.Crouch;
import com.vsu.actor.movement.Dash;
import com.vsu.actor.movement.Jump;
import com.vsu.actor.movement.Movement;
import com.vsu.actor.movement.combat.Action;
import com.vsu.actor.movement.combat.HeavyAttack;
import com.vsu.actor.movement.combat.LightAttack;
import com.vsu.actor.movement.combat.finisher.CircleAoe;
import com.vsu.actor.movement.combat.finisher.StrengthBuff;
import com.vsu.utils.ResourceReaderUtil;
import com.vsu.utils.TreeNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CombosetParser {
    List<Movement> movements;

    public CombosetParser() {
        movements = new ArrayList<>();
        movements.add(new LightAttack());
        movements.add(new HeavyAttack());
        movements.add(new Jump());
        movements.add(new Crouch());
        movements.add(new Action());
        movements.add(new StrengthBuff());
        movements.add(new CircleAoe());
        movements.add(new Dash());
    }

    public void readUpdatedComboTreeFromFile(String filename, TreeNode root) throws IOException {
        var bufferedReader = ResourceReaderUtil.readFile(filename);
        if (bufferedReader == null) {
            return;
        }
        String line;
        while((line = bufferedReader.readLine()) != null) {
            var comboBranch = parseComboLine(line);
            mergeBranch(comboBranch, root);
        }
    }

    private List<TreeNode> parseComboLine(String line) {
        var isOpenBracket = false;
        var isClosingBracket = false;
        var movementStr = new StringBuilder();
        var list = new ArrayList<TreeNode>();
        for (var c : line.toCharArray()) {
            if (c == '{') {
                isOpenBracket = true;
                isClosingBracket = false;
                continue;
            } else if (c == '}') {
                isOpenBracket = false;
                isClosingBracket = true;
            }
            if (isOpenBracket) {
                movementStr.append(c);
            }
            if (isClosingBracket) {
                StringBuilder finalMovementStr = movementStr;
                var movement = movements.stream()
                        .filter(m -> finalMovementStr.toString().equals(m.toString())).findFirst().orElse(null);
                if (movement == null) {
                    throw new NullPointerException();
                }
                list.add(new TreeNode(movement));
                movementStr = new StringBuilder();
            }
        }
        return list;
    }

    private void mergeBranch(List<TreeNode> branch, TreeNode root) {
        var cur = root;
        for (TreeNode n : branch) {
            var found = cur.find(n.getData());
            if (found == null) {
                cur.getChildren().add(n);
                cur = n;
            } else {
                cur = found;
            }
        }
    }

}
