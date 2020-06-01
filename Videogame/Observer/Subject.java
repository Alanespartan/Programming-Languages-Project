//  Subject.java      Autor: Juan Arturo Cruz Cardona
//  Interfaz para implementar a los subjects (quienes definiran los cambios)
package Videogame.Observer;

public interface Subject{
    void subscribe(Observer observer); // Añade un observador a la lista
    void notifyObservers(); // Cuando hay un cambio le notifica a todos los que lo están viendo
}
