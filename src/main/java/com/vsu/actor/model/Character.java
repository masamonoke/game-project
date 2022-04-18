package com.vsu.actor.model;

import lombok.*;

import static com.vsu.grid.model.Direction2D.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Character extends Actor {
    private String name;
    private Position pos;
    private int exp;
    private int toxic;
}
