// Server.java      Autor: Juan Arturo Cruz Cardona
// Responsible of starting the server and listening on a specific port
// also here is created the instance of the global enemy that all players face
package Server;

import java.io.*;
import java.net.*;
import java.util.*;

import Videogame.Figures.Boss;

public class Server{
    private int port;
    private Set<String> userNames = new HashSet<>();
    private Set<UserThread> userThreads = new HashSet<>();
    private Boss mutualBoss;

    public Server(int port) {
        this.port = port;
        // When creating the boss in each instance of the clients set the same initial hp
        this.mutualBoss = new Boss();
    }

    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port: " + port);

            while (true) {
                // When there is other client connecting to the same socket
                Socket socket = serverSocket.accept();

                // Give a UserThread to each client to handle the reading and writing
                UserThread newPlayer = new UserThread(socket, this, this.mutualBoss);
                this.userThreads.add(newPlayer);
                newPlayer.start();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 4000;

        Server server = new Server(port);
        server.execute();
    }

    // Deliver a message to all the clients through broadcasting (except the one who sent it)
    public void broadcast(String message, UserThread excludeUser) {
        for (UserThread aUser : userThreads) {
            if (aUser != excludeUser) {
                aUser.sendMessage(message);
            }
        }
    }

    public void broadcastToAll(String message) {
        for (UserThread aUser : userThreads) {
            aUser.sendMessage(message);
        }
    }

    // Stores username of the newly connected player
    public void addUserName(String userName) {
        this.userNames.add(userName);
    }

    // When a player is disconneted, removes the associated username and UserThread
    public void removeUser(String userName, UserThread aUser) {
        boolean removed = userNames.remove(userName);
        if (removed) {
            this.userThreads.remove(aUser);
        }
    }

    public Set<String> getUserNames() {
        return this.userNames;
    }

    // Returns true if there are other users connected and playing (not count the
    // currently connected user)
    public boolean hasUsers() {
        return !this.userNames.isEmpty();
    }
}