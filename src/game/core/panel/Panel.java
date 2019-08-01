package game.core.panel;

import render2d.elements.Point;

public abstract class Panel {
	
	protected Point base;
	
	public Panel(Point base) {
		this.base = base;
	}
	
	public Point getBase() {
		return base;
	}

	public abstract void load();
	public abstract void show();
	public abstract void toRender();
	
}
