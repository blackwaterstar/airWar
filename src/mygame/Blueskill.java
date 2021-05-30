package mygame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Blueskill extends FlyingObject{
	
	static BufferedImage[] image = new BufferedImage[9];
	static {
		for(int i=0;i<image.length;i++)
		image[i] = Tool.getImage("blueskill"+(i)+".png");
	}
	
	Hero hero;
	public Blueskill(Hero h){
		this.width = 34;
		this.height = 66;
		this.x = h.x+h.width/2-this.width/2;
		this.y = h.y;
		this.hero = h;
		this.state = DEAD;
	}
	
	int time = 230;
	public void Move() {
		if(time>200) {
			this.y -= 10;
		}
		if(time==200) {
			this.x-=184;
			this.y-=168;
			this.width = 402;
			this.height = 402;
			this.state = LIFE;
		}
		if(time<=0)
			this.goDead();
		time--;
	}
	
	int index = 5;
	int i=1;
	int stateLife = 0;
	public void paintObject(Graphics g) {
		if(this.time>=200)
			g.drawImage(image[0],this.x,this.y,null);
		else if(this.state == LIFE) {
			if(time>0) {
				g.drawImage(image[index/5],this.x,this.y,null);
				index+=i;
				if(index>=42)
					i=-1;
				if(index<=7)
					i=1;
			}
		}
	}
}
