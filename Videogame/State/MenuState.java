package Videogame.State;

import Videogame.ImageLoader.ImageLoader;
import java.awt.*;

public class MenuState extends State{
    public MenuState(){
        background = ImageLoader.getInstance().getImage("menu");
    }
    @Override
    public void gameRender(Graphics g, int PanelWidth, int PanelHeight) {
        g.drawImage(background,0,0, PanelWidth, PanelHeight ,null);
    }
    
    @Override
    public void gameUpdate(){
        // Do nothing
    }
    @Override
    public void pause() {
        // Do nothing
    }
    @Override
    public void play() {
        gameContext.setCurrentState(gameContext.getPlayState());
    }
    @Override
    public void gameOver() {
        // Do nothing
    }
}
