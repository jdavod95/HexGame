
package game.core;

import game.core.screen.battleField.GameField;
import game.unit.Unit;
import game.unit.characters.NoUnit;
import render2d.drawable.Clickable;
import render2d.elements.Point;

public class HexTile {
	
	private HexTileSkin tileSkin;
	
	private Point gridPos;
	private Unit unit;
	
	private HexDirection facing;
	private boolean selected = false;
	private boolean highlighted = false;
	
	public HexTile(Point gridPos, Point screenPos, int scale, Unit unit) {
		this.tileSkin = new HexTileSkin(this, screenPos, scale);
		this.gridPos = gridPos;
		setUnit(unit);
		
		if(gridPos.getY() < GameField.TILE_COUNT_HOR/2)
			facing = HexDirection.EAST;
		else 
			facing = HexDirection.WEST;
	}
	
	public Clickable getClickable() {
		return tileSkin.getClickable();
	}
	
	public void toRender(int l) {
		tileSkin.toRender(l);
	}
	
	public Point getGridPos() {
		return gridPos;
	}
	
	public Unit replaceUnit(Unit newUnit) {
		Unit oldUnit = this.unit;
		setUnit(newUnit);
		return oldUnit;
	}

	public void setUnit(Unit newUnit) {
		this.unit = newUnit;
		this.unit.setOnTile(this);
		tileSkin.setUnitSkin(unit.getTexName());
	}
	
	public Unit getUnit() {
		return unit;
	}
	
	public boolean hasUnit() {
		return !(unit instanceof NoUnit);
	}

	public HexDirection getFacing() {
		return facing;
	}

	public void setFacing(HexDirection facing) {
		this.facing = facing;
	}

	public boolean hostileTo(HexTile tile) {
		return !tile.getUnit().isOwner(unit.getOwner())
			&& tile.hasUnit()
			&& hasUnit();
	}
	
	public void select() {
		selected = true;
	}
	
	public void unSelect() {
		selected = false;
	}
	
	public void lightUp() {
		highlighted = true;
	}
	
	public void lightOff() {
		highlighted = false;
	}

	public boolean isSelected() {
		return selected;
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public Point getScreenPos() {
		return tileSkin.getPos();
	}
	
	
}
