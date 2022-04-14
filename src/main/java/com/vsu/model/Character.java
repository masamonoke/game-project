package com.vsu.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static com.vsu.model.Direction2D.*;

@AllArgsConstructor
@Getter
@Setter
public class Character {
    private String name;
    private Position pos;
}
