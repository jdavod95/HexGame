package game.items.armors;

import game.items.Armor;
import game.match.StatModifier;

public class LeatherArmor extends Armor{
	
	public LeatherArmor() {
		super(new StatModifier(0, 1, 1, .1));
	}

}
