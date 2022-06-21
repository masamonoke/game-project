package com.vsu.utils;

import com.vsu.map.Vector2;
import com.vsu.visual.ViewConfig;

import java.util.ArrayList;
import java.util.List;

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

    //возвращает значение в радианах
    public static double    findVectorAngleByPoint(Vector2 cartesianVector) {
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

    //возвращает точку в декартовых координатах
    public static Vector2 findPointBetween(Vector2 p1, Vector2 p2, double step) {
        var unitVector = unitVector(p1, p2);
        return p1.add(unitVector.multiply(step));
    }

    public static Vector2 unitVector(Vector2 a, Vector2 b) {
        var ab = orderedTriple(a ,b);
        var m = magnitude(ab);
        return ab.divide(m);
    }

    public static Vector2 orderedTriple(Vector2 a, Vector2 b) {
        return b.subtract(a);
    }

    public static double magnitude(Vector2 v) {
        return Math.sqrt(v.x * v.x + v.y * v.y);
    }

    public static double distance(Vector2 p1, Vector2 p2) {
        var x = Math.pow(p2.x - p1.x, 2);
        var y = Math.pow(p2.y - p1.y, 2);
        return Math.sqrt(x + y);
    }

    public static boolean isInCircle(Vector2 p, Vector2 center, double radius) {
        var res =  Math.sqrt(Math.pow(p.x - center.x, 2) + Math.pow(p.y- center.y, 2));
        res = MathUtils.round(res, 1);
        return res <= radius + 0.5;
    }

    public static Vector2 fromScreenToCartesianCoordinates(Vector2 screen) {
        var x = screen.x - ViewConfig.getInstance().getWindowWidth() / 2;
        var y = -screen.y + ViewConfig.getInstance().getWindowHeight() / 2;
        return new Vector2(x, y);
    }

    public static Vector2 fromCartesianToScreenCoordinates(Vector2 cartesian) {
        var x = cartesian.x + ViewConfig.getInstance().getWindowWidth() / 2;
        var y = -cartesian.y + ViewConfig.getInstance().getWindowHeight() / 2;
        return new Vector2(x, y);
    }

    public static boolean isPointsOnTheLine(Vector2 p1, Vector2 p2, List<Vector2> points) {
        var eps = 0.0000001;
        var prevUnit = GeometryUtils.unitVector(points.get(0), points.get(1));
        var boundaryUnit = GeometryUtils.unitVector(p1, p2);
        for (int i = 2; i < points.size(); i++) {
            var prev = points.get(i - 1);
            var cur = points.get(i);
            var unit = GeometryUtils.unitVector(prev, cur);
            var isNeighbourPointsOnTheSameLine =
                    Math.abs(prevUnit.x - unit.x) < 0.00001 && Math.abs(prevUnit.y - unit.y) < eps;
            var isCurPointsOnTheSameLineWithBoundaries =
                    boundaryUnit.subtract(unit).subtract(boundaryUnit.subtract(prevUnit)).lessThan(eps);
            if (!isNeighbourPointsOnTheSameLine && !isCurPointsOnTheSameLineWithBoundaries) {
                return false;
            }
            prevUnit = unit;
        }
        return true;
    }

    public static List<Vector2> getPathToDestination(Vector2 start, Vector2 destination, double step) {
        var list = new ArrayList<Vector2>();
        list.add(start);
        start = GeometryUtils.fromScreenToCartesianCoordinates(start);
        var dest = GeometryUtils.fromScreenToCartesianCoordinates(destination);
        var cur = start;
        var distance = GeometryUtils.distance(start, dest);
        var segmentN = (int) distance / step;
        for (int i = 0; i < segmentN - 1; i++) {
            cur = GeometryUtils.findPointBetween(cur, dest, step);
            var cartesian = GeometryUtils.fromCartesianToScreenCoordinates(cur);
            list.add(cartesian);
        }
        list.add(GeometryUtils.fromCartesianToScreenCoordinates(dest));
        return list;
    }

    public static Vector2 getMiddlePointFromOriginToPoint(Vector2 origin, Vector2 dest, int distance) {
        var radian = GeometryUtils.findVectorAngleByPoint(dest);
        var destinationX = distance * Math.cos(radian) + origin.x;
        var destinationY = distance * Math.sin(radian) + origin.y;
        return new Vector2(destinationX, destinationY);
    }
}
