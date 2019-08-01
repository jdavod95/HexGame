package game.items;

import game.match.StatModifier;

public abstract class Item {
	
	private StatModifier mod;
	
	public Item(StatModifier mods) {
		this.mod = mods;
	}

	public int getSpeedMod() {
		return mod.getSpeed();
	}

	public int getDefenseMod() {
		return mod.getDefense();
	}

	public double getEvasionMod() {
		return mod.getEvasion();
	}
	
	public StatModifier getMods() {
		return mod;
	}
}
