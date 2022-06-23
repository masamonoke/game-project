package com.vsu.map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void addValueTest() {
        var v = new Vector2(-76, 12);
        var a = 55;
        assertEquals(v.add(a), new Vector2(-21, 67));
    }

    @Test
    void addVectorTest() {
        var v = new Vector2(-76, 12);
        var b = new Vector2(24, -1);
        assertEquals(v.add(b), new Vector2(-52, 11));
    }

    @Test
    void subtractVectorTest() {
        var v = new Vector2(-76, 12);
        var b = new Vector2(24, -1);
        assertEquals(v.subtract(b), new Vector2(-100, 13));
    }

    @Test
    void divideValueTest() {
        var v = new Vector2(-76, 12);
        var a = 2;
        assertEquals(v.divide(a), new Vector2(-38, 6));
    }

    @Test
    void multiplyValueTest() {
        var v = new Vector2(-76, 12);
        var a = 2;
        var b = v.divide(a);
        assertEquals(b.multiply(a), v);
    }

    @Test
    void lessThanValueTest() {
        var v = new Vector2(-76, 12);
        var a = 13;
        assertTrue(v.lessThan(a));
    }
}