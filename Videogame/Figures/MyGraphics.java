package Videogame.Figures;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class MyGraphics extends Thread{
    protected int x1;
	protected int y1;
	protected int x2;
	protected int y2;
	protected int height;
	protected int width;
	protected int hp;
	protected Boolean isDead;
	protected BufferedImage image;

	public void render(Graphics g) {
        g.drawImage(image, x1, y1, width, height, null);
    }

	public void setX1(int x1){
		this.x1 = x1;
		this.x2 = x1 + width;
	}

	public void setY1(int y1){
		this.y1 = y1;
		this.y2 = y1 + height;
    }
    
    public void setWidth(int width){
		this.width = width;
	}

	public void setHeight(int height){
		this.height = height;
	}

	public void setHP(int hp){
		this.hp = hp;
	}

	public void setIsDead(Boolean isDead){
		this.isDead = isDead;
	}

	public void setImage(BufferedImage image){
		this.image = image;
	}

	public int getX1() {
		return x1;
	}

    public int getX2() {
		return x2;
	}
    
    public int getY1() {
		return y1;
    }

	public int getY2() {
		return y2;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	public int getHP(){
		return hp;
	}

	public Boolean getIsDead(){
		return isDead;
	}

	public String toString(){
		return "x1: " + x1 + ";y1: " + y1 + ";x2: " + x2 + ";y2: " + y2 + ";h: " + height + ";w: " + width;
	}
}
