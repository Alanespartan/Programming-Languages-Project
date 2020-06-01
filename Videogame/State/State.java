/*Copyright (C) 2020 Juan Arturo Cruz Cardona
* Final Project: 2D video game using a finite state machine, design patterns and threads.
* This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
* as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with this program. If not, see https://www.gnu.org/licenses/.
_______________________________________________________________________________________________________________________________________ 
* State.java
* Root class for every states
*/
package Videogame.State;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class State{
    protected BufferedImage background;

    GameContext gameContext;
    
    public abstract void gameUpdate();
    public abstract void gameRender(Graphics g, int PanelWidth, int PanelHeight);
    public abstract void pause();
    public abstract void play();
    public abstract void gameOver();
    public void setGameContext(GameContext gameContext){
        this.gameContext = gameContext;
    }
}