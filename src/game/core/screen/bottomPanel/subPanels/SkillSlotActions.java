package game.core.screen.bottomPanel.subPanels;

import render2d.elements.CursorActions;

public class SkillSlotActions extends CursorActions {
	
	private static SkillSlot selected;
	
	private SkillSlot owner;
	
	public SkillSlotActions(SkillSlot owner) {
		this.owner = owner;
	}

	@Override
	protected void actionRelease() {
		owner.unsetDown();
	}

	@Override
	protected void actionClick() {
		owner.setDown();
		if(selected != null)
			selected.unSelect();
		if(selected == null || !selected.equals(owner)) {
			selected = owner;
			owner.setSelect();
		} else {
			selected = null;
			owner.unSelect();
		}
	}

}
