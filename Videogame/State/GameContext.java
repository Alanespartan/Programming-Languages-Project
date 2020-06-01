// GameContext.java      Autor: Juan Arturo Cruz Cardona
// Contexto de la máquina de estados para controlarla
package Videogame.State;

import java.awt.*;
import Videogame.Factory.*;

public class GameContext{
    private State currentState;
    private State playState;
    private State pauseState;
    private State gameOverState;
    private State menuState;
    
    // Constructor
    public GameContext(){
        initComponents();
    }

    // Método para generar los componentes de la máquina de estados
    private void initComponents(){
        pauseState = Factory.getInstance().createState("Pause");
        playState = Factory.getInstance().createState("Play");
        gameOverState = Factory.getInstance().createState("GameOver");
        menuState = Factory.getInstance().createState("Menu");
        
        pauseState.setGameContext(this);
        playState.setGameContext(this);
        gameOverState.setGameContext(this);
        menuState.setGameContext(this);
        
        currentState = menuState;
    }

    // Métodos para controlar la animación
    public void gameUpdate(){
        currentState.gameUpdate();
    }
    public void gameRender(Graphics g, int PanelWidth, int PanelHeight){
        currentState.gameRender(g,PanelWidth,PanelHeight);
    }
    
    // Método para cambiar el estado actual
    public void setCurrentState(State newState){
        currentState = newState;
    }
    
    // Método para obtener los tipo de estado
    public State getPlayState(){ 
        return playState; 
    }
    public State getPauseState(){
        return pauseState; 
    }
    public State getMenuState(){
        return menuState;
    }
    public State getGameOverState() {
        return gameOverState;
    }
    public State getCurrentState(){
        return currentState;
    }
    
    // Métodos para pausar o reanudar la animación en el estado actual
    public void pause(){
        currentState.pause();
    }
    public void play(){
        currentState.play();
    }
    public void gameover(){
        currentState.gameOver();
    }
}
