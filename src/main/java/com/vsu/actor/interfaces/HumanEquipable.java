package com.vsu.actor.interfaces;

import com.vsu.actor.Equipment;
import com.vsu.actor.model.Actor;

public class HumanEquipable implements Equipable {
    //экипировка добавляется в список и меняются значения статов персонажа в зависимости от нее
    //экипировка делится на слоты => если слот занят, то надо заменить
    @Override
    public void equip(Equipment equipment, Actor actor) {

    }
}
