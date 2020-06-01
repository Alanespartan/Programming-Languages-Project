/*Copyright (C) 2020 Juan Arturo Cruz Cardona
* Final Project: 2D video game using a finite state machine, design patterns and threads.
* This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
* as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with this program. If not, see https://www.gnu.org/licenses/.
_______________________________________________________________________________________________________________________________________ 
* Player.java
* Specify the attributes and behavior of the player in game
*/
package Videogame.Figures;

import Videogame.ImageLoader.ImageLoader;
import java.util.LinkedList;

public class Player extends MyGraphics{
    private LinkedList<Bullet> bullets = new LinkedList<Bullet>();
    
    public Player() {
        setWidth(50);
        setHeight(75);
        setX1(20);
        setY1(570);
        setHP(50);
        setIsDead(false);
        setImage(ImageLoader.getInstance().getImage("player"));
    }

    public static class SingletonHolder {
        private static Player instance = new Player();
    }

    public static Player getInstance() {
        return SingletonHolder.instance;
    }

    public void movePlayer(String movement){
        if(movement == "up"){
            if(getY1() > 6){
                setY1(getY1() - 6);
            }
        }else if(movement == "down"){
            if(getY2() < 641) {
                setY1(getY1() + 6);
            }
        }else if(movement== "right"){
            if(getX2() < 900){
                setX1(getX1() + 6);
            }
        }else if(movement == "left"){
            if(getX1() > 6){
                setX1(getX1() - 6);
            }
        }
    }

    public void shoot() {
        bullets.add(new Bullet(getX2(), getY1() + (getHeight()/2) ));
    }

    public LinkedList<Bullet> getBullets() {
        return bullets;
    }
}