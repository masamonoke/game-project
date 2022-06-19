package com.vsu.utils;

import com.vsu.map.Vector2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeometryUtilsTest {
    private final double EPS = 0.0000001;
    @Test
    void findQuadrantTest() {
        var quadrant = GeometryUtils.findQuadrant(new Vector2(2, 2));
        assertEquals(1, quadrant);

        quadrant = GeometryUtils.findQuadrant(new Vector2(0, 0));
        assertEquals(1, quadrant);

        quadrant = GeometryUtils.findQuadrant(new Vector2(0, 9));
        assertEquals(1, quadrant);

        quadrant = GeometryUtils.findQuadrant(new Vector2(-1, 9));
        assertEquals(2, quadrant);

        quadrant = GeometryUtils.findQuadrant(new Vector2(-2, 2));
        assertEquals(2, quadrant);

        quadrant = GeometryUtils.findQuadrant(new Vector2(-2, 0));
        assertEquals(2, quadrant);

        quadrant = GeometryUtils.findQuadrant(new Vector2(-2, -2));
        assertEquals(3, quadrant);

        quadrant = GeometryUtils.findQuadrant(new Vector2(0, -2));
        assertEquals(3, quadrant);

        quadrant = GeometryUtils.findQuadrant(new Vector2(2, -2));
        assertEquals(4, quadrant);

    }

    @Test
    void degreeBetweenVectorAndXAxisTest() {
        var v1 = new Vector2(0, 0);
        var degree = getDegree(v1, new Vector2(8, 8));
        assertTrue(degree >= 0 && degree <= 90);

        degree = getDegree(v1, new Vector2(-8, 9));
        assertTrue(degree >= 90 && degree <= 180);

        degree = getDegree(v1, new Vector2(-8, -9));
        assertTrue(degree >= 180 && degree <= 270);

        degree = getDegree(v1, new Vector2(8, -9));
        assertTrue(degree >= 270 && degree < 360);

        degree = getDegree(v1, new Vector2(0, -9));
        assertEquals(270, degree);

        degree = getDegree(v1, new Vector2(0, 0));
        assertEquals(0, degree);

        degree = getDegree(v1, new Vector2(0, 9));
        assertEquals(90, degree);

        degree = getDegree(v1, new Vector2(-10, 0));
        assertEquals(180, degree);

    }

    private double getDegree(Vector2 v1, Vector2 v2) {
        var radian = GeometryUtils.findVectorAngleByPoint(v2);
        return radian * 180 / Math.PI;
    }

    @Test
    void isInCircleTest() {
        var p = new Vector2(19.9, 5);
        var radius = 20;
        var center = new Vector2(0, 0);
        assertTrue(GeometryUtils.isInCircle(p, center, radius));

        p = new Vector2(20, 5);
        assertFalse(GeometryUtils.isInCircle(p, center, radius));

        p = new Vector2(0, 0);
        assertTrue(GeometryUtils.isInCircle(p, center, radius));

        p = new Vector2(-19.9, 5);
        assertTrue(GeometryUtils.isInCircle(p, center, radius));

        p = new Vector2(-19.9, -19.9);
        assertFalse(GeometryUtils.isInCircle(p, center, radius));
    }

    @Test
    void orderedTripleTest() {
        var a = new Vector2(1, 3);
        var b = new Vector2(-3, 1);
        var ab = b.subtract(a);
        assertEquals(ab, new Vector2(-4, -2));
    }

    @Test
    void magnitudeTest() {
        var ab = new Vector2(-4, -2);
        var magnitude = GeometryUtils.magnitude(ab);
        var e = Math.abs(magnitude - 4.47213595);
        assertTrue(e < EPS);
    }


    @Test
    void defineUnitVectorTest() {
        var a = new Vector2(1, 3);
        var b = new Vector2(-3, 1);
        var unit = GeometryUtils.unitVector(a, b);
        var x = -0.894427192;
        var y = -0.447213596;
        var delta = Math.abs(x - unit.x);
        assertTrue(Math.abs(x - unit.x) < EPS);
        assertTrue(Math.abs(y - unit.y) < EPS);
    }

    @Test
    void distanceTest() {
        var p1 = new Vector2(2, -6);
        var p2 = new Vector2(7, 3);
        var distance = GeometryUtils.distance(p1, p2);
        assertTrue(Math.abs(distance - 10.2956301) < EPS);
    }

    @Test
    void findPointBetweenTest() {
        var p1 = new Vector2(52, 30);
        var p2 = new Vector2(70, 44);
        var between = GeometryUtils.findPointBetween(p1, p2, 20);
        var p1BetweenDistance = GeometryUtils.distance(p1, between);
        var betweenP2Distance = GeometryUtils.distance(between, p2);
        var p1p2Distance = GeometryUtils.distance(p1, p2);
        var s = p1BetweenDistance + betweenP2Distance;
        assertTrue(Math.abs(s - p1p2Distance) < EPS);
    }

    @Test
    void getPathToDestinationTest() {
        var p1 = new Vector2(351, 299);
        var p2 = new Vector2(370, 200);
        var path = GeometryUtils.getPathToDestination(p1, p2, 1);
        assertTrue(GeometryUtils.isPointsOnTheLine(p1, p2, path));
    }

    @Test
    void getMiddlePointFromDistanceAndEndPointTest() {
        var cursor = new Vector2(10, 12);
        var v = new Vector2(1, 2);
        int distance = 2;
        var res = GeometryUtils.getMiddlePointFromOriginToPoint(v, cursor, distance);
        var angleRadian1 = MathUtils.round(GeometryUtils.findVectorAngleByPoint(cursor), 0);
        var angleRadian2 = MathUtils.round(GeometryUtils.findVectorAngleByPoint(res), 0);
        assertEquals(angleRadian1, angleRadian2);

        cursor = new Vector2(-10, 12);
        v = new Vector2(-1, 2);
        res = GeometryUtils.getMiddlePointFromOriginToPoint(v, cursor, distance);
        angleRadian1 = MathUtils.round(GeometryUtils.findVectorAngleByPoint(cursor), 0);
        angleRadian2 = MathUtils.round(GeometryUtils.findVectorAngleByPoint(res), 0);
        assertEquals(angleRadian1, angleRadian2);

        cursor = new Vector2(-10, -12);
        v = new Vector2(-1, -2);
        res = GeometryUtils.getMiddlePointFromOriginToPoint(v, cursor, distance);
        angleRadian1 = MathUtils.round(GeometryUtils.findVectorAngleByPoint(cursor), 0);
        angleRadian2 = MathUtils.round(GeometryUtils.findVectorAngleByPoint(res), 0);
        assertEquals(angleRadian1, angleRadian2);

        cursor = new Vector2(10, 12);
        v = new Vector2(1, 2);
        res = GeometryUtils.getMiddlePointFromOriginToPoint(v, cursor, distance);
        angleRadian1 = MathUtils.round(GeometryUtils.findVectorAngleByPoint(cursor), 0);
        angleRadian2 = MathUtils.round(GeometryUtils.findVectorAngleByPoint(res), 0);
        assertEquals(angleRadian1, angleRadian2);
    }

    @Test
    void fromScreenToCartesianCoordinatesTest1() {
        var p1Screen = new Vector2(0, 0);
        var p1Cartesian = GeometryUtils.fromScreenToCartesianCoordinates(p1Screen);
        assertEquals(p1Cartesian, new Vector2(-350, 300));
    }

    @Test
    void fromScreenToCartesianCoordinatesTest2() {
        var p1Screen = new Vector2(700, 600);
        var p1Cartesian = GeometryUtils.fromScreenToCartesianCoordinates(p1Screen);
        assertEquals(p1Cartesian, new Vector2(350, -300));
    }

    @Test
    void fromScreenToCartesianCoordinatesTest3() {
        var p1Screen = new Vector2(350, 300);
        var p1Cartesian = GeometryUtils.fromScreenToCartesianCoordinates(p1Screen);
        assertEquals(p1Cartesian, new Vector2(0, 0));
    }

    @Test
    void fromScreenToCartesianCoordinatesTest4() {
        var p1Screen = new Vector2(350, 300);
        var p1Cartesian = GeometryUtils.fromScreenToCartesianCoordinates(p1Screen);
        var screen = GeometryUtils.fromCartesianToScreenCoordinates(p1Cartesian);
        assertEquals(p1Screen, screen);
    }

    @Test
    void fromCartesianToScreenCoordinates1() {
        var p1Cartesian = new Vector2(0, 0);
        var p1Screen = GeometryUtils.fromCartesianToScreenCoordinates(p1Cartesian);
        assertEquals(p1Screen, new Vector2(350, 300));
    }

    @Test
    void fromCartesianToScreenCoordinates2() {
        var p1Cartesian = new Vector2(500, 500);
        var p1Screen = GeometryUtils.fromCartesianToScreenCoordinates(p1Cartesian);
        assertEquals(p1Screen, new Vector2(850, -200));
    }

    @Test
    void fromCartesianToScreenCoordinates3() {
        var p1Cartesian = new Vector2(0, 0);
        var p1Screen = GeometryUtils.fromCartesianToScreenCoordinates(p1Cartesian);
        var cartesian = GeometryUtils.fromScreenToCartesianCoordinates(p1Screen);
        assertEquals(p1Cartesian, cartesian);
    }
}