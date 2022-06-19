package com.vsu.actor.interfaces;

import com.vsu.actor.Equipment;
import com.vsu.actor.model.Actor;

public interface Equipable {
    void equip(Equipment equipment, Actor actor);
}
