/*Copyright (C) 2020 Juan Arturo Cruz Cardona
* Final Project: 2D video game using a finite state machine, design patterns and threads.
* This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
* as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with this program. If not, see https://www.gnu.org/licenses/.
_______________________________________________________________________________________________________________________________________ 
* GameManager.java
* GameKeys.java
* Detects which keys have been pressed
*/
package Videogame.Observer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameKeys implements Subject, KeyListener {
	private ArrayList<Observer> obs = new ArrayList<>();
	private String state;

	public static class SingletonHolder{
		private static GameKeys instance = new GameKeys();
	}
	public static GameKeys getInstance() {
		return SingletonHolder.instance;
	}

	@Override
	public void subscribe(Observer observer){
		obs.add(observer);
	}

	@Override
	public void notifyObservers(){
		for (Observer observer : obs) {
			observer.update(this);
		}
	}
	
	public String getKeyPressed(){
		return state;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		String keyPressed = KeyEvent.getKeyText(e.getKeyCode());
		switch(keyPressed){
			case "D": state = "right"; break;
			case "A": state = "left"; break;
			case "W": state = "up"; break;
			case "S": state = "down"; break;
			case "P": state = "pause"; break;
			case "B": state = "dead"; break;
			case "Intro": state = "enter"; break;
			case "Escape": state = "escape"; break;
			case "Espacio": state = "shoot"; break;
			default: state = "none"; break;
		}
		notifyObservers();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e){}
}
