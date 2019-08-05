package game.core.screen.bottomPanel;

import render2d.Color;
import render2d.Render;
import render2d.drawable.RectangleClick;
import render2d.drawable.Shape;
import render2d.drawable.ShapeBuilder;
import render2d.elements.Cursor;
import render2d.elements.Point;

public class SkillSlot {

	private final static ShapeBuilder SHAPES = new ShapeBuilder();
	private final static int NUDGE = 1;
	private final static int MARGIN = UnitPanelLayout.BASE_MARGIN;
	private final static Color SELECT_COLOR = new Color(224, 224, 0);
	
	private Color baseColor;
	private Point relativePos;
	private int heigth;
	private int width;
	private Shape base;
	private Shape icon;
	
	private boolean isDown = false;
	private boolean selected = false;
	
	public SkillSlot(Color baseColor, Point relativePos, int heigth, int width) {
		this.baseColor = baseColor;
		this.relativePos = relativePos;
		this.heigth = heigth;
		this.width = width;
		base = SHAPES
				.newRectangle(
						relativePos,
						width, heigth)
				.setColor(baseColor)
				.getShape();
		
		icon = SHAPES
				.newRectangle(
						relativePos.getNew(
								MARGIN,
								MARGIN),
						width - MARGIN * 2, heigth - MARGIN * 2)
				.setTexture("")
				.setClickable(RectangleClick.class, new SkillSlotClick(this))
				.getShape();
	}

	public void toRender(Point bottomPanelBase, String texName, int layer) {
		Render.addUi(getBase(bottomPanelBase), layer);
		Shape icon = getIcon(bottomPanelBase, texName);
		Render.addUi(icon, layer);
		Cursor.addClickable(icon.getClickable());
	}
	
	private Shape getIcon(Point updatedBase, String texName) {
		int margin = MARGIN;
		if(isDown)
			margin += NUDGE;
		icon.setPos(
				updatedBase.getNew(
						relativePos.getX() + margin,
						relativePos.getY() + margin)
						);
		icon.setTexName(texName);
		return icon;
	}
	
	private Shape getBase(Point updatedBase) {
		base.setPos(updatedBase.getNew(relativePos));
		if(selected)
			base.setColor(SELECT_COLOR);
		else
			base.setColor(baseColor);
		return base;
	}
	
	public void setDown() {
		isDown = true;
	}	
	
	public void unsetDown() {
		isDown = false;
	}
	
	public void setSelect() {
		selected = true;
	}	
	
	public void unSelect() {
		selected = false;
	}
}
