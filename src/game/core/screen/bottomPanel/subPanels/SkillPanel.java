package game.core.screen.bottomPanel.subPanels;

import game.core.HexTileActions;
import game.core.screen.Panel;
import game.core.screen.bottomPanel.BottomPanel;
import render2d.Color;
import render2d.elements.Point;

public class SkillPanel extends Panel {

	public final static int LAYER = BottomPanel.LAYER + 1;
	public final static int BASE_MARGIN = BottomPanel.BASE_MARGIN;
	public final static Color BASE_COLOR = BottomPanel.BASE_COLOR;

	private SkillSlot movement;
	private SkillSlot weapon1;
	private SkillSlot weapon2;
	
	private int slotScale;
	
	public SkillPanel(Point basePos, int scale) {
		super(basePos, scale, scale);
		this.slotScale = scale;
		initSkillSlots();
	}

	@Override
	public void toRender() {
		updateSkillSlots();
		movement.toRender();
		weapon1.toRender();
		weapon2.toRender();
	}
	
	private void updateSkillSlots() {
		movement.setTexName(getSelectedUnitSkillTex(0));
		weapon1.setTexName(getSelectedUnitSkillTex(1));
		weapon2.setTexName(getSelectedUnitSkillTex(2));
	}

	private String getSelectedUnitSkillTex(int slot) {
		String texName = "";
		if(HexTileActions.getSelectedUnit() != null)
			texName = HexTileActions.getSelectedUnit()
				.getSkill(slot)
				.getTexName();
		return texName;
	}
	
	private void initSkillSlots() {
		this.movement = new SkillSlot(
			basePos.getNew(),
			slotScale
		);
		
		this.weapon1 = new SkillSlot(
			basePos.getNew(BASE_MARGIN + slotScale, 0),
			slotScale
		);
		
		this.weapon2 = new SkillSlot(
			basePos.getNew(BASE_MARGIN * 2 + slotScale * 2, 0),
			slotScale
		);
	}
}
