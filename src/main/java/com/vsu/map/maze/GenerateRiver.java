package com.vsu.map.maze;

import com.vsu.map.model.Tile;
import com.vsu.map.model.TileType;
import com.vsu.map.model.Tilemap;

import java.util.Random;

public class GenerateRiver {
    int minlength = 30;
    int maxlength = 50;
    int coefficientBranch = 3;
    int widthMin = 2;
    int widthMax = 5;
    int lengthExtensions = widthMax + 1;
    boolean left = false;
    boolean up = false;
    boolean right = false;
    boolean down = false;


    public GenerateRiver(Direction direction1) {
        if (direction1 == Direction.down) {
            down = true;
        }
        if (direction1 == Direction.up) {
            up = true;
        }
        if (direction1 == Direction.right) {
            right = true;
        }
        if (direction1 == Direction.left) {
            left = true;
        }
    }

    public void generate(Tilemap tilemap) {
        Random random = new Random();

        int[] upCoordinate = new int[2];
        int[] downCoordinate = new int[2];
        int[] leftCoordinate = new int[2];
        int[] rightCoordinate = new int[2];
        if (up) {
            upCoordinate[0] = random.nextInt(lengthExtensions, tilemap.getColSize() - lengthExtensions);
            upCoordinate[1] = 0;
            way(tilemap, upCoordinate, Direction.up, random.nextInt(minlength, maxlength));
        } else {
            if (down) {
                downCoordinate[0] = random.nextInt(lengthExtensions, tilemap.getColSize() - lengthExtensions);
                downCoordinate[1] = tilemap.getRowSize() - 1;
                way(tilemap, downCoordinate, Direction.down, random.nextInt(minlength, maxlength));
            } else {
                if (left) {
                    leftCoordinate[0] = 0;
                    leftCoordinate[1] = random.nextInt(lengthExtensions, tilemap.getRowSize() - lengthExtensions);
                    way(tilemap, leftCoordinate, Direction.left, random.nextInt(minlength, maxlength));
                } else {
                    rightCoordinate[0] = tilemap.getColSize() - 1;
                    rightCoordinate[1] = random.nextInt(lengthExtensions, tilemap.getRowSize() - lengthExtensions);
                    way(tilemap, rightCoordinate, Direction.right, random.nextInt(minlength, maxlength));
                }
            }
        }
    }

    private void way(Tilemap tilemap, int[] coordinate, Direction prevPoint, int length) {
        //          !!!!!!       доделать       !!!!
        //путь между начальными точками реки + рандомное расширение
        Random random = new Random();
        boolean branch = false;
        if (length > 0) {
            if (random.nextInt(coefficientBranch) == 1 && prevPoint != Direction.up && coordinate[1] - 1 > 1) {
                tilemap.getMatrix()[coordinate[1] - 1][coordinate[0]].setType(TileType.Lake);
                way(tilemap, new int[]{coordinate[0], coordinate[1] - 1}, Direction.down, length -
                        random.nextInt(1, 3));
                branch = true;
            }
            if (random.nextInt(coefficientBranch) == 1 && prevPoint != Direction.left && coordinate[0] - 1 > 1) {
                tilemap.getMatrix()[coordinate[1]][coordinate[0] - 1].setType(TileType.Lake);
                way(tilemap, new int[]{coordinate[0] - 1, coordinate[1]}, Direction.right, length -
                        random.nextInt(1, 3));
                branch = true;
            }
            if (random.nextInt(coefficientBranch) == 1 && prevPoint != Direction.right && coordinate[0] + 1 < tilemap.getColSize() - 1) {
                tilemap.getMatrix()[coordinate[1]][coordinate[0] + 1].setType(TileType.Lake);
                way(tilemap, new int[]{coordinate[0] + 1, coordinate[1]}, Direction.left, length -
                        random.nextInt(1, 3));
                branch = true;
            }
            if (random.nextInt(coefficientBranch) == 1 && prevPoint != Direction.down && coordinate[1] + 1 < tilemap.getRowSize() - 1) {
                tilemap.getMatrix()[coordinate[1] + 1][coordinate[0]].setType(TileType.Lake);
                way(tilemap, new int[]{coordinate[0], coordinate[1] + 1}, Direction.up, length -
                        random.nextInt(1, 3));
                branch = true;
            }
        }
        if (!branch) {
            randWay(tilemap, coordinate, prevPoint, length);
        }

    }

    private void randWay(Tilemap tilemap, int[] coordinate, Direction prevPoint, int length) {
        Random random = new Random();
        int tempRand = random.nextInt(4);
        if (tempRand == 0 && prevPoint != Direction.up && coordinate[1] - 1 > 1) {
            tilemap.getMatrix()[coordinate[1] - 1][coordinate[0]].setType(TileType.Lake);
            way(tilemap, new int[]{coordinate[0], coordinate[1] - 1}, Direction.down, length -
                    random.nextInt(1, 3));
        }
        if (tempRand == 1 && prevPoint != Direction.left && coordinate[0] - 1 > 1) {
            tilemap.getMatrix()[coordinate[1]][coordinate[0] - 1].setType(TileType.Lake);
            way(tilemap, new int[]{coordinate[0] - 1, coordinate[1]}, Direction.right, length -
                    random.nextInt(1, 3));
        }
        if (tempRand == 2 && prevPoint != Direction.right && coordinate[0] + 1 < tilemap.getColSize() - 1) {
            tilemap.getMatrix()[coordinate[1]][coordinate[0] + 1].setType(TileType.Lake);
            way(tilemap, new int[]{coordinate[0] + 1, coordinate[1]}, Direction.left, length -
                    random.nextInt(1, 3));
        }
        if (tempRand == 3 && prevPoint != Direction.down && coordinate[1] + 1 < tilemap.getRowSize() - 1) {
            tilemap.getMatrix()[coordinate[1] + 1][coordinate[0]].setType(TileType.Lake);
            way(tilemap, new int[]{coordinate[0], coordinate[1] + 1}, Direction.up, length -
                    random.nextInt(1, 3));
        }
    }

}
