package com.vsu.actor.movement.combat.damage.dataset;

import com.vsu.map.Vector2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SectorDamageTest {
    @Test
    void isDamagedTest() {
        var cursorAngle = 80;
        var radius = 50;
        var center = new Vector2(0, 0);
        var p = new Vector2(30, 20);
        var sector = new SectorDamage(alpha(cursorAngle), beta(cursorAngle), radius, center);
        assertTrue(sector.isDamaged(p));

        cursorAngle = 134;
        sector.setAlphaAngle(alpha(cursorAngle));
        sector.setBetaAngle(beta(cursorAngle));
        assertFalse(sector.isDamaged(p));
        p.setCoords(-20, 5);
        assertTrue(sector.isDamaged(p));
    }

    //альфа - отрицательный угол, p находится в 1 квадранте
    @Test
    void isDamagedTestBoundaryCase1() {
        var cursorAngle = 10;
        var radius = 50;
        var sector = new SectorDamage(alpha(cursorAngle), beta(cursorAngle), radius, new Vector2(0, 0));
        var p = new Vector2(1, 1);
        assertTrue(sector.isDamaged(p));
    }

    //альфа - отрицательный угол, p находится в 4 квадранте
    @Test
    void isDamagedTestBoundaryCase2() {
        var cursorAngle = 10;
        var radius = 50;
        var sector = new SectorDamage(alpha(cursorAngle), beta(cursorAngle), radius, new Vector2(0, 0));
        var p = new Vector2(1, -1);
        assertTrue(sector.isDamaged(p));
    }

    //бета > 360, p находится в 1 квадранте
    @Test
    void isDamagedTestBoundaryCase3() {
        var cursorAngle = 340;
        var radius = 50;
        var sector = new SectorDamage(alpha(cursorAngle), beta(cursorAngle), radius, new Vector2(0, 0));
        var p = new Vector2(1, 1);
        assertTrue(sector.isDamaged(p));
    }

    //бета > 360, p находится в 4 квадранте
    @Test
    void isDamagedTestBoundaryCase4() {
        var cursorAngle = 340;
        var radius = 50;
        var sector = new SectorDamage(alpha(cursorAngle), beta(cursorAngle), radius, new Vector2(0, 0));
        var p = new Vector2(1, -1);
        assertTrue(sector.isDamaged(p));
    }

    private double alpha(double cursorAngle) {
        return cursorAngle - 70;
    }

    private double beta(double cursorAngle) {
        return cursorAngle + 70;
    }

}