package mygame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Redskill extends FlyingObject{
	
	static BufferedImage[] image = new BufferedImage[2];
	static {
		for(int i=0;i<image.length;i++)
		image[i] = Tool.getImage("redskill"+(i+1)+".png");
	}
	
	Hero hero;
	public Redskill(Hero h){
		this.width = 114;
		this.height = 1186;
		this.x = h.x+h.width/2-this.width;
		this.y = h.y-this.height+h.height/2;
		this.hero = h;
	}
	
	int time = 200;
	public void Move() {
		if( this.isLife()) {
			this.x = hero.x+hero.width/2-this.width/2;
			this.y = hero.y-this.height+hero.height/2;			
		}
		if(time<=0)
			this.goDead();
		time--;
	}
	
	int stateLife = 0;
	public void paintObject(Graphics g) {
		if(hero.isLife() && this.state == LIFE) {
			if(stateLife == 0)
			{
					g.drawImage(image[0],this.x,this.y,null);
				stateLife = 1;
			}
			else {
					g.drawImage(image[1],this.x,this.y,null);
				stateLife = 0;
			}
		}
	}
}
