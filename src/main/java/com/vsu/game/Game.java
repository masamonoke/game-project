package com.vsu.game;

import com.vsu.actor.model.Actor;
import com.vsu.actor.movement.combat.combo.ComboTree;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Game {
    private final List<Actor> actors;
    private final ComboTree comboTree;

    public Game() {
        actors = new ArrayList<>();
        try {
            comboTree = new ComboTree();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
