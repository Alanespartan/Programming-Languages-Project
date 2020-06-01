/*Copyright (C) 2020 Juan Arturo Cruz Cardona
* Final Project: 2D video game using a finite state machine, design patterns and threads.
* This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
* as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with this program. If not, see https://www.gnu.org/licenses/.
_______________________________________________________________________________________________________________________________________ 
* RunGame.java
* Set up entities for the game
*/
package Videogame.Main;

import Videogame.Management.GameManager;
import Videogame.ImageLoader.ImageLoader;
//import Videogame.Sound.MusicPlayer;
import Videogame.State.GameContext;
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class RunGame {
    public static void main(String[] args) {
        JFrame GameFrame = new JFrame("Proyecto Final de la clase Lenguajes de Programación");
        
        // Load images
        ImageLoader.getInstance().load();
        
        // Load and start music
        //MusicPlayer.getInstance().loadClip("../Resources/Music/bossmusic.wav");
        //MusicPlayer.getInstance().playMusic();
        
        // GameState Machine
        GameContext gameContext = new GameContext();        
        
        // GamePanel (where the game runs)
        GamePanel gamePanel = new GamePanel(gameContext);

        // GameManager runs as client
        GameManager gameManager = new GameManager(gamePanel, gameContext, "localhost", 4000);
        gameManager.execute();

        GameFrame.getContentPane().add(gamePanel, BorderLayout.CENTER);
        GameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameFrame.pack();
        GameFrame.setResizable(false);
        GameFrame.setVisible(true);
    }
}