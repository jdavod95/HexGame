package render2d;

import render2d.drawable.Shape;
import render2d.elements.Point;

public class Hexagon extends Shape{

	// w : h
	// 6 : 7
	// gives a rounder shape
	
	public Hexagon(Point pos, int w, int h) {
		super(pos, w, h);
	}

	@Override
	public void reScale(int arg0, int arg1) {
	}

	@Override
	protected void drawShape() {

		drawTriangle(
				getPos().getNew(0, (int) (getH()/4)),
				new Point(getW()/2, (int) (-getH()/4)),
				new Point(getW(), 0)
				);
		
		drawTriangle(
				getPos().getNew(0, (int) (getH()/4)),
				new Point(getW(), 0),
				new Point(0, (int) (getH()/2))
				);
		
		drawTriangle(
				getPos().getNew(getW(), (int) (getH()/4)),
				new Point(-getW(), (int) (getH()/2)),
				new Point(0, (int) (getH()/2))
				);
		
		drawTriangle(
				getPos().getNew(0, getH()/4 + getH()/2),
				new Point(getW()/2, getH()/4),
				new Point(getW(), 0)
				);
		
	}
	
}
