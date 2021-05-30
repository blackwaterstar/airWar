package mygame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Enemy1 extends FlyingObject{							//敌人中等发射子弹飞机
	
	public static BufferedImage[] image = new BufferedImage[5];
	static BufferedImage[] bloodimage = new BufferedImage[2];
	int life=5;//敌人生命
	static{//导入敌人图片
		for(int i=0;i<image.length;i++) {
			image[i] = Tool.getImage("bigplane"+i+".png");
		}
		
	}
	int direction=1;
	
	public Enemy1(int i,int x,int y){//i为方向,x为x轴生成位置,y为y轴生成位置（设置y轴位置是为了让飞机不平行出现）
		this.width = 138;
		this.height = 90;
		this.speed = 1;//速度
		this.x = x;
		if(i%2==0)
		{
			direction=-1;
			this.x = GameMain.SCREEN_WIDTH-width-x;
		}
		this.y = -height-y;
	}
	
	int time=0;
	public void Move() {
		if(this.time < 200 || this.time >400)//敌人停顿一会
		{
			x+=2*speed*direction;
			y+=speed;
		}
		this.time++;
	}
	
	int index=10;//index设置为10是为了后面播放图片速度不要太快
	public void paintObject(Graphics g) {
		
		if(this.state == LIFE) {
			g.drawImage(image[0],x,y,null);
			
			
		}
		else if(this.state == DEAD) {//敌人死亡时播放死亡图片
			if(index>=image.length*10)
				{
					state = REMOVE;
					return ;
				}
			
			g.drawImage(image[(index++)/10],x,y,null);
			
		}
	}
	
	//生成子弹的方法
	public EnemyBullet[] Shoot() {
		EnemyBullet[] eb = new EnemyBullet[4];
		eb[0]= new EnemyBullet(this.x+this.width/2,this.y+this.height/2,1,1,0);//参数为子弹的生成x,y轴位置，以及子弹的x,y轴速度
		eb[1]= new EnemyBullet(this.x+this.width/2,this.y+this.height/2,1,-1,0);
		eb[2]= new EnemyBullet(this.x+this.width/2,this.y+this.height/2,-1,1,0);
		eb[3]= new EnemyBullet(this.x+this.width/2,this.y+this.height/2,-1,-1,0);
		return eb;
	}
	
	//生命减少方法
	public void substractLife(){
		if(life > 0)
			life--;
		if(life <= 0) {//生命值为零改为死亡状态
			state = DEAD;
			GameMain.score += 3;
		Music1 a =new Music1();			
		a.Music2();
		}
			
	}
}
