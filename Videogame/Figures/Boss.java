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