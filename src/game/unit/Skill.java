package game.unit;

import game.core.HexDirection;
import game.core.HexTile;

public abstract class Skill {
	public final static Skill EMPTY = new Skill() {
		@Override
		public void apply(HexTile target) {}

		@Override
		public HexDirection[] areaPattern(HexDirection facing) {
			return new HexDirection[]{HexDirection.MIDDLE};
		}
	};
	
	private String name;
	private int minRange;
	private int maxRange;
	private int arcLength;
	
	private String iconTexName = "SWORT";
	
	public abstract void apply(HexTile target);
	public abstract HexDirection[] areaPattern(HexDirection facing);
	
	public final String getTexName() {
		return iconTexName;
	}
}
