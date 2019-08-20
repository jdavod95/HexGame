package game.core.screen;

import game.core.screen.battleField.GameField;
import game.core.screen.bottomPanel.BottomPanel;
import game.match.Combat;
import game.match.Player;
import render2d.elements.Point;
import root.HexBattleApp;

public class CombatScreen implements Screen{

	private static final Point GAMEFIELD_POS = new Point(10, 10);
	private static final int BOTTOMPANEL_WIDTH = HexBattleApp.W;
	private static final int BOTTOMPANEL_HEIGHT = 200;
	public static final Point BOTTOMPANEL_POS = new Point(0, HexBattleApp.H - BOTTOMPANEL_HEIGHT);
	
	private Panel gameField;
	private Panel unitPanel;

	private Combat combat;

	private Player p1;
	private Player p2;
	
	public CombatScreen(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public void load() {
		gameField = new GameField(GAMEFIELD_POS, p1, p2);
		unitPanel = new BottomPanel(BOTTOMPANEL_POS, BOTTOMPANEL_WIDTH, BOTTOMPANEL_HEIGHT);
		combat = new Combat(p1, p2);
	}

	public void show() {
		combat.cycle();
		toRender();
	}

	public void toRender() {
		gameField.toRender();
		unitPanel.toRender();
	}
}
