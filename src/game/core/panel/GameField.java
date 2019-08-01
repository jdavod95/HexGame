package game.core.panel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import game.core.HexDirection;
import game.core.HexTile;
import game.match.Player;
import game.unit.Unit;
import game.unit.characters.Doktor;
import game.unit.characters.NoUnit;
import render2d.Color;
import render2d.Render;
import render2d.drawable.Rectangle;
import render2d.drawable.ShapeBuilder;
import render2d.elements.Cursor;
import render2d.elements.Point;

public class GameField extends Panel{

	public final static int TILE_COUNT_HOR = 11;
	public final static int TILE_COUNT_VER = 7;
	private final static int TILE_SCALE = 80;
	private final static ShapeBuilder SHAPES = new ShapeBuilder();
	private final static Color BASE_COLOR = new Color(224, 224, 224);
	
	private HexTile[][] hexGrid = new HexTile[TILE_COUNT_VER][TILE_COUNT_HOR];
	private Rectangle gridBase;

	private static GameField field;
	
	public GameField(Point base) {
		super(base);
		field = this;
	}
	
	@Override
	public void show() {
		for (int i = 0; i < TILE_COUNT_VER; i++) {
			for (int j = 0; j < TILE_COUNT_HOR; j++) {
				Cursor.addClickable(hexGrid[i][j].getClickable());
			}
		}
	}
	
	@Override
	public void toRender() {
		Render.addBgr(gridBase, 1);
		for (int i = 0; i < TILE_COUNT_VER; i++) {
			for (int j = 0; j < TILE_COUNT_HOR; j++) {
				hexGrid[i][j].toRender(5);
			}
		}
	}
	
	@Override
	public void load() {
		initMap();
	}
	
	private void initMap() { 
		int scale = TILE_SCALE;
		Point basePos = new Point(scale/2, scale/2);
		gridBase = (Rectangle) SHAPES
				.newRectangle(basePos.getNew(-scale/4, -scale/4),
						scale * (TILE_COUNT_HOR + 1) + scale/4,
						scale * (TILE_COUNT_VER + 1) - scale * 3/2)
				.setColor(BASE_COLOR)
				.getShape();
		
		for (int i = 0; i < TILE_COUNT_VER; i++) {
			for (int j = 0; j < TILE_COUNT_HOR; j++) {
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
		Player.REAL.loadUnits(new Unit[] {new Doktor(Player.REAL, hexGrid[0][0])});
		hexGrid[0][0].setUnit(Player.REAL.getGroup().get(0));
		

		Player.EMPTY.loadUnits(new Unit[] {new NoUnit(hexGrid[1][0])});
	}
	
	public HexTile getNextTo(Point gridPos, HexDirection to) {
		int gridX = gridPos.getX() + to.getVector().getX();
		int gridY = gridPos.getY() + to.getVector().getY();
		if(inBounds(gridX, gridY))
			return hexGrid[gridY][gridX];
		return hexGrid[gridPos.getY()][gridPos.getX()];
	}

	private static boolean inBounds(int gridX, int gridY) {
		return gridX < TILE_COUNT_HOR && gridX > 0
			&& gridY < TILE_COUNT_VER && gridY > 0;
	}

	public static HexTile[] getTilesInRange(HexTile center, int range) {
		List<HexTile> tiles = new ArrayList<>();
		Point centerPos = center.getGridPos();
		for (int i = centerPos.getY() - range; i < centerPos.getY() + range; i++) {
			for (int j = centerPos.getX() - range; j < centerPos.getX() + range; j++) {
				if(inBounds(j, i)) 
					tiles.add(field.hexGrid[i][j]);
			}
		}
		return (HexTile[]) Array.newInstance(HexTile.class, tiles.size());
	}
	
	public static void clearHighlight() {
		for (int i = 0; i < TILE_COUNT_VER; i++) {
			for (int j = 0; j < TILE_COUNT_HOR; j++) {
				if(field.hexGrid[i][j].isHighlighted())
					field.hexGrid[i][j].switchHighlighted();
			}
		}
	}
}
