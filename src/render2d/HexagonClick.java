package render2d;

import render2d.drawable.ClickableShapeDummy;
import render2d.drawable.Shape;
import render2d.elements.CursorActions;
import render2d.elements.Point;

public class HexagonClick extends ClickableShapeDummy{

	public HexagonClick(Shape shape, CursorActions action) {
		super(shape, action);
	}
	
	@Override
	public boolean contains(Point m) {
		boolean inside = false;
		int mouseX = m.getX();
		int mouseY = m.getY(); 
		
		int shapeX = shape.getPos().getX();
		int shapeY = shape.getPos().getY();
		int shapeW = shape.getW();
		int shapeH = shape.getH();
		
		if(mouseX > shapeX
		&& mouseX < shapeX + shapeW
		&& mouseY > shapeY
		&& mouseY < shapeY + shapeH
		
		&& mouseY > (2/4d) * (mouseX - shapeX) - shapeH * (1/4d) + shapeY
		&& mouseY > (-2/4d) * (mouseX - shapeX) + shapeH * (1/4d) + shapeY
		&& mouseY < (-2/4d) * (mouseX - shapeX) + shapeH * (5/4d) + shapeY
		&& mouseY < (2/4d) * (mouseX - shapeX) + shapeH * (3/4d) + shapeY
		)
			inside = true;
		
		return inside;
	}

}
