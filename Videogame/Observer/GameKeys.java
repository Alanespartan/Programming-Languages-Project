//  GameKeys.java      Autor: Juan Arturo Cruz Cardona
//  Clase que sirve para guardar detectar las teclas presionadas

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

	// Añadir observadores
	@Override
	public void subscribe(Observer observer){
		obs.add(observer);
	}
	// Notificar a sus observadores
	@Override
	public void notifyObservers(){
		for (Observer observer : obs) {
			observer.update(this);
		}
	}
	
	public String getKeyPressed(){
		return state;
	}
	
	// Detectar tecla presionada
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
