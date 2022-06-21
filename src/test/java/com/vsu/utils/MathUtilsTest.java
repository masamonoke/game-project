package com.vsu.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MathUtilsTest {
    @Test
    void roundTest1() {
        var a = 3.68;
        assertEquals(MathUtils.round(a, 1), 3.7);
    }

    @Test
    void roundTest2() {
        var a = 3.65;
        assertEquals(MathUtils.round(a, 1), 3.7);
    }

    @Test
    void roundTest3() {
        var a = 3.5;
        assertEquals(MathUtils.round(a, 0), 4);
    }

    @Test
    void roundTest4() {
        var a = 3.68124;
        assertEquals(MathUtils.round(a, 4), 3.6812);
    }

    @Test
    void roundTest5() {
        var a = -3.68;
        assertEquals(MathUtils.round(a, 1), -3.7);
    }

    @Test
    void roundTest6() {
        var a = 3;
        assertEquals(MathUtils.round(a, 1), 3);
    }

    @Test
    void roundTest7() {
        var a = 3;
        assertEquals(MathUtils.round(a, 0), 3);
    }

    @Test
    void roundTest8() {
        var a = 0;
        assertEquals(MathUtils.round(a, 0), 0);
    }

    @Test
    void roundTest9() {
        var a = 0.6;
        assertEquals(MathUtils.round(a, 0), 1);
    }

    @Test
    void roundTest10() {
        var a = 0.6;
        assertEquals(MathUtils.round(a, 1), 0.6);
    }
}