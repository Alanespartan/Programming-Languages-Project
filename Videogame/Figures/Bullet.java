/*Copyright (C) 2020 Juan Arturo Cruz Cardona
* Final Project: 2D video game using a finite state machine, design patterns and threads.
* This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
* as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with this program. If not, see https://www.gnu.org/licenses/.
_______________________________________________________________________________________________________________________________________ 
* Bullet.java
* Specify the attributes and behavior of the bullets in game
*/
package Videogame.Figures;

import Videogame.ImageLoader.ImageLoader;

public class Bullet extends MyGraphics{
    public Bullet(int x1, int y1){
        setWidth(20);
        setHeight(20);
        setX1(x1);
        setY1(y1);
        setImage(ImageLoader.getInstance().getImage("bullet"));
    }

    public void update(){
        this.setX1(getX1() + 1);
    }
}