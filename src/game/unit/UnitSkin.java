package game.unit;

import render2d.Render;
import render2d.drawable.Rectangle;
import render2d.drawable.ShapeBuilder;
import render2d.elements.Point;

public class UnitSkin {
	
	private final static ShapeBuilder SHAPES = new ShapeBuilder();
	private Rectangle skin;
	
	public UnitSkin(Point pos, int w, int h) {
		skin = (Rectangle) SHAPES.newRectangle(
				pos, w, h)
				.setTexture("DOKTOR")
				.getShape();
	}

	public void toRender() {
		Render.addScn(skin, 3);
	}
}
