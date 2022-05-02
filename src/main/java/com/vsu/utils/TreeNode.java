package com.vsu.utils;

import com.vsu.actor.movement.Movement;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TreeNode {
    private Movement data;
    private List<TreeNode> children;
    private TreeNode parent;

    public TreeNode(Movement data) {
        this.data = data;
        parent = null;
        children = new ArrayList<>();
    }

    public TreeNode add(Movement data) {
        var node = new TreeNode(data);
        node.parent = this;
        children.add(node);
        return node;
    }

    public TreeNode add(TreeNode node) {
        node.parent = this;
        children.add(node);
        return node;
    }

    public TreeNode find(Movement data) {
        var found =
                children.stream().filter(c -> c.data.getType().equals(data.getType())).findFirst().orElse(null);
        if (found != null) {
            if (found.data.getType().equals(data.getType())) {
                return found;
            }
        }
        return null;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public boolean isRoot() {
        return parent == null;
    }
}
