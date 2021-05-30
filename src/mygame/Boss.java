package mygame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Boss extends FlyingObject{
		static BufferedImage[] image = new BufferedImage[16];
		static BufferedImage[] bloodimage = new BufferedImage[2];
		int direction = 1;										//BOSS移动路线的方向
		int life = 2000;										//BOSS血量
		static {
			for(int i=0;i<image.length; i++) {
				image[i] = Tool.getImage("boss"+i+".png");
			}
			for(int i=0;i<bloodimage.length; i++) {
				bloodimage[i] = Tool.getImage("bossblood"+i+".png");
			}
		}
		
		Boss(){
			this.width = 295;
			this.height = 200;
			this.x = GameMain.SCREEN_WIDTH/2-this.width/2;
			this.y = -this.height;
			speed = 2;
		}
		
		int time = 0;										//时间变量；用于设置BOSS移动路线
		public void Move(){
			if(time >= 0 && time < 100) {					//BOSS进入战场
				this.y += speed;
				time++;
			}
			else {
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
				g.drawImage(bloodimage[0],GameMain.SCREEN_WIDTH/2-300,50,600,42,null);					//BOSS血条框
				g.drawImage(bloodimage[1],GameMain.SCREEN_WIDTH/2-300,50,600*this.life/2000,42,null);	//BOSS血条
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
		
		public void substractLife(){						//生命值减少
			if(life > 0)
				life--;
			if(life <= 0) {									//生命值为零改为死亡状态
				state = DEAD;
				GameMain.score += 1000;						//BOSS击杀得分
				Music1 a =new Music1();			
				a.Music2();
			}
		}
		
		//生成子弹的方法
		public EnemyBullet[] Shoot(int random ,Hero[] h) {
			if(random == 1) {
				EnemyBullet[] eb = new EnemyBullet[12];
				eb[0]= new EnemyBullet(this.x+this.width/2,this.y+this.height/2,2,1,0);//参数为子弹的生成x,y轴位置，以及子弹的x,y轴速度,子弹类型
				eb[1]= new EnemyBullet(this.x+this.width/2,this.y+this.height/2,2,-1,0);
				eb[2]= new EnemyBullet(this.x+this.width/2,this.y+this.height/2,-2,1,0);
				eb[3]= new EnemyBullet(this.x+this.width/2,this.y+this.height/2,-2,-1,0);
				eb[4]= new EnemyBullet(this.x+this.width/2,this.y+this.height/2,1,2,0);//参数为子弹的生成x,y轴位置，以及子弹的x,y轴速度,子弹类型
				eb[5]= new EnemyBullet(this.x+this.width/2,this.y+this.height/2,1,-2,0);
				eb[6]= new EnemyBullet(this.x+this.width/2,this.y+this.height/2,-1,2,0);
				eb[7]= new EnemyBullet(this.x+this.width/2,this.y+this.height/2,-1,-2,0);
				eb[8]= new EnemyBullet(this.x+this.width/2,this.y+this.height/2,1,1,0);//参数为子弹的生成x,y轴位置，以及子弹的x,y轴速度,子弹类型
				eb[9]= new EnemyBullet(this.x+this.width/2,this.y+this.height/2,1,-1,0);
				eb[10]= new EnemyBullet(this.x+this.width/2,this.y+this.height/2,-1,1,0);
				eb[11]= new EnemyBullet(this.x+this.width/2,this.y+this.height/2,-1,-1,0);
				return eb;
			}
			else if (random == 2) {
				EnemyBullet[] eb = new EnemyBullet[4];
				eb[0]= new EnemyBullet(this.x+this.width/5,this.y+this.height/2,0,2,0);//参数为子弹的生成x,y轴位置，以及子弹的x,y轴速度,子弹类型
				eb[1]= new EnemyBullet(this.x+this.width/5*2,this.y+this.height/2,0,2,0);
				eb[2]= new EnemyBullet(this.x+this.width/5*3,this.y+this.height/2,0,2,0);
				eb[3]= new EnemyBullet(this.x+this.width/5*4,this.y+this.height/2,0,2,0);
				return eb;
			}
			else {
				int r = (int)(Math.random()*h.length);
				if(r == 0 && !h[0].isLife())
					r=1;
				else if(r == 1 && !h[1].isLife())
					r=0;
				EnemyBullet[] eb = new EnemyBullet[2];
					if(h[r].isLife()) {
						eb[0]= new EnemyBullet(this.x+this.width/5,this.y+this.height/2,-(this.x-h[r].x)/90,-(this.y-h[r].y)/90,1);//参数为子弹的生成x,y轴位置，以及子弹的x,y轴速度
						eb[1]= new EnemyBullet(this.x+this.width/5*4,this.y+this.height/2,-(this.x-h[r].x)/90,-(this.y-h[r].y)/90,1);
					}
					else{
						eb[0]= new EnemyBullet(this.x+this.width/5,this.y+this.height/2,0,5,1);//参数为子弹的生成x,y轴位置，以及子弹的x,y轴速度,子弹类型
						eb[1]= new EnemyBullet(this.x+this.width/5*4,this.y+this.height/2,0,5,1);
					}
				
				return eb;
			}
			
		}
		
}
