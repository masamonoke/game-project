package com.vsu.map;

import com.vsu.utils.GeometryUtils;
import com.vsu.utils.MathUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2Test {
    @Test
    void directionTest() {
        var v = new Vector2(25, 16);
        Vector2 res;
        res = v.add(Vector2.up);
        assertEquals(res, new Vector2(25, 17));

        res = v.add(Vector2.down);
        assertEquals(res, new Vector2(25, 15));

        res = v.add(Vector2.left);
        assertEquals(res, new Vector2(24, 16));

        res = v.add(Vector2.right);
        assertEquals(res, new Vector2(26, 16));

        res = v.add(Vector2.upRight);
        assertEquals(res, new Vector2(26, 17));

        res = v.add(Vector2.upLeft);
        assertEquals(res, new Vector2(24, 17));

        res = v.add(Vector2.downRight);
        assertEquals(res, new Vector2(26, 15));

        res = v.add(Vector2.downLeft);
        assertEquals(res, new Vector2(24, 15));

        res = v.add(Vector2.zero);
        assertEquals(res, v);
    }

    @Test
    void addTest() {
        var v = new Vector2(-76, 12);
        var a = 55;
        assertEquals(v.add(a), new Vector2(-21, 67));
        var b = new Vector2(24, -1);
        assertEquals(v.add(b), new Vector2(-52, 11));
    }

    //TODO: дописать для всех квадрантов
    @Test
    void getMiddlePointFromDistanceAndEndPointTest() {
        var cursor = new Vector2(10, 12);
        var v = new Vector2(1, 2);
        int distance = 2;
        var res = cursor.getMiddlePointFromDistanceAndEndPoint(cursor, distance);
        var angleRadian1 = MathUtils.round(GeometryUtils.findVectorAngleByPoint(cursor), 1);
        var angleRadian2 = MathUtils.round(GeometryUtils.findVectorAngleByPoint(res), 1);
        assertEquals(angleRadian1, angleRadian2);
    }

}