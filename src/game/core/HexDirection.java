package game.core;

import render2d.elements.Point;

public enum HexDirection {
	NORTH_EAST	(new Point(1, -1)),
	EAST		(new Point(1, 0)),
	SOUTH_EAST	(new Point(1, 1)),
	SOUTH_WEST	(new Point(-1, 1)),
	WEST		(new Point(-1, 0)),
	NORTH_WEST	(new Point(-1, -1)),
	MIDDLE		(new Point(0, 0))
	;
	
	private final Point vector;
	
	private HexDirection(Point vector) {
		this.vector = vector;
	}
	
	public Point getVector() {
		return vector.getNew();
	}
	
	public HexDirection getDirection(Point vector) {
		if(vector.equals(this.vector))
			return this;
		return MIDDLE;
	}
	
	public HexDirection directionClockwise(boolean counter) {
		int x = 0;
		int y = 0;
		int east = 1;
		if(counter)
			east = -1;
		if(vector.getX() == east)
			if(vector.getY() < 1)
				y = 1;
			else
				x = -2;
		else
			if(vector.getY() > -1)
				y = -1;
			else
				x = 2;
		if(counter)
			x *= -1;
		return getDirection(vector.getNew(x, y));
	}
	
	public HexDirection directionCounterClockwise() {
		int x = 0;
		int y = 0;
		if(vector.getX() == 1)
			if(vector.getY() > -1)
				y = -1;
			else
				x = -2;
		else
			if(vector.getY() < 1)
				y = 1;
			else
				x = 2;
		return getDirection(vector.getNew(x, y));
	}
}
