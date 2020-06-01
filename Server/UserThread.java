// UserThread.java      Autor: Juan Arturo Cruz Cardona
// Responsible for reading messages sent from the client (instances of the game) 
// and broadcasting messages to all other clients
// Also, since each connection is processed in a separate thread, 
// the server is able to handle multiple clients at the same time
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