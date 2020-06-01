package Videogame.Figures;

import Videogame.ImageLoader.ImageLoader;

public class Thunder extends MyGraphics{
    public Thunder(int y1){
        setWidth(50);
        setHeight(30);
        setX1(950);
        setY1(y1);
        setImage(ImageLoader.getInstance().getImage("thunder"));
    }

    public void update(){
        setX1(getX1() - 1);  
    }
}