package com.clone;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	CloneGame game; //the game in witch we want to listen to the events
	//Clone player; //the object that does the things
	
	public KeyInput(CloneGame game){
		this.game = game;
	}
	
	//TODO: put keyX fuctions in the CloneGame class as well, where the 
	//things will happen and where there probably is an instant of the
	//player already.
	
	//TODO: also add keylistener before creating the player, when initializing
	// addKeyListener(new KeyInput(this);
	
	public void keyPressed(KeyEvent e){
		//game.keyPressed(e);
		
		//TODO: the following is to be moved to the keyPressed function
		//class in the CloneGame class!
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_A){
			
		}else if(key == KeyEvent.VK_D){
			
		}else if(key == KeyEvent.VK_W){
			
		}else if(key == KeyEvent.VK_S){
			
		}
		
	}
	public void keyReleased(KeyEvent e){
		//game.keyReleased(e);
		
	}
	


}
