/*Copyright (C) 2020 Juan Arturo Cruz Cardona
* Final Project: 2D video game using a finite state machine, design patterns and threads.
* This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
* as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with this program. If not, see https://www.gnu.org/licenses/.
_______________________________________________________________________________________________________________________________________ 
* Factory.java
* Creates different instances of entitys to be use in game
*/
package Videogame.Factory;

import Videogame.State.*;

public class Factory{
    private static class SingletonHolder{
        private static Factory instance = new Factory();
    }
    public static Factory getInstance(){
        return SingletonHolder.instance;
    }
    // Método para crear estados

    public State createState(String type) {
        switch(type){
            case "Play": return new PlayState();
            case "Pause": return new PauseState();
            case "GameOver": return new GameOverState();
            case "Menu": return new MenuState();
            default: return null;
        }
    }
}