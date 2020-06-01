//  MusicPlayer.java      Autor: Juan Arturo Cruz Cardona
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
