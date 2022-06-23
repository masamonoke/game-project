package com.vsu.actor.movement.combat.combo;

import com.vsu.actor.model.Actor;
import com.vsu.actor.movement.Movement;
import com.vsu.actor.movement.MovementResult;
import com.vsu.utils.TreeNode;
import lombok.Getter;

import java.io.IOException;

import static com.vsu.App.logger;


@Getter
public class ComboTree {
    private final TreeNode root;
    private TreeNode cur;
    private TreeNode finisher;
    private final String filename;
    private long time;

    //только для тестов и прототипа
    public ComboTree() throws IOException {
        this("src/main/resources/combat/updatedComboset.txt");
    }

    public ComboTree(String filename) throws IOException {
        root = new TreeNode(null);
        cur = root;
        this.filename = filename;
        CombosetParser parser = new CombosetParser();
        parser.readUpdatedComboTreeFromFile(filename, root);
        time = -1;
    }

    public MovementResult traverseTree(Movement movement, Actor actor) {
        if (cur.isLeaf()) {
            cur = root;
            finisher = null;
        }
        if (cur == root) {
            logger.info("Starting new combo chain");
        }

        TreeNode found;
        if (movement != null) {
            found = cur.find(movement);
        } else {
            return null;
        }

        if (time == -1) {
            time = System.currentTimeMillis();
        } else {
            var timePassed = System.currentTimeMillis() - time;
            if (timePassed > 2000) {
                found = null;
            }
            time = -1;
        }

        if (found != null) {
            for (var child : found.getChildren()) {
                if (child.isLeaf()) {
                    finisher = child;
                    break;
                }
            }
            cur = found;
            var m = found.getData();
            var res = actor.move(m, actor);
            if (res == null) {
                cur = root;
            }
            return res;
        } else {
            logger.info("Combo chain is interrupted");
            cur = root;
            return actor.move(movement, actor);
        }
    }

    public MovementResult finisher(Actor actor) {
        var res = finisher != null ? finisher.getData().apply(actor) : null;
        if (res != null) {
            logger.info("Combo is finished");
            cur = finisher;
        }
        return res;
    }
}
