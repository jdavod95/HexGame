package game.core.screen.bottomPanel.subPanels;

import game.core.screen.Panel;
import game.core.screen.bottomPanel.BottomPanel;
import render2d.Color;
import render2d.Render;
import render2d.drawable.Shape;
import render2d.drawable.ShapeBuilder;
import render2d.elements.Point;

public class UnitStatPanel extends Panel {
	
	private final static ShapeBuilder SHAPES = new ShapeBuilder();
	
	public final static Color BASE_COLOR = BottomPanel.BASE_COLOR;
	public final static int LAYER = BottomPanel.LAYER + 1;
	
	private Shape baseShape;
	
	public UnitStatPanel(Point base, int w, int h) {
		super(base, w, h);
		initBaseShape();
	}

	protected void initBaseShape() {
		baseShape =	SHAPES
				.newRectangle(getUpdatedBasePos(),
						width, height)
				.setColor(BASE_COLOR)
				.getShape();
	}

	@Override
	public void toRender() {
		updateBaseShape();
		Render.addUi(baseShape, LAYER);
	}
	
	private void updateBaseShape() {
		baseShape.setPos(getUpdatedBasePos());
	}
}
