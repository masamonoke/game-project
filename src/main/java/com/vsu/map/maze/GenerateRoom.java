package com.vsu.map.maze;

import com.vsu.map.model.Tile;
import com.vsu.map.model.TileType;
import com.vsu.map.model.Tilemap;
import javafx.scene.Node;

import java.util.*;

public class GenerateRoom {
    Tilemap tilemap;
    int maxHeightRoom, maxWidthRoom, minHeightRoom, minWidthRoom;

    public GenerateRoom (Tilemap tilemap, int maxHeightRoom, int maxWidthRoom, int minHeightRoom, int minWidthRoom) {
        this.tilemap = tilemap;
        this.maxHeightRoom = maxHeightRoom;
        this.maxWidthRoom = maxWidthRoom;
        this.minWidthRoom = minWidthRoom;
        this.minHeightRoom = minHeightRoom;
    }



    public void generate(Tilemap tilemap) {
        Random random = new Random();
        int width = tilemap.getColSize();
        int height = tilemap.getRowSize();

        // создание комнат с помощью дерева
        Tree tree = new Tree(new NodeTree(new int[] {2, 2}, new int[] {width - 2, height - 2}, null));
        List<Room> list = new ArrayList<>();

        createRoom(tilemap, tree.getHead(), list, maxHeightRoom, maxWidthRoom, minHeightRoom, minWidthRoom);
        NodeTree temp = tree.getHead();
        while (temp.getLeft() != null) {
            temp = temp.getLeft();
        }
        createWay(tilemap, list);
    }

    public void cutRoom(Tilemap tilemap, Room room) {
        Tile[][] tiles = tilemap.getMatrix();
        for (int i = room.getLeftUpX(); i <= room.getRightDownX(); i++) {
            for (int j = room.getLeftUpY(); j < room.getRightDownY(); j++) {
                tiles[j][i].setType(TileType.Room);
            }
        }
        int j = room.getLeftUpY();
        for (int i = room.getLeftUpX(); i <= room.getRightDownX(); i++) {
            tiles[j][i].setType(TileType.Wall);
        }
        j = room.getRightDownY();
        for (int i = room.getLeftUpX(); i <= room.getRightDownX(); i++) {
            tiles[j][i].setType(TileType.Wall);
        }
        int i = room.getLeftUpX();
        for (j = room.getLeftUpY(); j <= room.getRightDownY(); j++) {
            tiles[j][i].setType(TileType.Wall);
        }
        i = room.getRightDownX();
        for (j = room.getLeftUpY(); j <= room.getRightDownY(); j++) {
            tiles[j][i].setType(TileType.Wall);
        }
    }

    public void cutWay(Tilemap tilemap, Room firstRoom, Room secondRoom) {
        Tile[][] tiles = tilemap.getMatrix();
        Random random = new Random();

        int inputX = random.nextInt(firstRoom.getLeftUpX(), firstRoom.getRightDownX());
        int inputY = random.nextInt(firstRoom.getLeftUpY(), firstRoom.getRightDownY());
        int outputX = random.nextInt(secondRoom.getLeftUpX(), secondRoom.getRightDownX());
        int outputY = random.nextInt(secondRoom.getLeftUpY(), secondRoom.getRightDownY());

        if (inputX > outputX) {
            int temp = inputX;
            inputX = outputX;
            outputX = temp;
            temp = inputY;
            inputY = outputY;
            outputY = temp;
        }
        for (int i = inputX; i <= outputX; i++) {
            if (tiles[inputY][i].getType() == TileType.Lake) {
                tiles[inputY][i].setType(TileType.Bridge);
            } else {
                tiles[inputY][i].setType(TileType.Pavement);
            }

        }
        int input = inputY;
        int output = outputY;
        if (outputY < inputY) {
            input = outputY;
            output = inputY;
        }
        for (int i = input; i <= output; i++) {
            if (tiles[i][outputX].getType() == TileType.Lake) {
                tiles[i][outputX].setType(TileType.Bridge);
            } else {
                tiles[i][outputX].setType(TileType.Pavement);
            }

        }

    }

    public void createRoom(Tilemap tilemap, NodeTree node, List<Room> list, int maxHeightRoom, int maxWidthRoom, int minHeightRoom, int minWidthRoom) {
        Random random = new Random();
        int height = node.getOutputPoint()[1] - node.getInputPoint()[1];
        int width = node.getOutputPoint()[0] - node.getInputPoint()[0];
        boolean rand = random.nextBoolean();
        boolean heightBool;
        boolean widthBool;
        if (rand) {
            heightBool = splitHeight(tilemap, list, node, height, maxHeightRoom, maxWidthRoom, minHeightRoom, minWidthRoom);
            if (heightBool) {
                widthBool = splitWidth(tilemap, list,  node, width, maxHeightRoom, maxWidthRoom, minHeightRoom, minWidthRoom);
            } else {
                widthBool = false;
            }
        } else {
            widthBool = splitWidth(tilemap, list,  node, width, maxHeightRoom, maxWidthRoom, minHeightRoom, minWidthRoom);
            if (widthBool) {
                heightBool = splitHeight(tilemap, list, node, height, maxHeightRoom, maxWidthRoom, minHeightRoom, minWidthRoom);
            } else {
                heightBool = false;
            }
        }
        // Если не делится в обоих направлениях
        if (widthBool && heightBool) {
            int openX = random.nextInt(node.getInputPoint()[0] + 1, node.getOutputPoint()[0] - minWidthRoom - 2);
            int openY = random.nextInt(node.getInputPoint()[1] + 1, node.getOutputPoint()[1] - minHeightRoom - 2);
            int endX = random.nextInt(openX + minWidthRoom, node.getOutputPoint()[0] - 1);
            int endY = random.nextInt(openY + minHeightRoom, node.getOutputPoint()[1] - 1);
            Room room = new Room(openX, openY, endX, endY);
            list.add(room);
            cutRoom(tilemap, room);
        }
    }

