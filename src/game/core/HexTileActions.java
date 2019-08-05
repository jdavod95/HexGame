package game.core;

import java.util.List;

import game.core.screen.battleField.GameField;
import game.match.Combat;
import game.unit.Unit;
import render2d.Color;
import render2d.Hexagon;
import render2d.Render;
import render2d.drawable.Shape;
import render2d.drawable.ShapeBuilder;
import render2d.elements.Action;
import render2d.elements.CursorActions;

public class HexTileActions extends CursorActions{
	private final static ShapeBuilder SHAPES = new ShapeBuilder();
	
	private static HexTile selected = null;
	
	private final HexTile ownerTile;
	private Shape hoverShape;
	
	public HexTileActions(Shape shape, HexTile tile) {
		this.ownerTile = tile;
		hoverShape = SHAPES
				.newShape(
					Hexagon.class,
					shape.getPos().getNew(1, 0),
					shape.getW() - 2, shape.getH() - 5)
				.getShape();
		
		setHover(
			new Action() {
				@Override
				public void run() {
					hoverShape.setColor(new Color(Color.WHITE, 0.2));
					Render.addUi(hoverShape, 5);
				}
			}
		);
		
		setClick(
			new Action() {
				@Override
				public void run() {
					hoverShape.setColor(new Color(Color.WHITE, 0.6));
					Render.addUi(hoverShape, 2);
					if(hasSelected())
						clickLogic();
				}
			}
		);
	}
	
	private void clickLogic() {
		if(!ownerTile.hasUnit() && ownerTile.isHighlighted()){
			ownerTile.setUnit(selected.replaceUnit(ownerTile.getUnit()));
			clearSelected();
		}
	}

	private static boolean hasSelected() {
		return selected != null;
	}

	private static void clearSelected() {
		selected.unSelect();
		selected = null;
		GameField.clearHighlight();
		Combat.takenPlayerAction();
	}
	
	public static void setSelectedTile(HexTile onTile) {
		selected = onTile;
		onTile.select();
	}
	
	public static Unit getSelectedUnit() {
		return selected.getUnit();
	}
	public static void unitActionSelect(String action) {
		// movement
		List<HexTile> valid = GameField.getTilesInRange(
					selected,
					selected.getUnit().getSpeed());
		for(HexTile tile : valid)
			tile.lightUp();
	}
	
}
