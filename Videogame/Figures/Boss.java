/*Copyright (C) 2020 Juan Arturo Cruz Cardona
* Final Project: 2D video game using a finite state machine, design patterns and threads.
* This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
* as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with this program. If not, see https://www.gnu.org/licenses/.
_______________________________________________________________________________________________________________________________________ 
* Boss.java
* Specify the attributes and behavior of the boss in game
*/
package Videogame.Figures;

import Videogame.ImageLoader.ImageLoader;
import java.util.LinkedList;
import java.util.Random;

public class Boss extends MyGraphics{
    private LinkedList<Thunder> thunders = new LinkedList<Thunder>();
    private Random r = new Random();
    private Boolean paused = false;

    public Boss() {
        setWidth(500);
        setHeight(610);
        setX1(950);
        setY1(15);
        setHP(100);
        setIsDead(false);
        setImage(ImageLoader.getInstance().getImage("boss"));
    }

    public synchronized void shoot() {
        int low = 10;
        int high = 640;
        int result = r.nextInt(high - low) + low;
        thunders.add(new Thunder(result));
    }

    public synchronized LinkedList<Thunder> getThunders() {
        return thunders;
    }

    public void run() {
        while(getHP() > 0) {
            if(!paused){
                try {
                    Thread.sleep(1000);
                    shoot();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("Boss Thread is Paused");
            }
        }
    }

    public void setPaused(Boolean aux){
        paused = aux;
    }
}