/*Copyright (C) 2020 Juan Arturo Cruz Cardona
* Final Project: 2D video game using a finite state machine, design patterns and threads.
* This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
* as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with this program. If not, see https://www.gnu.org/licenses/.
_______________________________________________________________________________________________________________________________________ 
* GameManager.java
* Starts the client program and connects to a server and
* make certain modifications to the gameplay based in server messages
*/
package Videogame.Managment;

import Videogame.Observer.*;
import Videogame.Sound.MusicPlayer;
import Videogame.State.GameContext;
import Videogame.Figures.*;
import Videogame.HUD.HUD;
import Videogame.Main.GamePanel;

import java.net.*;
import java.io.*;
import Server.ReadThread;
import Server.WriteThread;

public class GameManager implements Observer {
    private GameContext gameContext;
    private GamePanel gamePanel;

    private String hostname;
    private int port;

    private ReadThread readThread;
    private WriteThread writeThread;
    
    public GameManager(GamePanel gamePanel, GameContext gameContext, String hostname, int port) {
        // Info to stablish connection with the server
        this.hostname = hostname;
        this.port = port;

        // Knew when a key has been pressed
        GameKeys.getInstance().subscribe(this);
        
        this.gamePanel = gamePanel;
        this.gameContext = gameContext;
    }

    @Override
    public void update(Subject subject) {
        if (subject == GameKeys.getInstance()) {
            // Menu interactions
            if (GameKeys.getInstance().getKeyPressed() == "escape") { // Exit game
                if (gameContext.getCurrentState() == gameContext.getMenuState()
                        || gameContext.getCurrentState() == gameContext.getGameOverState()
                        || gameContext.getCurrentState() == gameContext.getPauseState()) {
                    Player.getInstance().setIsDead(true);
                }
            } else if (GameKeys.getInstance().getKeyPressed() == "enter") { // Start or resume game
                if (gameContext.getCurrentState() == gameContext.getMenuState()) {
                    gameContext.play();
                } else if (gameContext.getCurrentState() == gameContext.getPauseState()) {
                    gameContext.play();
                    MusicPlayer.getInstance().playMusic();
                }
            } else if (GameKeys.getInstance().getKeyPressed() == "pause") { // Pause game
                if (gameContext.getCurrentState() == gameContext.getPlayState()) {
                    gameContext.pause();
                    MusicPlayer.getInstance().pauseMusic();
                }
            }

            // Player movements
            else if (GameKeys.getInstance().getKeyPressed() == "right") { // Player moves right
                if (gameContext.getCurrentState() == gameContext.getPlayState()) {
                    Player.getInstance().movePlayer("right");
                }
            } else if (GameKeys.getInstance().getKeyPressed() == "left") {
                if (gameContext.getCurrentState() == gameContext.getPlayState()) {
                    Player.getInstance().movePlayer("left");
                }
            } else if (GameKeys.getInstance().getKeyPressed() == "up") {
                if (gameContext.getCurrentState() == gameContext.getPlayState()) {
                    Player.getInstance().movePlayer("up");
                }
            } else if (GameKeys.getInstance().getKeyPressed() == "down") {
                if (gameContext.getCurrentState() == gameContext.getPlayState()) {
                    Player.getInstance().movePlayer("down");
                }
            } else if (GameKeys.getInstance().getKeyPressed() == "shoot") {
                if (gameContext.getCurrentState() == gameContext.getPlayState()) {
                    Player.getInstance().shoot();
                    this.writeThread.userShoot();
                }
            }

            // Auxiliar keys
            else if (GameKeys.getInstance().getKeyPressed() == "dead") {
                Player.getInstance().setIsDead(true);
            }
        }
    }

    public void execute() {
        try {
            // Link to the server
            Socket socket = new Socket(hostname, port);
            readThread = new ReadThread(socket, this);
            writeThread = new WriteThread(socket, this);

            // Start writing and reading in the buffer
            readThread.start();
            writeThread.start();
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        }
    }

    public void setServerMessageInHUD(String serverMessage) {
        HUD.getInstance().setServerMessage(serverMessage);
    }

    public void finishGame() {
        gameContext.gameover();
    }

    public Boolean getIsPlayerDead() {
        return Player.getInstance().getIsDead();
    }

    public void endClient() {
        readThread.closeBuffer();
        writeThread.closeBuffer();
        gamePanel.stopAnimation();
        System.exit(0);
    }
}