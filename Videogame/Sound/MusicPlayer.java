/*Copyright (C) 2020 Juan Arturo Cruz Cardona
* Final Project: 2D video game using a finite state machine, design patterns and threads.
* This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
* as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with this program. If not, see https://www.gnu.org/licenses/.
_______________________________________________________________________________________________________________________________________ 
* MusicPlayer.java
* Load and control the music in game
*/
package Videogame.Sound;

import javax.sound.sampled.*;

public class MusicPlayer {
    private Clip clip;
    private long clipTime = 0;
    
    private static class SingletonHolder{
        private static MusicPlayer instance = new MusicPlayer();
    }
    public static MusicPlayer getInstance() {
        return SingletonHolder.instance;
    }

    public void loadClip(String path) {
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(getClass().getResource(path));
            clip = AudioSystem.getClip();
            clip.open(stream);
            stream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void playMusic() {
        clip.setMicrosecondPosition(clipTime);
        clip.start();
    }

    public void pauseMusic() {
        clipTime = clip.getMicrosecondPosition();
        clip.stop();
    }
}
