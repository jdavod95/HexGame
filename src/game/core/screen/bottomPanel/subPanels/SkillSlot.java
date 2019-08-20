package game.core.screen.bottomPanel.subPanels;

import game.core.HexTileSkin;
import game.core.screen.Panel;
import game.core.screen.bottomPanel.BottomPanel;
import render2d.Color;
import render2d.Render;
import render2d.drawable.RectangleClick;
import render2d.drawable.Shape;
import render2d.drawable.ShapeBuilder;
import render2d.elements.Cursor;
import render2d.elements.Point;

public class SkillSlot extends Panel{

	private final static ShapeBuilder SHAPES = new ShapeBuilder();

	public final static int LAYER = SkillPanel.LAYER + 1;
	public final static Color HIGHLIGHT_COLOR = HexTileSkin.HIGHLIGHT_COLOR;
	public final static Color BASE_COLOR = SkillPanel.BASE_COLOR;
	
	public final static int BASE_MARGIN = BottomPanel.BASE_MARGIN;
	
	private final static int NUDGE = 1;

	private Shape baseShape;
	private Shape icon;
	
	private boolean isDown = false;
	private boolean selected = false;
	
	public SkillSlot(Point basePos, int scale) {
		super(basePos, scale, scale);
		initBaseShape();
		initIcon();
	}

	private void initIcon() {
		icon = SHAPES
			.newRectangle(
					basePos.getNew(BASE_MARGIN, BASE_MARGIN),
					width - BASE_MARGIN * 2, height - BASE_MARGIN * 2)
			.setTexture("")
			.setClickable(RectangleClick.class, new SkillSlotActions(this))
			.getShape();
	}

	private void initBaseShape() {
		baseShape = SHAPES
				.newRectangle(basePos,
						width, height)
				.setColor(BASE_COLOR)
				.getShape();
	}

	@Override
	public void toRender() {
		updateBaseShape();
		updateIcon();
		Render.addUi(baseShape, LAYER);
		Render.addUi(icon, LAYER);
		Cursor.addClickable(icon.getClickable());
	}
	
	private void updateIcon() {
		int margin = BASE_MARGIN;
		if(isDown)
			margin += NUDGE;
		icon.setPos(getUpdatedBasePos().getNew(margin, margin));
	}
	
	private void updateBaseShape() {
		baseShape.setPos(getUpdatedBasePos());
		if(selected)
			baseShape.setColor(HIGHLIGHT_COLOR);
		else
			baseShape.setColor(BASE_COLOR);
	}
	
	public void setTexName(String texName) {
		icon.setTexName(texName);
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
