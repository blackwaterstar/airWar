package mygame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Aerolite extends FlyingObject {

	static BufferedImage[] image = new BufferedImage[1];
	int life=30;//敌人生命
	int direction=1;
	int direction1=1;
	static{//导入敌人图片
		for(int i=0;i<image.length;i++) {
			image[i] = Tool.getImage("Aerolite"+i+".png");
		}
		
	}
	
	
	Aerolite(int i,int x,int y){//i为方向,x为x轴生成位置,y为y轴生成位置（设置y轴位置是为了让飞机不平行出现）
		this.width = 119;
		this.height = 150;
			this.speed = 1;//速度
			this.x = x;
			if(i%2==0)
			{
				direction=-1;
				this.x = GameMain.SCREEN_WIDTH-width-x;
			}
			this.y = -height-y;

	}


		int time = 0;										//时间变量；用于设置移动路线
		public void Move(){
				x+=2*speed*direction;
				y+=3*speed;
				time++; 
		}
		
	
		int index=15;
		public void paintObject(Graphics g) {
			if(this.state == LIFE) {
				g.drawImage(image[0],x,y,null);															//图片
			
			}
			else if(this.state == DEAD) {
				if(index>=image.length*15)
					{
						state = REMOVE;
						return ;
					}
				g.drawImage(image[(index++)/15],x,y,null);
			}
		}
	
	
	//生命减少方法
	public void substractLife(){
		if(life > 0)
			life--;
		if(life <= 0) {//生命值为零改为死亡状态
			state = DEAD;
			GameMain.score +=50;
		Music1 a =new Music1();			
		a.Music2();
		}
			
	}

}
