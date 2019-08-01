package game.core.panel;

import game.match.Combat;
import game.match.Player;
import render2d.elements.Point;

public class CombatScreen extends Panel{

	private Panel gameField;
	private Panel unitPanel;

	private Combat combat;

	private Player p1;
	private Player p2;
	public CombatScreen(Player p1, Player p2) {
		super(new Point(0, 0));
		this.p1 = p1;
		this.p2 = p2;
	}
	@Override
	public void load() {
		gameField = new GameField(new Point(10, 10));
		gameField.load();
		unitPanel = new UnitPanel();
		unitPanel.load();
		combat = new Combat(p1, p2);
	}

	@Override
	public void show() {
		gameField.show();
		unitPanel.show();
		combat.cycle();
		toRender();
	}

	@Override
	public void toRender() {
		gameField.toRender();
		unitPanel.toRender();
	}
}
