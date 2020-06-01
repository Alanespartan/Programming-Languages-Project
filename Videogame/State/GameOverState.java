package Videogame.State;

import Videogame.ImageLoader.ImageLoader;
import java.awt.*;

public class GameOverState extends State{
    public GameOverState(){
        background = ImageLoader.getInstance().getImage("gameover");
    }
    @Override
    public void gameRender(Graphics g, int PanelWidth, int PanelHeight){
        g.drawImage(background,0,0, PanelWidth, PanelHeight ,null);
    }

    /* No hacen nada */
    @Override
    public void gameUpdate() {
        // Do nothing
    }
    @Override
    public void pause() {
        // Do nothing
    }
    @Override
    public void play(){
        // Do nothing
    }
    @Override
    public void gameOver() {
        // Do nothing
    }
}
