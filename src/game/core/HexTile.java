
package game.core;

import game.core.panel.GameField;
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
	
	public int getH() {
		return tileSkin.getScale();
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
	
	public void switchSelected() {
		if(selected)
			selected = false;
		else
			selected = true;
	}
	
	public void switchHighlighted() {
		if(highlighted)
			highlighted = false;
		else
			highlighted = true;
	}

	public boolean isSelected() {
		return selected;
	}

	public boolean isHighlighted() {
		return highlighted;
	}
	
	
}
