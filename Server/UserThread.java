/*Copyright (C) 2020 Juan Arturo Cruz Cardona
* Final Project: 2D video game using a finite state machine, design patterns and threads.
* This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
* as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with this program. If not, see https://www.gnu.org/licenses/.
_______________________________________________________________________________________________________________________________________ 
* UserThread.java
* Process each connection in a separate thread to read 
* messages sent from the client (instances of the game) 
* and broadcasting messages to all other clients 
*/
package Server;

import java.io.*;
import java.net.*;
import Videogame.Figures.Boss;

public class UserThread extends Thread {
    private final Socket socket;
    private final Server server;
    
    private InputStream input;
    private PrintWriter writer;
    private BufferedReader reader;
    private OutputStream output;
    
    private String serverMessage, clientMessage;
    private String userName;

    private Boss mutualBoss;
 
    public UserThread(final Socket socket, final Server server, Boss mutualBoss) {
        this.socket = socket;
        this.server = server;
        this.mutualBoss = mutualBoss;
    }
 
    public void run() {
        try {
            System.out.println("UserThread Is Handling a New Client");
            input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
 
            output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
 
            printUsers();
 
            userName = reader.readLine();
            server.addUserName(userName);
 
            // Advice the other players that someone else has joined the game
            serverMessage = userName + " has joined the game";
            server.broadcast(serverMessage, this);
                
            while(true){
                clientMessage = reader.readLine();
                if(clientMessage.equals("playerdead")){
                    server.removeUser(userName, this);
                    serverMessage = userName + " has died against the boss";
                    server.broadcast(serverMessage, this);
                    break;
                }
                if(clientMessage.equals("shoot")){ // A player shot the boss
                    mutualBoss.setHP(mutualBoss.getHP() - 10);
                }
                if(mutualBoss.getHP() <= 0){
                    serverMessage = "bosskilled";
                    server.broadcastToAll(serverMessage);
                }
            }
            socket.close();
        } catch (final IOException ex) {
            System.out.println("Error in UserThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Sends a list of online users to the newly connected user
    public void printUsers() {
        if (server.hasUsers()) {
            writer.println("Connected players: " + server.getUserNames());
        } else {
            writer.println("No other players connected");
        }
    }
 
    // Sends messages to the client
    public void sendMessage(final String message) {
        writer.println(message);
    }
}