package com.vsu.service.game;

import com.vsu.actor.model.Actor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class Game {
    private final List<Actor> actors;

    public Game(Actor[] actors) {
        this.actors = Arrays.asList(actors);
    }

    public Game() {
        actors = new ArrayList<>();
    }
}
