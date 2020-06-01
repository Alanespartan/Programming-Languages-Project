// Observer.java      Autor: Juan Arturo Cruz Cardona
// Interfaz encargada de que todos los objetos que "observan" tengan el update
package Videogame.Observer;

public interface Observer {
	// Cuando un subject que estaba siendo "observando" le notifica que cambio a sus observers se realiza cierta acción
	void update(Subject auxSubject); 
}
