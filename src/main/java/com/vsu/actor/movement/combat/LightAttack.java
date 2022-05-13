package com.vsu.actor.movement.combat;

import com.vsu.actor.model.Character;
import com.vsu.actor.movement.MovementResult;
import com.vsu.actor.movement.MovementType;

import static com.vsu.App.logger;

public class LightAttack extends MeleeAttack {
    private String tag;

    public LightAttack() {
        super(70, 50);
        type = MovementType.LightAttack;
    }

    //конструктор для тестов
    public LightAttack(String tag) {
        this();
        this.tag = tag;
    }

    public LightAttack(double sectorHalfAngle, double radius) {
        super(sectorHalfAngle, radius);
        type = MovementType.LightAttack;
    }

    @Override
    public MovementResult apply(Character character) {
        movementResult = super.apply(character);
        if (movementResult != null) {
            logger.info(character + " made" + type + ": " + movementResult.getDamageData().getDamageDone());
        } else {
            logger.debug(character + " made " + type);
        }
        return movementResult;
    }
}
