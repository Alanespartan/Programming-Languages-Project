/*Copyright (C) 2020 Juan Arturo Cruz Cardona
* Final Project: 2D video game using a finite state machine, design patterns and threads.
* This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
* as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with this program. If not, see https://www.gnu.org/licenses/.
_______________________________________________________________________________________________________________________________________ 
* ReadThread.java
* Is responsible for reading input from the server and telling the GameManager 
* to execute some actions depending on the message.
*/
package Server;

import java.io.*;
import java.net.*;

import Videogame.Management.GameManager;

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