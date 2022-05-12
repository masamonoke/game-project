package com.vsu.service;

import com.vsu.actor.model.Character;
import com.vsu.map.Vector2;

//TODO: test
public class CharacterService {
    public void moveCharacter(Character character, Vector2 destination) {
        character.getPos().setCoords(destination);
    }

    public void moveUp(Character character) {
        character.getPos().add(Vector2.up);
    }

    public void moveDown(Character character) {
        character.getPos().add(Vector2.down);
    }

    public void moveLeft(Character character) {
        character.getPos().add(Vector2.left);
    }

    public void moveRight(Character character) {
        character.getPos().add(Vector2.right);
    }
}
