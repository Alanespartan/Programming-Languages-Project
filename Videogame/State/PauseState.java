//  PauseState.java     Autor: Juan Arturo Cruz Cardona
//  Clase que define el comportamiento del estado Pause
package Videogame.State;

import Videogame.ImageLoader.ImageLoader;
import java.awt.Graphics;

public class PauseState extends State{
    public PauseState(){
        background = ImageLoader.getInstance().getImage("pause");
    }
    // Métodos para controlar la animación
    @Override
    public void gameUpdate(){

    }
    @Override
    public void gameRender(Graphics g, int PanelWidth, int PanelHeight){
        g.drawImage(background,0,0, PanelWidth, PanelHeight ,null);
    }
    @Override
    public void play() {
        gameContext.setCurrentState(gameContext.getPlayState());
    }

    /* No hacen nada*/
    @Override
    public void pause() {
        // Do nothing
    }
    @Override
    public void gameOver() {
        // If a player pause the game but the other players kill the boss
        gameContext.setCurrentState(gameContext.getGameOverState());
    }
}
