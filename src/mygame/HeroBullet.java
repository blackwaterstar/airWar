package mygame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class HeroBullet extends FlyingObject{									//英雄子弹类
	static BufferedImage[] image =new BufferedImage[4];
	int type;
	double vx;
	double vy;
	static{//导入英雄子弹图片
		for(int i=0;i<image.length;i++)
		image[i] = Tool.getImage("herobullet"+i+".png");
	}
	
	public HeroBullet(int x, int y,int vx,int vy,int type){//参数为子弹的生成x,y轴位置，以及子弹的x,y轴速度,子弹类型
		this.height = 20;
		this.width = 10;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.type = type;
	}
	
	//移动方法
	public void Move(){
		this.y += vy;
		this.x += vx;
	}
	
	//绘图方法
	public void paintObject(Graphics g) {
		if(state == LIFE)
			g.drawImage(image[type],x,y,null);
		else
			return ;
	}
}
