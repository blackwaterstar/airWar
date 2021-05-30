package mygame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Enemy2 extends FlyingObject{										//敌人冲撞小飞机类
		
	public static BufferedImage[] image = new BufferedImage[5];
	int life=2;//敌人生命
	static{//导入图片
		for(int i=0;i<image.length;i++) {
		image[i] = Tool.getImage("airplane"+i+".png");
		}
	}
	int direction=1;
	
	public Enemy2(int i,int x,int y){//i为方向,x为x轴生成位置,y为y轴生成位置（设置y轴位置是为了让飞机不平行出现）
		this.width = 48;
		this.height = 35;
		this.speed = 3;
		this.x = x;
		if(i%2==0)
		{
			direction=-1;
			this.x = GameMain.SCREEN_WIDTH-width-x;
		}
		this.y = -height-y;
	}
	
	public void Move() {
			y+=speed;
	}
	
	int index=10;
	public void paintObject(Graphics g) {
		if(this.state == LIFE) {
			g.drawImage(image[0],x,y,null);
		}
		else if(this.state == DEAD) {
			if(index>=image.length*10)
				{
					state = REMOVE;
					return ;
				}
			g.drawImage(image[(index++)/10],x,y,null);
		}
	}
	
	public void substractLife(){
		if(life > 0)
			life--;
		if(life <= 0) {//生命值为零改为死亡状态
			state = DEAD;
			GameMain.score += 1;
		Music1 a =new Music1();			
		a.Music2();
		}
	}
}
