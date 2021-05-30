package mygame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bee extends FlyingObject{											//奖励类
		
	public static BufferedImage[] image = new BufferedImage[2];
	public static final int fire = 0;
	public static final int cure = 1;
	public int type;
	int direction = 1;//方向，1为向右移动，-1为向左移动
	static{//导入奖励图片
			image[0] = Tool.getImage("fire.png");
			image[1] = Tool.getImage("cure.png");
	}
	
	public Bee(){
		width = 62;
		height = 62;
		x = (int)(Math.random()*GameMain.SCREEN_WIDTH-this.width);
		y = -height;
		type = (int)(Math.random()*2);//随机生成加生命，火力的奖励
	}
	
	public void Move() {
		if(x>=GameMain.SCREEN_WIDTH-this.width-20)//碰到边界时反弹
			direction = -1;
		if(x <= 0)
			direction = 1;
		x += 1*direction;
		y += 1;
	}
	
	public void paintObject(Graphics g) {
		if(this.isLife()) {
			g.drawImage(image[type],x,y,null);
		}
	}
}
