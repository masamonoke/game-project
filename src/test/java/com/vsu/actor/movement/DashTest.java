package com.vsu.actor.movement;

import com.vsu.actor.model.Character;
import org.junit.jupiter.api.Test;

class DashTest {
    //TODO: доделать тесты
    @Test
    void dashTest() {
        var char1 = new Character(  "char1");
        char1.setPos(52, 43);
        char1.setCursorPos(52, 60);
        var dash = new Dash();
        var result = dash.apply(char1);
    }
}