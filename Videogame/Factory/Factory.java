// Factory.java      Autor: Juan Arturo Cruz Cardona
// Factory para crear las diferentes entidades del juego
package Videogame.Factory;

import Videogame.State.*;

public class Factory{
    private static class SingletonHolder{
        private static Factory instance = new Factory();
    }
    public static Factory getInstance(){
        return SingletonHolder.instance;
    }
    // Método para crear estados

    public State createState(String type) {
        switch(type){
            case "Play": return new PlayState();
            case "Pause": return new PauseState();
            case "GameOver": return new GameOverState();
            case "Menu": return new MenuState();
            default: return null;
        }
    }
}