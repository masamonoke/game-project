package com.vsu.actor.model;

import com.vsu.map.Vector2;
import lombok.*;


import java.util.UUID;

import static com.vsu.map.model.TilemapDirection2D.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Character extends Actor {
    protected String id;
    protected String name;
    //TODO: отвязать от тайлов
    protected Position tilePos;
    protected Vector2 pos;
    protected Vector2 cursorPos;
    protected int exp;
    protected int toxic;
    protected int strength;
    protected int intelligence;
    protected int agility;
    protected int noise;
    protected int trickery;
    //TODO: считается от статов брони и оружия
    protected int modificationDamage;
    protected double criticalChance;

    public Character(String name) {
        hp = 20;
        mp = 10;
        stamina = 10;
        speed = 1;
        physicalDamage = 1;
        magicalDamage = 1;
        id = UUID.randomUUID().toString();
        this.name = name;
        tilePos = new Position(0, 0);
        pos = new Vector2(0, 0);
        exp = 0;
        toxic = 0;
        strength = 1;
        intelligence = 1;
        agility = 1;
        noise = 1;
        trickery = 1;
        modificationDamage = 1;
        criticalChance = 0.3;
    }

    public void setPos(double x, double y) {
        pos.setCoords(x, y);
    }

    public void setCursorPos(double x, double y) {
        if (cursorPos == null) {
            cursorPos = new Vector2(x, y);
            return;
        }
        cursorPos.setCoords(x, y);
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                '}';
    }
}
