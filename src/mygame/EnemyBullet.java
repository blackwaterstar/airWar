package mygame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class EnemyBullet extends FlyingObject{										//敌人子弹类
	
	static BufferedImage[] image = new BufferedImage[2];
	int type = 0;
	int vx;
	int vy;
	static {
		for(int i=0;i<image.length;i++)
			image[i] = Tool.getImage("enemybullet"+i+".png");
	}
	
	public EnemyBullet(int x, int y,int vx,int vy,int type) {//参数为子弹的生成x,y轴位置，以及子弹的x,y轴速度,子弹类型
		this.height = 20;
		this.width = 20;
		this.x = x;
		this.y = y;
		this.speed = 1;
		this.vx = speed*vx;
		this.vy = speed*vy;
		this.type = type;
	}
	
	public void Move() {
		this.x += vx;
		this.y += vy;
	}
	
	public void paintObject(Graphics g) {
		if(state == LIFE)
		g.drawImage(image[type], this.x, this.y, null);
	}
}
