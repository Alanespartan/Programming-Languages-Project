//  RunGame.java      Autor: Juan Arturo Cruz Cardona
package Videogame.Main;

import Videogame.Managment.GameManager;
import Videogame.ImageLoader.ImageLoader;
import Videogame.Sound.MusicPlayer;
import Videogame.State.GameContext;
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class RunGame {
    public static void main(String[] args) {
        JFrame GameFrame = new JFrame("Proyecto Final de la clase Lenguajes de Programación");
        
        // Load images
        ImageLoader.getInstance().load();
        
        // Load and start music
        MusicPlayer.getInstance().loadClip("../Resources/Music/bossmusic.wav");
        MusicPlayer.getInstance().playMusic();
        
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