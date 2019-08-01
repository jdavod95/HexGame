package game.items;

import game.match.StatModifier;

public class Armor extends Item{
	public final static Armor EMPTY = new Armor(StatModifier.EMPTY);

	public Armor(StatModifier mods) {
		super(mods);
	}
	

}