    private void createWay(Tilemap tilemap, List<Room> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            cutWay(tilemap, list.get(i), list.get(i + 1));
        }
    }

    private boolean splitWidth(Tilemap tilemap, List<Room> list, NodeTree node, int width,
                               int maxHeightRoom, int maxWidthRoom, int minHeightRoom, int minWidthRoom) {
        //Истина, если уже не делится
        Random random = new Random();
        boolean roomIf = true;
        if (width >= (maxWidthRoom * 2 + 2)) {
            while (roomIf) {
                int rand = random.nextInt(width);
                if (rand >= maxWidthRoom && width - rand - 1 >= maxWidthRoom) {
                    node.setLeft(new NodeTree(new int[] {node.getInputPoint()[0], node.getInputPoint()[1]},
                            new int[] {node.getInputPoint()[0] + rand, node.getOutputPoint()[1]}, node));
                    createRoom(tilemap, node.getLeft(), list, maxHeightRoom, maxWidthRoom, minHeightRoom, minWidthRoom);
                    node.setRight(new NodeTree(new int[] {node.getInputPoint()[0] + rand + 1, node.getInputPoint()[1]},
                            new int[] {node.getOutputPoint()[0], node.getOutputPoint()[1]}, node));
                    createRoom(tilemap, node.getRight(), list, maxHeightRoom, maxWidthRoom, minHeightRoom, minWidthRoom);
                    roomIf = false;
                }
            }
            return false;
        }
        return true;
    }
    private boolean splitHeight(Tilemap tilemap, List<Room> list, NodeTree node, int height,
                                int maxHeightRoom, int maxWidthRoom, int minHeightRoom, int minWidthRoom) {
        //Истина, если уже не делится
        Random random = new Random();
        boolean roomIf = true;
        if (height >= (maxHeightRoom * 2 + 2)) {
            while (roomIf) {
                int rand = random.nextInt(height);
                if (rand >= maxHeightRoom && height - rand - 1 >= maxHeightRoom) {
                    node.setLeft(new NodeTree(new int[] {node.getInputPoint()[0], node.getInputPoint()[1]},
                            new int[] {node.getOutputPoint()[0], node.getInputPoint()[1] + rand}, node));
                    createRoom(tilemap, node.getLeft(), list, maxHeightRoom, maxWidthRoom, minHeightRoom, minWidthRoom);
                    node.setRight(new NodeTree(new int[] {node.getInputPoint()[0], node.getInputPoint()[1] + rand + 1},
                            new int[] {node.getOutputPoint()[0], node.getOutputPoint()[1]}, node));
                    createRoom(tilemap, node.getRight(), list, maxHeightRoom, maxWidthRoom, minHeightRoom, minWidthRoom);
                    roomIf = false;
                }
            }
            return false;
        }
        return true;
    }



    private class Tree{
        private NodeTree head;

        public Tree(NodeTree head) {
            this.head = head;
        }

        public NodeTree getHead() {
            return head;
        }

        public void setHead(NodeTree head) {
            this.head = head;
        }
    }
    private class NodeTree{
        private int[] inputPoint = new int[2];
        private int[] outputPoint = new int[2];

        private NodeTree left;
        private NodeTree right;
        private NodeTree parent;

        public void setParent(NodeTree parent) {
            this.parent = parent;
        }

        public NodeTree getParent() {
            return parent;
        }

        public NodeTree(int[] inputPoint, int[] outputPoint, NodeTree parent) {
            this.inputPoint = inputPoint;
            this.outputPoint = outputPoint;
            this.parent = parent;
        }

        public int[] getInputPoint() {
            return inputPoint;
        }

        public int[] getOutputPoint() {
            return outputPoint;
        }

        public NodeTree getLeft() {
            return left;
        }

        public NodeTree getRight() {
            return right;
        }

        public void setInputPoint(int[] inputPoint) {
            this.inputPoint = inputPoint;
        }

        public void setOutputPoint(int[] outputPoint) {
            this.outputPoint = outputPoint;
        }

        public void setLeft(NodeTree left) {
            this.left = left;
        }

        public void setRight(NodeTree right) {
            this.right = right;
        }
    }
}
