package game.core.screen.bottomPanel;

import render2d.elements.Action;
import render2d.elements.CursorActions;

public class SkillSlotClick extends CursorActions {
	private static SkillSlot selected;
	
	public SkillSlotClick(SkillSlot owner) {
		setClick(
			new Action() {
				@Override
				public void run() {
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
		);

		setRelease(
			new Action() {
				@Override
				public void run() {
					owner.unsetDown();
				}
			}
		);
	}

}
