package game.items.weapons;

import game.core.HexDirection;
import game.core.HexTile;
import game.items.Weapon;
import game.match.StatModifier;
import game.unit.Skill;

public class Sword extends Weapon{
	private final static Skill SLOT1 = new Skill() {
		private StatModifier mod = new StatModifier(-1, 0, 0, 0);
		@Override
		public void apply(HexTile target) {
			target.getUnit().addStatMod(mod);
		}

		@Override
		public HexDirection[] areaPattern(HexDirection facing) {
			return new HexDirection[]{
					facing,
					facing.directionClockwise(false),
					facing.directionClockwise(true)
			};
		}
	};
	// = swing 1 hex range 3 tile arc, multi hit
	
	private final static Skill SLOT2 = new Skill() {

		@Override
		public void apply(HexTile target) {
		}

		@Override
		public HexDirection[] areaPattern(HexDirection facing) {
			return new HexDirection[]{
					facing};
		}};	
	// = slash single target, attack twice, increased bleed chance

	protected Skill doublePassive = null; // = + speed, + accuracy, swing hits twice, slash hits three times
	
	// private Effect bleed = ...
	
	public Sword() {
		super(new StatModifier(0, 0, 0, 0), SLOT1, SLOT2);
	}
	
}
