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