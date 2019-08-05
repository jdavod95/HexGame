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
	private final static Color BASE_COLOR = new Color(168, 168, 168);
	private final static Color SELECT_COLOR = new Color(0, 224, 96);
	private final static Color HIGHLIGHT_COLOR = new Color(224, 224, 0);

	private final static ShapeBuilder SHAPES = new ShapeBuilder();
	
	private HexTile owner;
	
	private Shape baseSkin;
	private Shape unitSkin;
	private Shape selectionSkin;
	private Shape[] highlightSkin;
	private int scale;

	private Point screenPos;
	
	public HexTileSkin(HexTile owner, Point screenPos, int scale) {
		this.owner = owner;
		this.screenPos = screenPos;
		this.scale = scale;
		
		baseSkin = SHAPES
				.newShape(Hexagon.class, screenPos, scale, scale)
				.setColor(BASE_COLOR)
				.setClickable(
						HexagonClick.class,
						new HexTileActions(SHAPES.getShape(), owner))
				.getShape();
		
		selectionSkin = SHAPES
				.newShape(
						Hexagon.class,
						baseSkin.getPos().getNew(6, 6),
						baseSkin.getW() - 12, baseSkin.getH() - 12)
				.setColor(SELECT_COLOR)
				.getShape();
		
		highlightSkin = new Shape[2];
		highlightSkin[0] = SHAPES
				.newShape(
						Hexagon.class,
						baseSkin.getPos().getNew(3, 3),
						baseSkin.getW() - 6, baseSkin.getH() - 6)
				.setColor(HIGHLIGHT_COLOR)
				.getShape();
		
		highlightSkin[1] = SHAPES
				.newShape(
						Hexagon.class,
						baseSkin.getPos().getNew(6, 6),
						baseSkin.getW() - 12, baseSkin.getH() - 12)
				.setColor(BASE_COLOR)
				.getShape();
	}
	
	public void toRender(int l) {
		Render.addScn(baseSkin, l);
		if(owner.isHighlighted())
			Render.addScn(highlightSkin, l);
		if(owner.isSelected()) 
			Render.addScn(selectionSkin, l);
		if(owner.hasUnit())
			Render.addScn(unitSkin, l);
	}
	
	public void setUnitSkin(String texName) {
		unitSkin = SHAPES
				.newRectangle(screenPos.getNew(0, -scale/3), scale, scale)
				.setTexture(texName)
				.getShape();
	}
	
	public Clickable getClickable() {
		return baseSkin.getClickable();
	}

	public int getScale() {
		return scale;
	}

	public Point getPos() {
		return baseSkin.getPos().getNew();
	}
	
	
}
