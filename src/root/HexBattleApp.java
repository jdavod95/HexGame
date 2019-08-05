package root;
import static org.lwjgl.opengl.GL13.GL_MULTISAMPLE;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

import java.io.File;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import game.core.screen.CombatScreen;
import game.core.screen.Panel;
import game.match.Player;
import render2d.Camera;
import render2d.Color;
import render2d.Render;
import render2d.Texturing;
import render2d.drawable.Shape;
import render2d.drawable.ShapeBuilder;
import render2d.elements.Cursor;
import render2d.write.Label;

public class HexBattleApp {
	
	public final static int W = 1024;
	public final static int H = 768;
	static int ticks = 0;
	static int sec = 0;
	
	private static Panel screen;
	
	public static void main(String[] args){
		initDisplay();
		gameLoop();
        Display.destroy();
        System.exit(0);
	}

	private static void gameLoop() {
		while (!Display.isCloseRequested()) {
		    Controls.navigate();
		    Cursor.check();
		    Camera.create();
		    screen.show();
			Render.addBgr(getBackground(),0);
		    Render.drawFrame();	
		    tick();
		}
	}
	// Observer?
	public static void preLoad(){ 
		Texturing.loadAll();
		Label.loadSymbols();
		screen = new CombatScreen(
				Player.REAL,
				Player.REALTWO);
		screen.load();
	}
	
	public static int getTicks(){
		return ticks;
	}
	
	private static void tick() {
		ticks ++;
		if(ticks == 60){
			ticks = 0;
			sec++;
		}
	}

	private static void initDisplay() {
		System.setProperty(
				"org.lwjgl.librarypath", 
				new File("lib/natives").getAbsolutePath()
				);
			
		try{
    	  	Display.setDisplayMode(new DisplayMode(W, H));
            Display.setTitle("HexBattle");
            Display.create();
            
		} catch (Exception e) {
            System.err.println("Display wasn't initialized correctly.");
            System.exit(1);
        }
		
		preLoad();
		
		glEnable(GL_MULTISAMPLE);  
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND); 
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glDisable(GL_LIGHTING);

	}
	
	private static Shape getBackground() {
		ShapeBuilder shapes = new ShapeBuilder();
		shapes.newRectangle(Camera.getCameraPos(), W, H);
		shapes.setColor(Color.WHITE);
		return shapes.getShape();
	}
}
