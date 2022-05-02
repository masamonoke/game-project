package com.vsu.actor.movement.combat.combo;

import com.vsu.actor.model.Character;
import com.vsu.actor.movement.Crouch;
import com.vsu.actor.movement.Jump;
import com.vsu.actor.movement.Movement;
import com.vsu.actor.movement.combat.Action;
import com.vsu.actor.movement.combat.HeavyAttack;
import com.vsu.actor.movement.combat.LightAttack;
import com.vsu.utils.TreeNode;
import lombok.Getter;

import java.io.*;

import static com.vsu.App.logger;


@Getter
public class ComboTree {
    private final TreeNode root;
    private TreeNode cur;
    private String filename;

    public ComboTree() throws IOException {
        root = new TreeNode(null);
        cur = root;
        filename = "src/main/resources/combat/comboset.txt";
        readComboTreeFromFile();
    }

    public ComboTree(String filename) throws IOException {
        root = new TreeNode(null);
        cur = root;
        this.filename = filename;
        readComboTreeFromFile();
    }

    public TreeNode traverse(Movement movement, Character character) {
        if (cur == root) {
            logger.info("Starting new combo chain");
        }
        TreeNode found;
        if (movement != null) {
            found = cur.find(movement);
        } else {
            return null;
        }
        if (found != null) {
            var parent = found.getParent();
            Movement prev = null;
            if (parent != null) {
                prev = parent.getData();
            }
            found.getData().apply(prev, character);
            movement.setPrev(cur.getData());
            if (found.isLeaf()) {
                cur = root;
                //вызываем эвент завершения комбо
                logger.info("Combo is finished");
                return found;
            }
            cur = found;
            return found;
        } else {
            movement.apply(null, character);
            logger.info("Combo chain is interrupted");
            cur = root;
            return cur;
        }
    }

    private void readComboTreeFromFile() throws IOException {
        FileReader fileReader;
        try {
            fileReader = new FileReader(filename);
        } catch (FileNotFoundException e) {
            var file = new File(filename);
            if (file.createNewFile()) {
                fileReader = new FileReader(filename);
            } else {
                return;
            }
        }
        var bufferedReader = new BufferedReader(fileReader);
        String line;
        while((line = bufferedReader.readLine()) != null) {
            var node = root;
            for (int i = 0; i < line.length(); i++) {
                var newNode = new TreeNode(getMovementByLetter(line.charAt(i)));
                node.add(newNode);
                node = newNode;
            }
        }
    }

    private Movement getMovementByLetter(char c) {
        c = java.lang.Character.toUpperCase(c);
        switch (c) {
            case 'L' -> {
                return new LightAttack();
            }
            case 'H' -> {
                return new HeavyAttack();
            }
            case 'J' -> {
                return new Jump();
            }
            case 'C' -> {
                return new Crouch();
            }
            case 'A' -> {
                return new Action();
            }
        }
        return null;
    }

}
