package com.vsu.utils;

import com.vsu.map.Vector2;
import com.vsu.visual.ViewConfig;

//TODO: покрыть тестами
public class GeometryUtils {

    public static int findQuadrant(Vector2 p) {
        if (p == null) {
            throw new NullPointerException();
        }
        if (p.x >= 0 && p.y >= 0) {
            return 1;
        } else if (p.x <= 0 && p.y >= 0) {
            return 2;
        } else if (p.x <= 0 && p.y <= 0) {
            return 3;
        } else if (p.x >= 0 && p.y <= 0) {
            return 4;
        }
        return 0;
    }

    public static double findVectorAngleByPoint(Vector2 cartesianVector) {
        double radianAngle;
        if (cartesianVector.y == 0 && cartesianVector.x == 0) {
            radianAngle = 0;
        } else {
            radianAngle = Math.atan(cartesianVector.y / cartesianVector.x);
        }
        var quadrant = GeometryUtils.findQuadrant(cartesianVector);
        if (quadrant == 1) {
            return radianAngle;
        } else if (quadrant == 2) {
            return Math.PI + radianAngle;
        } else if (quadrant == 3) {
            if (cartesianVector.x == 0) {
                return Math.PI - radianAngle;
            }
            return Math.PI + radianAngle;
        } else if (quadrant == 4) {
            return 3 * Math.PI / 2 - radianAngle;
        }
        return 0;
    }

    public static boolean isInCircle(Vector2 p, Vector2 center, double radius) {
        var res =  Math.sqrt(Math.pow(p.x - center.x, 2) + Math.pow(p.y- center.y, 2));
        res = MathUtils.round(res, 1);
        return res <= radius + 0.5;
    }

    //TODO: покрыть тестами
    public static Vector2 fromScreenToCartesianCoordinates(Vector2 screen) {
        var x = screen.x - ViewConfig.getInstance().getWindowWidth() / 2;
        var y = screen.y - ViewConfig.getInstance().getWindowHeight() / 2;
        return new Vector2(x, y);
    }

    public static Vector2 fromCartesianToScreenCoordinates(Vector2 cartesian) {
        var x = cartesian.x + ViewConfig.getInstance().getWindowWidth() / 2;
        var y = -cartesian.y + ViewConfig.getInstance().getWindowHeight() / 2;
        return new Vector2(x, y);
    }
}
