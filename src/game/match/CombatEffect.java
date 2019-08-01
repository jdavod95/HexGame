package game.match;

import game.unit.Unit;

public abstract class CombatEffect {
	private StatModifier mods;
	
	public CombatEffect(StatModifier mods) {
		this.mods = mods;
	}

	public StatModifier getMods() {
		return mods;
	}

	public abstract void apply(Unit unit);
}
