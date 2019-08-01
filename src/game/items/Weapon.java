package game.items;

import game.match.StatModifier;

public class Weapon extends Item {
	public final static Weapon EMPTY = new Weapon(StatModifier.EMPTY);

	public Weapon(StatModifier mods) {
		super(mods);
	}
	
	

}
