package game.match;

import java.util.Arrays;
import java.util.List;

import game.unit.Unit;

public class Player {
	public final static Player EMPTY = new Player();
	public final static Player REAL = new Player();
	
	private List<Unit> group;
	
	public Player() {}
	
	public void loadUnits(Unit[] units) {
		group = Arrays.asList(units);
	}
	
	public List<Unit> getGroup() {
		return group;
	}
}
