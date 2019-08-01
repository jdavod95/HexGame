package game.core.panel;

import render2d.Camera;
import render2d.Color;
import render2d.Render;
import render2d.drawable.Shape;
import render2d.drawable.ShapeBuilder;
import render2d.elements.Point;
import root.HexBattleApp;

public class UnitPanel extends Panel{

	private final static ShapeBuilder SHAPES = new ShapeBuilder();

	private final static int PANEL_H = 200;
	private final static int SCREEN_W = HexBattleApp.W;
	private final static int SCREEN_H = HexBattleApp.H;
	private final static int BASE_MARGIN = 5;
	private final static int STATPANEL_W = 180;
	private final static int SLOT_SCALE = 80;
	public final static Color BASE_COLOR = new Color(0, 0, 16, 0.1);
	
	public UnitPanel() {
		super(new Point(0, 0));
	}

	@Override
	public void load() {
		
	}

	@Override
	public void show() {

	}

	@Override
	public void toRender() {
		base = Camera.getCameraPos().getNew(0, SCREEN_H - PANEL_H);

		Render.addUi(getPanelBase(), 1);
		Render.addUi(getStatPanel(), 1);
		Render.addUi(getSkillPanel(), 1);
	}

	private Shape[] getSkillPanel() {
		Shape skillSlots[] = new Shape[3];
		skillSlots[0] = SHAPES
				.newRectangle(base.getNew(
							BASE_MARGIN * 4 + STATPANEL_W,
							BASE_MARGIN * 4),
						SLOT_SCALE, SLOT_SCALE)
				.setColor(BASE_COLOR)
				.getShape();
		
		skillSlots[1] = SHAPES
				.newRectangle(base.getNew(
							BASE_MARGIN * 5 + STATPANEL_W + SLOT_SCALE,
							BASE_MARGIN * 4),
						SLOT_SCALE, SLOT_SCALE)
				.setColor(BASE_COLOR)
				.getShape();
		
		skillSlots[2] = SHAPES
				.newRectangle(base.getNew(
							BASE_MARGIN * 6 + STATPANEL_W + SLOT_SCALE * 2,
							BASE_MARGIN * 4),
						SLOT_SCALE, SLOT_SCALE)
				.setColor(BASE_COLOR)
				.getShape();
		return skillSlots;
	}

	private Shape[] getStatPanel() {
		Shape statPanel[] = new Shape[1];
		statPanel[0] = SHAPES
				.newRectangle(base.getNew(BASE_MARGIN * 2, BASE_MARGIN * 2),
						STATPANEL_W, PANEL_H - BASE_MARGIN * 5)
				.setColor(BASE_COLOR)
				.getShape();
		return statPanel;
	}

	private Shape[] getPanelBase() {
		Shape panelBase[] = new Shape[4];
		panelBase[0] = SHAPES
				.newRectangle(base,
						SCREEN_W, PANEL_H)
				.setColor(new Color(255 - BASE_COLOR.getB(), 255 - BASE_COLOR.getB(), 255))
				.getShape();
		
		panelBase[1] = SHAPES
				.newRectangle(base,
						SCREEN_W, PANEL_H)
				.setColor(BASE_COLOR)
				.getShape();
		
		panelBase[2] = SHAPES
				.newRectangle(base.getNew(0, BASE_MARGIN),
						SCREEN_W, PANEL_H - BASE_MARGIN)
				.setColor(BASE_COLOR)
				.getShape();
		
		panelBase[3] = SHAPES
				.newRectangle(base.getNew(0, PANEL_H - BASE_MARGIN),
						SCREEN_W, BASE_MARGIN)
				.setColor(new Color(BASE_COLOR, .2))
				.getShape();
		
		return panelBase;
	}

}
