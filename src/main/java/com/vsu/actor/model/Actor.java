package com.vsu.actor.model;

import com.vsu.actor.Equipment;
import com.vsu.actor.effect.Stats;
import com.vsu.actor.effect.TemporaryStatusEffect;
import com.vsu.actor.interfaces.Equipable;
import com.vsu.actor.interfaces.Moveable;
import com.vsu.actor.interfaces.Walkable;
import com.vsu.actor.movement.Movement;
import com.vsu.actor.movement.MovementResult;
import com.vsu.actor.movement.combat.combo.ComboTree;
import com.vsu.map.Vector2;
import lombok.*;

import java.util.UUID;

import static com.vsu.map.model.TilemapDirection2D.Position;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Actor implements Walkable, Moveable, Equipable {
    @NoArgsConstructor
    static class Resources {
        protected int hp;
        protected int mp;
        protected int stamina;
        Resources defaultResources;

        public Resources(int hp, int mp, int stamina) {
            this.hp = hp;
            this.mp = mp;
            this.stamina = stamina;
            //костыль с пустым конструктором, потому как стек оверфлоу
            defaultResources = new Resources();
            defaultResources.hp = hp;
            defaultResources.mp = mp;
            defaultResources.stamina = stamina;
        }

        public void changeHp(int v) {
            var newHp = hp + v;
            if (newHp < 0) {
                hp = 0;
            } else {
                hp = Math.min(newHp, defaultResources.hp);
            }
        }

        public void changeMp(int v) {
            var newMp = mp + v;
            if (newMp <= 0) {
                mp = 1;
            } else {
                mp = Math.min(newMp, defaultResources.mp);
            }
        }

        public void changeStamina(int v) {
            var newStamina = stamina + v;
            if (newStamina <= 0) {
                stamina = 1;
            } else {
                stamina = Math.min(newStamina, defaultResources.stamina);
            }
        }
    }

    protected String id;
    protected String name;
    protected ActorStatus status;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Resources resources;
    protected Stats stats;
    private Stats defaultStats;
    private TemporaryStatusEffect temporaryEffect;
    protected int physicalDamage;
    protected int magicalDamage;
    //TODO: расчитывать текущий тайл по pos:Vector2, либо убрать совсем
    protected Position tilePos;
    protected Vector2 pos;
    protected Vector2 cursorPos;
    protected int exp;
    //TODO: считается от статов брони и оружия и соответственно при экипировании меняются значения
    protected int modificationDamage;
    protected double criticalChance;

    @Getter(AccessLevel.NONE)
    private Equipable equipable;
    @Getter(AccessLevel.NONE)
    private Walkable walkable;
    @Getter(AccessLevel.NONE)
    private Moveable moveable;

    public Actor(String name, Equipable equipable, Walkable walkable, Moveable moveable) {
        resources = new Resources(20, 15, 20);

        physicalDamage = 1;
        magicalDamage = 1;
        id = UUID.randomUUID().toString();
        this.name = name;
        tilePos = new Position(0, 0);
        pos = new Vector2(0, 0);
        cursorPos = new Vector2(pos.x + 10, pos.y + 10);
        exp = 0;
        stats = new Stats(0, 1, 1, 1, 1, 1, 1);
        defaultStats = new Stats(stats);
        modificationDamage = 1;
        criticalChance = 0.3;
        this.equipable = equipable;
        this.walkable = walkable;
        this.moveable = moveable;
        status = ActorStatus.Alive;
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

    public int getToxic() {
        return stats.getToxic();
    }

    public void setToxic(int val) {
        stats.setToxic(val);
    }

    public int getStrength() {
        return stats.getStrength();
    }

    public void setStrength(int val) {
        stats.setStrength(val);
    }

    public int getIntelligence() {
        return stats.getIntelligence();
    }

    public void setIntelligence(int val) {
        stats.setIntelligence(val);
    }

    public int getAgility() {
        return stats.getAgility();
    }

    public void setAgility(int val) {
        stats.setAgility(val);
    }

    public int getNoise() {
        return stats.getNoise();
    }

    public void setNoise(int val) {
        stats.setNoise(val);
    }

    public int getTrickery() {
        return stats.getTrickery();
    }

    public void setTrickery(int val) {
        stats.setTrickery(val);
    }

    public int getSpeed() {
        return stats.getSpeed();
    }

    public void setSpeed(int val) {
        stats.setSpeed(val);
    }

    public int getHp() {
        return resources.hp;
    }

    public void setHp(int v) {
        resources.hp = v;
    }

    public int getStamina() {
        return resources.stamina;
    }

    public void setStamina(int v) {
        resources.stamina = v;
    }

    public int getMp() {
        return resources.mp;
    }

    public void setMp(int v) {
        resources.mp = v;
    }

    public void changeHp(int delta) {
        resources.changeHp(delta);
        if (resources.hp == 0) {
            status = ActorStatus.Dead;
        }
    }

    public void changeMp(int delta) {
        resources.changeMp(delta);
    }

    public void changeStamina(int delta) {
        resources.changeStamina(delta);
    }

    public void applyEffect(TemporaryStatusEffect temporaryEffect) {
        if (this.temporaryEffect == null) {
            this.temporaryEffect = new TemporaryStatusEffect(temporaryEffect);
        } else {
            temporaryEffect.add(this.temporaryEffect);
        }
        //контролирует, что статы актора не могут опуститься ниже 1
        temporaryEffect.apply(this);
    }

    public void endEffect() {
        var curTime = System.currentTimeMillis();
        if (curTime - temporaryEffect.getApplyTime() <= temporaryEffect.getEffectTime()) {
            return;
        }
        stats = new Stats(defaultStats);
        temporaryEffect = null;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public void equip(Equipment equipment, Actor actor) {
        equipable.equip(equipment, actor);
    }

    @Override
    public MovementResult move(Movement movement, Actor actor) {
        return moveable.move(movement, actor);
    }

    @Override
    public void restoreResources(Actor actor) {
        moveable.restoreResources(actor);
    }

    @Override
    public ComboTree getComboTree() {
        return moveable.getComboTree();
    }

    @Override
    public void walkUp(Actor actor) {
        walkable.walkUp(actor);
    }

    @Override
    public void walkDown(Actor actor) {
        walkable.walkDown(actor);
    }

    @Override
    public void walkLeft(Actor actor) {
        walkable.walkLeft(actor);
    }

    @Override
    public void walkRight(Actor actor) {
        walkable.walkRight(actor);
    }

    @Override
    public void walkUpRight(Actor actor) {
        walkable.walkUpRight(actor);
    }

    @Override
    public void walkUpLeft(Actor actor) {
        walkable.walkUpLeft(actor);
    }

    @Override
    public void walkDownRight(Actor actor) {
        walkable.walkDownRight(actor);
    }

    @Override
    public void walkDownLeft(Actor actor) {
        walkable.walkDownLeft(actor);
    }
}
