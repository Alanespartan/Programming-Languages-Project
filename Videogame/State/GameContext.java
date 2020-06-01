/*Copyright (C) 2020 Juan Arturo Cruz Cardona
* Final Project: 2D video game using a finite state machine, design patterns and threads.
* This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
* as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with this program. If not, see https://www.gnu.org/licenses/.
_______________________________________________________________________________________________________________________________________ 
* GameContext.java
* Define finite state machine
*/
package Videogame.State;

import java.awt.*;
import Videogame.Factory.*;

public class GameContext{
    private State currentState;
    private State playState;
    private State pauseState;
    private State gameOverState;
    private State menuState;
    
    public GameContext(){
        initComponents();
    }

    private void initComponents(){
        // Craate states
        pauseState = Factory.getInstance().createState("Pause");
        playState = Factory.getInstance().createState("Play");
        gameOverState = Factory.getInstance().createState("GameOver");
        menuState = Factory.getInstance().createState("Menu");
        // Set context
        pauseState.setGameContext(this);
        playState.setGameContext(this);
        gameOverState.setGameContext(this);
        menuState.setGameContext(this);
        // Set inital state
        currentState = menuState;
    }

    // Control animation based in the state
    public void gameUpdate(){
        currentState.gameUpdate();
    }
    public void gameRender(Graphics g, int PanelWidth, int PanelHeight){
        currentState.gameRender(g,PanelWidth,PanelHeight);
    }
    
    // Setters
    public void setCurrentState(State newState){
        currentState = newState;
    }
    
    // Getters
    public State getPlayState(){ 
        return playState; 
    }
    public State getPauseState(){
        return pauseState; 
    }
    public State getMenuState(){
        return menuState;
    }
    public State getGameOverState() {
        return gameOverState;
    }
    public State getCurrentState(){
        return currentState;
    }
    
    // Speacial events that can occur in every state
    public void pause(){
        currentState.pause();
    }
    public void play(){
        currentState.play();
    }
    public void gameover(){
        currentState.gameOver();
    }
}
