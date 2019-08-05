package game.core.screen.bottomPanel;

import java.util.Arrays;
import java.util.List;

import game.core.HexTileActions;
import game.core.screen.PanelShapeLayout;
import render2d.Color;
import render2d.Render;
import render2d.drawable.RectangleClick;
import render2d.drawable.Shape;
import render2d.drawable.ShapeBuilder;
import render2d.elements.CursorActions;
import render2d.elements.Point;
import root.HexBattleApp;

public class UnitPanelLayout extends PanelShapeLayout{
	private final static ShapeBuilder SHAPES = new ShapeBuilder();

	private final static int PANEL_H = 200;
	private final static int SCREEN_W = HexBattleApp.W;
	private final static int STATPANEL_W = 180;
	private final static int SLOT_SCALE = 80;
	
	public final static Color BASE_COLOR = new Color(0, 0, 16, 0.1);
	public final static int BASE_MARGIN = 5;
	
	private Point base;
	private SkillSlot movement;
	private SkillSlot weapon1;
	private SkillSlot weapon2;
	
	public UnitPanelLayout() {
		setSkillSlots();
	}

	@Override
	public void toRender(Point base, int layer) {
		this.base = base;
		Render.addUi(getPanelBase(), layer);
		Render.addUi(getStatPanel(), layer);
		movement.toRender(base, getSelectedUnitSkillTex(0), layer);
		weapon1.toRender(base, getSelectedUnitSkillTex(1), layer);
		weapon2.toRender(base, getSelectedUnitSkillTex(2), layer);
	}

	private String getSelectedUnitSkillTex(int slot) {
		if(HexTileActions.getSelectedUnit() == null)
			return "";
		return HexTileActions.getSelectedUnit()
				.getSkill(slot)
				.getTexName();
	}
	
	private void setSkillSlots() {
		this.movement = new SkillSlot(
				BASE_COLOR,
				new Point(
						BASE_MARGIN * 4 + STATPANEL_W,
						BASE_MARGIN * 4),
				SLOT_SCALE, SLOT_SCALE
				);
		
		this.weapon1 = new SkillSlot(
				BASE_COLOR,
				new Point(
						BASE_MARGIN * 5 + STATPANEL_W + SLOT_SCALE,
						BASE_MARGIN * 4),
				SLOT_SCALE, SLOT_SCALE
				);
		
		this.weapon2 = new SkillSlot(
				BASE_COLOR,
				new Point(
						BASE_MARGIN * 6 + STATPANEL_W + SLOT_SCALE * 2,
						BASE_MARGIN * 4),
				SLOT_SCALE, SLOT_SCALE
				);
	}
	
	private List<Shape> getStatPanel() {
		return Arrays.asList(
			SHAPES
			.newRectangle(base.getNew(BASE_MARGIN * 2, BASE_MARGIN * 2),
					STATPANEL_W, PANEL_H - BASE_MARGIN * 5)
			.setColor(BASE_COLOR)
			.getShape()
		);		
	}

	private List<Shape> getPanelBase() {
		return Arrays.asList(
			SHAPES
			.newRectangle(base,
					SCREEN_W, PANEL_H)
			.setColor(new Color(255 - BASE_COLOR.getB(), 255 - BASE_COLOR.getB(), 255))
			.getShape(),
			
			SHAPES
			.newRectangle(base,
					SCREEN_W, PANEL_H)
			.setColor(BASE_COLOR)
			.getShape(),
			
			SHAPES
			.newRectangle(base.getNew(0, BASE_MARGIN),
					SCREEN_W, PANEL_H - BASE_MARGIN)
			.setColor(BASE_COLOR)
			.getShape(),
			
			SHAPES
			.newRectangle(base.getNew(0, PANEL_H - BASE_MARGIN),
					SCREEN_W, BASE_MARGIN)
			.setColor(new Color(BASE_COLOR, .2))
			.getShape()
		);
	}
}
