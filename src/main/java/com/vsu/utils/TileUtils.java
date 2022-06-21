package com.vsu.utils;

import com.vsu.visual.VisualData;

public class TileUtils {
    private VisualData data;

    public int[] tileCoord(double x, double y) {
        int[] coor = new int[2];
        while (x>data.getTileSize()){
            x/= data.getTileSize();
            coor[0]+=1;
        }
        while (y>data.getTileSize()){
            y/= data.getTileSize();
            coor[1]+=1;
        }
        return coor;
    }

    public double[] getPosInTile(int x,int y) {
        double[] pos = new double[2];
        pos[0]=x;
        pos[1]=y;
        while (pos[0]>data.getTileSize()){
            pos[0]/= data.getTileSize();
        }
        while (pos[1]>data.getTileSize()){
            pos[1]/= data.getTileSize();
        }
        return pos;
    }

}
