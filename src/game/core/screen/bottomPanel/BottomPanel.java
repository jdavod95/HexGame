package game.core.screen.bottomPanel;

import game.core.screen.Panel;
import game.core.screen.bottomPanel.subPanels.UnitStatPanel;
import game.core.screen.bottomPanel.subPanels.SkillPanel;
import render2d.Color;
import render2d.Render;
import render2d.drawable.Shape;
import render2d.drawable.ShapeBuilder;
import render2d.elements.Point;

public class BottomPanel extends Panel{

	private final static ShapeBuilder SHAPES = new ShapeBuilder();

	public final static Color BASE_COLOR = new Color(0, 0, 16, 0.1);
	public final static int LAYER = 1;
	public final static int BASE_MARGIN = 5;

	private Panel selectedUnitStatPanel;
	private Panel hoveredUnitStatPanel;
	private Panel skillPanel;
	
	private Shape[] baseShape;
	
	public BottomPanel(Point basePos, int w, int h) {
		super(basePos, w, h);
		initBaseShape();
		initSubPanels();
	}

	@Override
	public void toRender() {
		updateBaseShape();
		Render.addUi(baseShape, LAYER);
		selectedUnitStatPanel.toRender();
		hoveredUnitStatPanel.toRender();
		skillPanel.toRender();
	}
	
	private void initBaseShape() {
		baseShape = new Shape[] {
			SHAPES
			.newRectangle(basePos,
					width, height)
			.setColor(new Color(
					(int) Color.COLBITS - BASE_COLOR.getB(),
					(int) Color.COLBITS - BASE_COLOR.getB(),
					(int) Color.COLBITS))
			.getShape(),
			
			SHAPES
			.newRectangle(basePos,
					width, height)
			.setColor(BASE_COLOR)
			.getShape(),
			
			SHAPES
			.newRectangle(basePos.getNew(0, BASE_MARGIN),
					width, height - BASE_MARGIN)
			.setColor(BASE_COLOR)
			.getShape(),
			
			SHAPES
			.newRectangle(basePos.getNew(0, height - BASE_MARGIN),
					width, BASE_MARGIN)
			.setColor(new Color(BASE_COLOR, .2))
			.getShape()
		};
	}
	
	private void updateBaseShape() {
		Point updPos = getUpdatedBasePos();
		baseShape[0].setPos(updPos);
		baseShape[1].setPos(updPos);
		baseShape[2].setPos(updPos.getNew(0, BASE_MARGIN));
		baseShape[3].setPos(updPos.getNew(0, height - BASE_MARGIN));
	}

	// smelly
	private void initSubPanels(){
		int statPanelWidth = 180;
		int statPanelHeight = height - BASE_MARGIN * 4;
		int skillSlotScale = 80;
		
		Point hoveredUnitStatPos = basePos.getNew(width - statPanelWidth - BASE_MARGIN * 2, BASE_MARGIN * 2);
		Point selectedUnitStatPos = basePos.getNew(BASE_MARGIN * 2, BASE_MARGIN * 2);
		Point skillPanelPos = basePos.getNew(BASE_MARGIN * 4 + statPanelWidth, BASE_MARGIN * 4);
		
		selectedUnitStatPanel = new UnitStatPanel(selectedUnitStatPos, statPanelWidth, statPanelHeight);
		hoveredUnitStatPanel = new UnitStatPanel(hoveredUnitStatPos, statPanelWidth, statPanelHeight);
		skillPanel = new SkillPanel(skillPanelPos, skillSlotScale);
	}
}
