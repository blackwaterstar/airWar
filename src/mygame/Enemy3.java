/**
 * 
 */
package mygame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * 
 *
 */
public class Enemy3 extends FlyingObject {

	/**
	 * 
	 */
	static BufferedImage[] image = new BufferedImage[7];
//	static BufferedImage[] bloodimage = new BufferedImage[2];
	int life=300;//敌人生命
	int direction=1;
	int direction1=1;
	static{//导入敌人图片
		for(int i=0;i<image.length;i++) {
			image[i] = Tool.getImage("Taurus0"+i+".png");
		}
		
	}
	
	
	Enemy3(){
		this.width = 360;
		this.height = 276;
		this.x = GameMain.SCREEN_WIDTH/2-this.width/2;
		this.y = -this.height;
		speed = 4;
	}

		int time = 0;										//时间变量；用于设置BOSS移动路线
		public void Move(){
			if(time >= 0 && time < 100) {					//BOSS进入战场
				this.y += speed;
				this.x += speed;
				time++;
			}
			else {
				if(y>=GameMain.SCREEN_HEIGHT-this.height+100)//接近边界时反弹
					direction1 = -1;
				if(y <= 0)								//接近边界时反弹
					direction1 = 1;
				this.y += 1*direction1;
				if(x>=GameMain.SCREEN_WIDTH-this.width-120)//接近边界时反弹
					direction = -1;
				if(x <= 100)								//接近边界时反弹
					direction = 1;
				this.x += 1*direction;
			}		
		
		}
	
		int index=15;
		public void paintObject(Graphics g) {
			if(this.state == LIFE) {
				g.drawImage(image[0],x,y,null);															//BOSS图片
				//g.drawImage(bloodimage[0],GameMain.SCREEN_WIDTH/2-300,50,600,42,null);					//BOSS血条框
			//	g.drawImage(bloodimage[1],GameMain.SCREEN_WIDTH/2-300,50,600*this.life/2000,42,null);	//BOSS血条
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
	
	//生成子弹的方法
	public EnemyBullet[] Shoot(Hero[] h) {
		EnemyBullet[] eb = new EnemyBullet[8];
		eb[0]= new EnemyBullet(this.x+this.width/2,this.y+this.height/2,1,1,0);//参数为子弹的生成x,y轴位置，以及子弹的x,y轴速度
		eb[1]= new EnemyBullet(this.x+this.width/2,this.y+this.height/2,1,-1,0);
		eb[2]= new EnemyBullet(this.x+this.width/2,this.y+this.height/2,-1,1,0);
		eb[3]= new EnemyBullet(this.x+this.width/2,this.y+this.height/2,-1,-1,0);
		eb[4]= new EnemyBullet(this.x+this.width/2,this.y+this.height/2,-1,-2,0);
		eb[5]= new EnemyBullet(this.x+this.width/2,this.y+this.height/2,1,2,0);
		eb[6]= new EnemyBullet(this.x+this.width/2,this.y+this.height/2,-1,2,0);
		eb[7]= new EnemyBullet(this.x+this.width/2,this.y+this.height/2,1,-2,0);
		return eb;
	}
	
	//生命减少方法
	public void substractLife(){
		if(life > 0)
			life--;
		if(life <= 0) {//生命值为零改为死亡状态
			state = DEAD;
			GameMain.score +=500;
		Music1 a =new Music1();			
		a.Music2();
		}
			
	}
}
