package game.items;

import game.match.StatModifier;
import game.unit.Skill;

public abstract class Weapon extends Item {
	public final static Weapon EMPTY = new Weapon(StatModifier.EMPTY, Skill.EMPTY, Skill.EMPTY) {};

	protected Skill slot1;
	protected Skill slot2;
	
	public Weapon(StatModifier mods, Skill slot1, Skill slot2) {
		super(mods);
		this.slot1 = slot1;
		this.slot2 = slot2;
	}

	public Skill getFirstSkill() {
		return slot1;
	}
	
	public Skill getSecondSkill() {
		return slot2;
	}
	
	

}
