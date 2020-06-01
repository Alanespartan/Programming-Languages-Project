/*Copyright (C) 2020 Juan Arturo Cruz Cardona
* Final Project: 2D video game using a finite state machine, design patterns and threads.
* This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
* as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with this program. If not, see https://www.gnu.org/licenses/.
_______________________________________________________________________________________________________________________________________ 
* GameOverState.java
* Defines behavior of the game over in game
*/
package Videogame.State;

import Videogame.ImageLoader.ImageLoader;
import java.awt.*;

public class GameOverState extends State{
    public GameOverState(){
        background = ImageLoader.getInstance().getImage("gameover");
    }
    @Override
    public void gameRender(Graphics g, int PanelWidth, int PanelHeight){
        g.drawImage(background,0,0, PanelWidth, PanelHeight ,null);
    }
    @Override
    public void gameUpdate() {}
    @Override
    public void pause() {}
    @Override
    public void play(){}
    @Override
    public void gameOver() {}
}
