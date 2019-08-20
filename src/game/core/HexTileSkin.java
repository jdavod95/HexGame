package game.core;

import render2d.Color;
import render2d.Hexagon;
import render2d.HexagonClick;
import render2d.Render;
import render2d.drawable.Clickable;
import render2d.drawable.Shape;
import render2d.drawable.ShapeBuilder;
import render2d.elements.Point;

public class HexTileSkin {
	
	public final static Color BASE_COLOR = new Color(168, 168, 168);
	public final static Color SELECT_COLOR = new Color(0, 224, 96);
	public final static Color HIGHLIGHT_COLOR = new Color(224, 224, 0);
	public final static Color HOSTILE_COLOR = new Color(224, 0, 0);

	private final static ShapeBuilder SHAPES = new ShapeBuilder();
	
	private HexTile owner;
	
	private Shape baseShape;
	private Shape unitShape;
	private Shape selectionShape;
	private Shape[] highlightShape;
	private Shape[] hostileShape;
	private int scale;

	private Point screenPos;
	
	public HexTileSkin(HexTile owner, Point screenPos, int scale) {
		this.owner = owner;
		this.screenPos = screenPos;
		this.scale = scale;
		
		initBaseShape();
		initSelectShape();
		initHighlightShape();
		initHostileShape();
	}

	private void initHostileShape() {
		hostileShape = new Shape[] {
			SHAPES
			.newShape(
					Hexagon.class,
					baseShape.getPos().getNew(3, 3),
					baseShape.getW() - 6, baseShape.getH() - 6)
			.setColor(HOSTILE_COLOR)
			.getShape(),

			SHAPES
			.newShape(
					Hexagon.class,
					baseShape.getPos().getNew(6, 6),
					baseShape.getW() - 12, baseShape.getH() - 12)
			.setColor(BASE_COLOR)
			.getShape()
		};
	}

	private void initHighlightShape() {
		highlightShape = new Shape[] {	
			SHAPES
			.newShape(
					Hexagon.class,
					baseShape.getPos().getNew(3, 3),
					baseShape.getW() - 6, baseShape.getH() - 6)
			.setColor(HIGHLIGHT_COLOR)
			.getShape(),

			SHAPES
			.newShape(
					Hexagon.class,
					baseShape.getPos().getNew(6, 6),
					baseShape.getW() - 12, baseShape.getH() - 12)
			.setColor(BASE_COLOR)
			.getShape()};
	}

	private void initSelectShape() {
		selectionShape = SHAPES
			.newShape(
					Hexagon.class,
					baseShape.getPos().getNew(6, 6),
					baseShape.getW() - 12, baseShape.getH() - 12)
			.setColor(SELECT_COLOR)
			.getShape();
	}

	private void initBaseShape() {
		baseShape = SHAPES
			.newShape(Hexagon.class, screenPos, scale, scale)
			.setColor(BASE_COLOR)
			.setClickable(
					HexagonClick.class,
					new HexTileActions(SHAPES.getShape(), owner))
			.getShape();
	}
	
	public void toRender(int l) {
		Render.addScn(baseShape, l);
		if(owner.isHighlighted()) {
			if(owner.hasUnit()
			&& !HexTileActions.getSelectedUnit().isOwner(owner.getUnit().getOwner()))
				Render.addScn(hostileShape, l);
			else
				Render.addScn(highlightShape, l);
		}
		if(owner.isSelected()) 
			Render.addScn(selectionShape, l);
		if(owner.hasUnit())
			Render.addScn(unitShape, l);
	}
	
	public void setUnitSkin(String texName) {
		unitShape = SHAPES
				.newRectangle(screenPos.getNew(0, -scale/3), scale, scale)
				.setTexture(texName)
				.getShape();
	}
	
	public Clickable getClickable() {
		return baseShape.getClickable();
	}

	public int getScale() {
		return scale;
	}

	public Point getPos() {
		return baseShape.getPos().getNew();
	}
	
	
}
