package com.vsu.map.maze;

import com.vsu.map.model.TileType;
import com.vsu.map.model.Tilemap;

import java.util.Random;

public class GenerateRiver {
    int widthMin = 2;
    int widthMax = 5;
    int lengthExtensions = widthMax + 1;
    boolean left = false;
    boolean up = false;
    boolean right = false;
    boolean down = false;


    public GenerateRiver(Direction direction1, Direction direction2) {
        if (direction1 == Direction.down) {down = true;}
        if (direction1 == Direction.up) {up = true;}
        if (direction1 == Direction.right) {right = true;}
        if (direction1 == Direction.left) {left = true;}

        if (direction2 == Direction.down) {down = true;}
        if (direction2 == Direction.up) {up = true;}
        if (direction2 == Direction.right) {right = true;}
        if (direction2 == Direction.left) {left = true;}
    }

    public void generate(Tilemap tilemap) {
        Random random = new Random();

        int[] upCoordinate = new int[2];
        int[] downCoordinate = new int[2];
        int[] leftCoordinate = new int[2];
        int[] rightCoordinate = new int[2];
        if (up) {
            upCoordinate[0] = random.nextInt(lengthExtensions, tilemap.getColSize());
            upCoordinate[1] = 0;
        }
        if (down) {
            downCoordinate[0] = random.nextInt(lengthExtensions, tilemap.getColSize());
            downCoordinate[1] = tilemap.getRowSize();
        }
        if (left) {
            leftCoordinate[0] = 0;
            leftCoordinate[1] = random.nextInt(lengthExtensions, tilemap.getRowSize());
        }
        if (right) {
            rightCoordinate[0] = tilemap.getColSize();
            rightCoordinate[1] = random.nextInt(lengthExtensions, tilemap.getRowSize());
        }

        if (up && down) {
            way(upCoordinate, downCoordinate, true);
        } else {
            if (left && right) {
                way(leftCoordinate, rightCoordinate, false);
            } else {
                if (up && left) {
                    way(leftCoordinate, upCoordinate, random.nextBoolean());
                } else {
                    if (up && right) {
                        way(upCoordinate, rightCoordinate, random.nextBoolean());
                    } else {
                        if (down && left) {
                            way(leftCoordinate, downCoordinate, random.nextBoolean());
                        } else {
                            if (down && right) {
                                way(downCoordinate, rightCoordinate, random.nextBoolean());
                            }
                        }
                    }
                }
            }
        }
    }

    private void way(int[] coordinate1, int[] coordinate2, boolean direction) {
        //          !!!!!!       доделать       !!!!
        //путь между начальными точками реки + рандомное расширение
    }


    private void expansion(Tilemap tilemap, int x, int y, int width, boolean direction) {
        //          !!!!!!       доделать       !!!! расширение русла
        // if direction = true, then vertically, else horizontally
        int distanceCenter = 0;
        tilemap.getMatrix()[x][y].setType(TileType.Lake);
        for (int i = 1; i <= width; i ++) {
            if ()
            if (direction) {
                tilemap.getMatrix()[x][y].setType(TileType.Lake);
            }
        }
    }
}
