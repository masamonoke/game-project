package com.vsu.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Creature {
    private int hp;
    private int speed;
    private int damage;
}
