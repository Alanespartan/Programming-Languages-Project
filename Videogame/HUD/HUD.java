/*Copyright (C) 2020 Juan Arturo Cruz Cardona
* Final Project: 2D video game using a finite state machine, design patterns and threads.
* This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
* as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with this program. If not, see https://www.gnu.org/licenses/.
_______________________________________________________________________________________________________________________________________ 
* HUD.java
* Render util information for the players
*/
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
         int hp = Player.getInstance().getHP() / 10;
         int distance = 0;
         for(int i = 0; i < hp; i++){
            g.drawImage(heart,x+70+distance,y+655, 30, 30 ,null);
            distance+=50;
         }
    }
}
