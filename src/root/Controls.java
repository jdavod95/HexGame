package root;

import org.lwjgl.input.Keyboard;

import render2d.Camera;

public class Controls {
	
	public static void navigate(){
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			Camera.step(-15, 0);
		else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			Camera.step(15, 0);
		
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			Camera.step(0, 15);
		else if(Keyboard.isKeyDown(Keyboard.KEY_UP))
			Camera.step(0, -15);
		
		if (Keyboard.isKeyDown(Keyboard.KEY_R))
			Camera.rePosition(0,0);
		
	}

}
