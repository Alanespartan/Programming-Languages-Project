//  HUD.java      Autor: Juan Arturo Cruz Cardona
//  Clase que se usa para mostrar los puntajes y más información al jugador
package Videogame.HUD;

import java.awt.*;
import Videogame.Figures.Player;
import Videogame.ImageLoader.ImageLoader;
import java.awt.image.BufferedImage;

public class HUD{
    private int x, y;
    private String serverMessage = "";
    private BufferedImage heart = ImageLoader.getInstance().getImage("heart");

    private HUD(){
        this.x = 0;
        this.y = 0;
    }

    private static class SingletonHolder{
        private static HUD instance = new HUD();
    }

    public static HUD getInstance(){
        return SingletonHolder.instance;
    }
    
    public void render(Graphics g) {
        renderServerMessage(g);
        renderHearts(g);
    }

    public void renderServerMessage(Graphics g){
        g.setColor(Color.black);
        g.fillRect(x+350, y+640, 800, 500);
        g.setColor(Color.white);
        g.drawString(serverMessage , x+375, y+655);
    }

    public void setServerMessage(String serverMessage){
        this.serverMessage = serverMessage;
    }

    public void renderHearts(Graphics g){
         /* RENDER HP del jugador */
         int hp = Player.getInstance().getHP() / 10;
         int distance = 0;
         for(int i = 0; i < hp; i++){
            g.drawImage(heart,x+70+distance,y+655, 30, 30 ,null);
            distance+=50;
         }
    }
}
