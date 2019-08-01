package game.unit;

import game.core.HexDirection;
import game.core.HexTile;

public abstract class Skill {
	private String name;
	private int minRange;
	private int maxRange;
	private int arcLength;
	
	public abstract void apply(HexTile target);
	public abstract HexDirection[] areaPattern(HexDirection facing);
}
