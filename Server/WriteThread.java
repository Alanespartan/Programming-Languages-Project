//  WriteThread.java      Autor: Juan Arturo Cruz Cardona
//  Es responsable de leer el estado del jugador y mandarlo al server
// Corre en un loop infinito hasta que el jugador deja de jugar
package Server;

import java.io.*;
import java.net.*;
import java.text.DecimalFormat;

import Videogame.Managment.GameManager;

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