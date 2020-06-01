// ReadThread.java      Autor: Juan Arturo Cruz Cardona
// Is responsible for reading input from the server and telling the GameManager 
// to execute some actions depending on the message.
package Server;

import java.io.*;
import java.net.*;

import Videogame.Managment.GameManager;

public class ReadThread extends Thread {
    private BufferedReader reader;
    private Socket socket;
    private GameManager client;

    public ReadThread(Socket socket, GameManager client) {
        this.socket = socket;
        this.client = client;

        try {
            InputStream input = this.socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {
        System.out.println("ReadThread Started Reading the Buffer");
        while (true) {
            try {
                String response = reader.readLine(); // Read message from the server after broadcasting it
                if (response.equals("bosskilled")) { // If the boss has been killed, finish game
                    client.finishGame();
                } else {
                    client.setServerMessageInHUD(response); // Prints the server message in the HUD of client
                }
            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }

    public void closeBuffer() {
        try {
            System.out.println("ReadThread Stop Running");
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}