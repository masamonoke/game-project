package com.vsu.actor.movement;

import com.vsu.actor.model.Actor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import static com.vsu.App.logger;

@EqualsAndHashCode
@Getter
@Setter
public abstract class Movement {
    protected int price;
    @EqualsAndHashCode.Exclude
    protected MovementResult movementResult;
    protected MovementType movementType;

    //TODO: доделать формулы под статы от экипировки и оружия
    public abstract MovementResult apply(Actor actor);

    @Override
    public String toString() {
        return movementType.toString();
    }

    public abstract void log(Actor actor);

    public boolean takeResources(Actor actor) {
        var staminaLeft = actor.getStamina() - price;
        if (staminaLeft > 0) {
            actor.changeStamina(-price);
            logger.info(actor + " spent " + price + " stamina");
            return true;
        } else if (staminaLeft == 0) {
            actor.setStamina(1);
            return true;
        }
        else {
            logger.info("Not enough stamina");
        }
        return false;
    }
}
