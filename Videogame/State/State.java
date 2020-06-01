// State.java      Autor: Juan Arturo Cruz Cardona
// Interfaz a implementar en los diferentes estados
package Videogame.State;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class State{
    protected BufferedImage background;

    GameContext gameContext;
    
    public abstract void gameUpdate();
    public abstract void gameRender(Graphics g, int PanelWidth, int PanelHeight);
    public abstract void pause();
    public abstract void play();
    public abstract void gameOver();
    public void setGameContext(GameContext gameContext){
        this.gameContext = gameContext;
    }
}