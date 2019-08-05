package game.core.screen.bottomPanel;

import game.core.screen.Panel;
import render2d.Camera;
import render2d.Render;
import render2d.elements.Point;
import root.HexBattleApp;

public class UnitPanel extends Panel{

	private final static UnitPanelLayout LAYOUT = new UnitPanelLayout();

	private final static int PANEL_H = 200;
	private final static int SCREEN_H = HexBattleApp.H;
	
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
		LAYOUT.toRender(getPanelBase(), 1);
	}
	
	public static Point getPanelBase() {
		return Camera.getCameraPos().getNew(0, SCREEN_H - PANEL_H);
	}
}
