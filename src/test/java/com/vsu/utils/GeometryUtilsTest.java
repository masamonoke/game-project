package com.vsu.utils;

import com.vsu.map.Vector2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeometryUtilsTest {
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

        try {
            quadrant = GeometryUtils.findQuadrant(null);
        } catch (NullPointerException ignored) {}
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
}