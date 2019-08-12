package game.core.screen.bottomPanel;

import render2d.elements.CursorActions;

public class SkillSlotClick extends CursorActions {
	private static SkillSlot selected;
	private SkillSlot owner;
	
	public SkillSlotClick(SkillSlot owner) {
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
