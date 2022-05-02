package com.vsu.actor.movement;

import com.vsu.actor.model.Character;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
public abstract class Movement {
    @EqualsAndHashCode.Exclude
    protected Movement prev;
    @EqualsAndHashCode.Exclude
    protected MovementResult movementResult;
    protected MovementType type;

    public abstract MovementResult apply(Movement prev, Character character);
}
