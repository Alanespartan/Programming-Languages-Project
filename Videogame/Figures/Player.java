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
        setHP(10);
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