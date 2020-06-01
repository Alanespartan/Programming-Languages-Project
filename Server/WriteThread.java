/*Copyright (C) 2020 Juan Arturo Cruz Cardona
* Final Project: 2D video game using a finite state machine, design patterns and threads.
* This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
* as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with this program. If not, see https://www.gnu.org/licenses/.
_______________________________________________________________________________________________________________________________________ 
* WriteThread.java
* Reponsible for sending messages to the server
* Run an infinite loop until player dies or left the game
*/
package Server;

import java.io.*;
import java.net.*;
import java.text.DecimalFormat;

import Videogame.Management.GameManager;

public class WriteThread extends Thread {
    private PrintWriter writer;
    private Socket socket;
    private GameManager client;
    private DecimalFormat df = new DecimalFormat("#.#");
    private String playerName = "RandomName" + df.format(Math.random());
    private Boolean shootFlag = false;

    public void userShoot(){
        shootFlag = true;
    }

    public WriteThread(Socket socket, GameManager client) {
        this.socket = socket;
        this.client = client;

        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {
        System.out.println("WriteThread Started Writting in Buffer");
        String text = "";
        // Writes  the name of the player to read it in UserThread and then pass it to the server
        writer.println(playerName);
        do {
            if(client.getIsPlayerDead()) { // Player left or get killed
                text = "playerdead";
                writer.println(text);
            }if(shootFlag) { // Player shot the boss
                text = "shoot";
                writer.println(text);
                shootFlag = false;
            }
            else { // Thread sleep every time there is nothing to communicate
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (!text.equals("playerdead"));
        
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("Error writing to server: " + ex.getMessage());
        }

        client.endClient();
    }

    public void closeBuffer(){
        System.out.println("WriteThread Stop Running");
        writer.close();
    }
}