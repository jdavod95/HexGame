package game.core.screen;

import render2d.Camera;
import render2d.elements.Point;

public abstract class Panel {
	
	protected Point basePos;

	protected int width;
	protected int height;
	
	public Panel(Point base, int w, int h) {
		this.basePos = base;
		this.width = w;
		this.height = h;
	}
	
	public Point getBase() {
		return basePos;
	}
	
	protected void setBase(Point base) {
		this.basePos = base;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Point getUpdatedBasePos() {
		return Camera.getCameraPos().getNew(basePos);
	}
	
	public abstract void toRender();
	
}
