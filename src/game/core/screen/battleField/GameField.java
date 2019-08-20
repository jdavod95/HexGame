package game.core.screen.battleField;

import java.util.ArrayList;
import java.util.List;

import game.core.HexDirection;
import game.core.HexTile;
import game.core.screen.CombatScreen;
import game.core.screen.Panel;
import game.core.screen.bottomPanel.BottomPanel;
import game.match.Player;
import game.unit.Unit;
import game.unit.characters.Doktor;
import game.unit.characters.NoUnit;
import render2d.Camera;
import render2d.Color;
import render2d.Render;
import render2d.drawable.Rectangle;
import render2d.drawable.ShapeBuilder;
import render2d.elements.Cursor;
import render2d.elements.Point;
import root.HexBattleApp;

public class GameField extends Panel{

	public final static int TILE_COUNT_HOR = 20;
	public final static int TILE_COUNT_VER = 20;
	
	private final static int TILE_SCALE = 50;
	private final static ShapeBuilder SHAPES = new ShapeBuilder();
	private final static Color BASE_COLOR = new Color(224, 224, 224);
	
	private static Rectangle gridBase;
	
	private static HexTile[][] hexGrid = new HexTile[TILE_COUNT_VER][TILE_COUNT_HOR];
	private Player attacker;
	private Player defender;
	
	public GameField(Point base, Player attacker, Player defender) {
		super(base, 0, 0);
		this.attacker = attacker;
		this.defender = defender;
		
		initMap(TILE_COUNT_HOR, TILE_COUNT_VER);
		unitSetup(attacker, defender);
	}

	@Override
	public void toRender() {
		Render.addBgr(gridBase, 1);
		for (int i = 0; i < TILE_COUNT_VER; i++) {
			for (int j = 0; j < TILE_COUNT_HOR; j++) {
				if(posInsideView(hexGrid[i][j].getScreenPos())) {
					hexGrid[i][j].toRender(5);
					Cursor.addClickable(hexGrid[i][j].getClickable());	
				}
			}
		}
	}

	private static void initMap(int mapW, int mapH) { 
		int scale = TILE_SCALE;
		Point basePos = new Point(scale/2, scale/2);

		gridBase = (Rectangle) SHAPES
				.newRectangle(basePos.getNew(-scale/4, -scale/4),
						scale * (mapW + 1) + scale/4,
						scale * (mapH + 1) - scale * 3/2)
				.setColor(BASE_COLOR)
				.getShape();
		
		for (int i = 0; i < mapH; i++) {
			for (int j = 0; j < mapW; j++) {
				int offset = 0;
				if(i % 2 == 0)
					offset = scale/2;
				hexGrid[i][j] = new HexTile(
						new Point(j, i),
						basePos.getNew(
								j+j*scale + offset,
								i+i*(scale*3/4)),
						scale,
						new NoUnit(hexGrid[i][j])
					);
			}
		}
	}

	private void unitSetup(Player attacker, Player defender) {
		Player.REAL.loadUnits(new Unit[] {new Doktor(Player.REAL, hexGrid[3][5]),new Doktor(Player.REAL, hexGrid[5][5])});
		hexGrid[3][5].setUnit(Player.REAL.getGroup().get(0));
		hexGrid[5][5].setUnit(Player.REAL.getGroup().get(1));
		
		Player.REALTWO.loadUnits(new Unit[] {new Doktor(Player.REALTWO, hexGrid[7][15])});
		hexGrid[7][15].setUnit(Player.REALTWO.getGroup().get(0));
	}
	
	public static HexTile getNextTo(Point gridPos, HexDirection to) {
		int gridX = gridPos.getX() + to.getVector().getX();
		int gridY = gridPos.getY() + to.getVector().getY();
		if(inBounds(gridX, gridY))
			return hexGrid[gridY][gridX];
		return hexGrid[gridPos.getY()][gridPos.getX()];
	}

	public static List<HexTile> getTilesInRange(HexTile center, int range) {
		List<HexTile> tiles = new ArrayList<>();
		Point centerPos = center.getGridPos();
		for (int i = - range; i <= range; i++) {
			int rangeStart = - range + Math.abs(i)/2;
			int rangeEnd = range - (Math.abs(i) + 1)/2;
			if(centerPos.getY() % 2 == 0 && Math.abs(i) % 2 == 1) {
				rangeStart++;
				rangeEnd++;
			}
			for (int j = rangeStart; j <= rangeEnd; j++) {
				if(inBounds(j + centerPos.getX(), i + centerPos.getY())
				&& !(i == 0 && j == 0)) {
					tiles.add(hexGrid[i + centerPos.getY()][j + centerPos.getX()]);
				}
			}
		}
		return tiles;
	}
	
	public static void clearHighlight() {
		for (int i = 0; i < TILE_COUNT_VER; i++) {
			for (int j = 0; j < TILE_COUNT_HOR; j++) {
				if(hexGrid[i][j].isHighlighted())
					hexGrid[i][j].lightOff();
			}
		}
	}

	private static boolean inBounds(int gridX, int gridY) {
		return gridX < TILE_COUNT_HOR && gridX >= 0
			&& gridY < TILE_COUNT_VER && gridY >= 0;
	}
	
	private static boolean posInsideView(Point screenPos) {
		Point lowerBounds = Camera.getCameraPos().getNew(-TILE_SCALE, -TILE_SCALE);
		Point upperBounds = Camera.getCameraPos().getNew(HexBattleApp.W + TILE_SCALE, CombatScreen.BOTTOMPANEL_POS.getY());
		
		return screenPos.getX() > lowerBounds.getX() && screenPos.getX() < upperBounds.getX()
			&& screenPos.getY() > lowerBounds.getY() && screenPos.getY() < upperBounds.getY();
	}

}
