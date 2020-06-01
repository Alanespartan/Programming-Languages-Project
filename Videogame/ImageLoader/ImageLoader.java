/*Copyright (C) 2020 Juan Arturo Cruz Cardona
* Final Project: 2D video game using a finite state machine, design patterns and threads.
* This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
* as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with this program. If not, see https://www.gnu.org/licenses/.
_______________________________________________________________________________________________________________________________________ 
* ImageLoader.java
* Load and save different images to use later in game
*/
package Videogame.ImageLoader;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;

public class ImageLoader {
    private Hashtable <String, BufferedImage> images;
    private Hashtable <String, Image> gifs;

    private ImageLoader(){
        this.images = new Hashtable<String, BufferedImage>();
        this.gifs = new Hashtable<String, Image>();
    }

    public static class SingletonHolder{
        private static ImageLoader instance = new ImageLoader();
    }

    public static ImageLoader getInstance(){
        return SingletonHolder.instance;
    }
    
    // Load images and gifs into their hashtables
    public void load(){
        try{
            // Menu backgrounds
            images.put("menu", ImageIO.read(ImageLoader.class.getResourceAsStream("../Resources/Backgrounds/menu.png")));
            images.put("pause", ImageIO.read(ImageLoader.class.getResourceAsStream("../Resources/Backgrounds/pause.png")));
            images.put("gameover", ImageIO.read(ImageLoader.class.getResourceAsStream("../Resources/Backgrounds/gameover.png")));
            
            // In game backgrounds          
            images.put("fondo", ImageIO.read(ImageLoader.class.getResourceAsStream("../Resources/Backgrounds/fondo.png")));

            // Player
            images.put("player", ImageIO.read(ImageLoader.class.getResourceAsStream("../Resources/Images/player.png")));
            images.put("bullet", ImageIO.read(ImageLoader.class.getResourceAsStream("../Resources/Images/bullet.png")));
            images.put("heart", ImageIO.read(ImageLoader.class.getResourceAsStream("../Resources/Images/heart.png")));
            gifs.put("playerIdle", new ImageIcon("../Resources/Gifs/Player/Player.gif").getImage());
            
            // Boss
            images.put("boss", ImageIO.read(ImageLoader.class.getResourceAsStream("../Resources/Images/boss.png")));
            images.put("thunder", ImageIO.read(ImageLoader.class.getResourceAsStream("../Resources/Images/thunder.png")));
            gifs.put("BossIdle", new ImageIcon("../Resources/Gifs/Boss/Idle.gif").getImage());
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public BufferedImage getImage(String key){
        return images.get(key);
    }

    public Image getGif(String key){
		return gifs.get(key);
	}
}