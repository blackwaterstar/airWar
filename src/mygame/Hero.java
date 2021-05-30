package mygame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Hero extends FlyingObject{										//英雄类
		
	public static BufferedImage[] image = new BufferedImage[14];
	int life = 5;//英雄生命
	int fire = 1;//英雄火力
	int type;//英雄类型
	int vx;//英雄x轴移动速度
	int vy;//英雄y周移动速度
	int index;
	int cd = 0;//技能CD
	static {
		for(int i=0;i<image.length;i++) {
			if(i<7) {
				image[i] = Tool.getImage("hero1_"+i+".png");
			}
			else {
				image[i] = Tool.getImage("hero2_"+(i-7)+".png");
			}
			
		}
	}
	
	Hero(int type){
		this.type = type;
		if(type == 1) {
			width =112;
			height = 90;
			this.speed= 4;
			x=(GameMain.SCREEN_WIDTH/4)-this.width/2;
			y= GameMain.SCREEN_HEIGHT/4*3;
			this.index=10;
		}
		else {
			width = 82;
			height = 108;
			this.speed= 4;
			x=(GameMain.SCREEN_WIDTH/4*3)-this.width/2;
			y= GameMain.SCREEN_HEIGHT/4*3;
			this.index=10+image.length*5;
		}
		
	}
	
	//根据英雄火力产生英雄的子弹
	public HeroBullet[] HeroShoot(){
		if(type == 1) {
			if(fire==1){//创建子弹组
				HeroBullet[] bs = new HeroBullet[1];
				bs[0] = new HeroBullet(this.x+this.width/2-5,this.y+this.height/4,0,-5,0);//参数为子弹的生成x,y轴位置，以及子弹的x,y轴速度
				return bs;
			}
			else if(fire==2){
				HeroBullet[] bs = new HeroBullet[2];
				bs[0] = new HeroBullet(this.x+this.width/4-5,this.y+this.height/2,0,-5,0);
				bs[1] = new HeroBullet(this.x+this.width/4*3-5,this.y+this.height/2,0,-5,0);
				return bs;
			}
			else if(fire==3){
				HeroBullet[] bs = new HeroBullet[3];
				bs[0] = new HeroBullet(this.x+this.width/4-5,this.y+this.height/2,0,-5,0);
				bs[1] = new HeroBullet(this.x+this.width/4*3-5,this.y+this.height/2,0,-5,0);
				bs[2] = new HeroBullet(this.x+this.width/2-5,this.y+this.height/4,0,-5,0);
				return bs;
			}
			else if(fire==4){
				HeroBullet[] bs = new HeroBullet[5];
				bs[0] = new HeroBullet(this.x+this.width/4-5,this.y+this.height/2,0,-5,0);
				bs[1] = new HeroBullet(this.x+this.width/4*3-5,this.y+this.height/2,0,-5,0);
				bs[2] = new HeroBullet(this.x+this.width/2-5,this.y+this.height/4,0,-5,0);
				bs[3] = new HeroBullet(this.x+this.width/6*5-5,this.y+this.height/2,3,-5,1);
				bs[4] = new HeroBullet(this.x+this.width/6-5,this.y+this.height/2,-3,-5,1);
				return bs;
			}
			else {
				HeroBullet[] bs = new HeroBullet[7];
				bs[0] = new HeroBullet(this.x+this.width/4-5,this.y+this.height/2,0,-5,0);
				bs[1] = new HeroBullet(this.x+this.width/4*3-5,this.y+this.height/2,0,-5,0);
				bs[2] = new HeroBullet(this.x+this.width/2-5,this.y+this.height/4,0,-5,0);
				bs[3] = new HeroBullet(this.x+this.width/6*5-5,this.y+this.height/2,3,-4,1);
				bs[4] = new HeroBullet(this.x+this.width/6-5,this.y+this.height/2,-3,-4,1);
				bs[5] = new HeroBullet(this.x+this.width/6*5-5,this.y+this.height/2,1,-4,1);
				bs[6] = new HeroBullet(this.x+this.width/6-5,this.y+this.height/2,-1,-4,1);
				return bs;
			}
		}
		else {//2号英雄弹幕
			if(fire==1){//创建子弹组
				HeroBullet[] bs = new HeroBullet[1];
				bs[0] = new HeroBullet(this.x+this.width/2-5,this.y+this.height/4,0,-10,2);//参数为子弹的生成x,y轴位置，以及子弹的x,y轴速度
				return bs;
			}
			else if(fire==2){
				HeroBullet[] bs = new HeroBullet[2];
				bs[0] = new HeroBullet(this.x+this.width/4-5,this.y+this.height/2,0,-10,2);
				bs[1] = new HeroBullet(this.x+this.width/4*3-5,this.y+this.height/2,0,-10,2);
				return bs;
			}
			else if(fire==3){
				HeroBullet[] bs = new HeroBullet[3];
				bs[0] = new HeroBullet(this.x+this.width/4-5,this.y+this.height/2,0,-10,2);
				bs[1] = new HeroBullet(this.x+this.width/4*3-5,this.y+this.height/2,0,-10,2);
				bs[2] = new HeroBullet(this.x+this.width/2-5,this.y+this.height/4,0,-10,2);
				return bs;
			}
			else if(fire==4){
				HeroBullet[] bs = new HeroBullet[5];
				bs[0] = new HeroBullet(this.x+this.width/4-5,this.y+this.height/2,0,-10,2);
				bs[1] = new HeroBullet(this.x+this.width/4*3-5,this.y+this.height/2,0,-10,2);
				bs[2] = new HeroBullet(this.x+this.width/2-5,this.y+this.height/4,0,-10,2);
				bs[3] = new HeroBullet(this.x+this.width/3*2-5,this.y+this.height/4*3,0,-10,3);
				bs[4] = new HeroBullet(this.x+this.width/3-5,this.y+this.height/4*3,0,-10,3);
				return bs;
			}
			else {
				HeroBullet[] bs = new HeroBullet[7];
				bs[0] = new HeroBullet(this.x+this.width/4-5,this.y+this.height/2,0,-10,2);
				bs[1] = new HeroBullet(this.x+this.width/4*3-5,this.y+this.height/2,0,-10,2);
				bs[2] = new HeroBullet(this.x+this.width/2-5,this.y+this.height/4,0,-10,2);
				bs[3] = new HeroBullet(this.x+this.width/3*2-5,this.y+this.height/4*3,0,-10,3);
				bs[4] = new HeroBullet(this.x+this.width/3-5,this.y+this.height/4*3,0,-10,3);
				bs[5] = new HeroBullet(this.x+this.width-5,this.y+this.height/2,0,-10,3);
				bs[6] = new HeroBullet(this.x-5,this.y+this.height/2,-0,-10,3);
				return bs;
			}
		}
		
	}
	
	//角色绘图方法
	
	int stateLife = 0;
	public void paintObject(Graphics g) {
		//if判断如果角色再x轴上不越线并且存活就可以左右移动
		if((this.x > 0 && vx <= 0 && this.isLife())
		||(this.x < GameMain.SCREEN_WIDTH-this.width-20) && vx>=0 && this.isLife())
			this.x += vx;
		if((this.y > 0 && vy <= 0 && this.isLife())
		||(this.y < GameMain.SCREEN_HEIGHT-this.height-20) && vy>=0 && this.isLife())
			this.y += vy;
		//角色不断喷射喷射器，机尾喷火
		if(this.state == LIFE) {
			if(stateLife == 0)
			{
				if(type==1)
					g.drawImage(image[0],this.x,this.y,null);
				else
					g.drawImage(image[7],this.x,this.y,null);
				stateLife = 1;
			}
			else {
				if(type==1)
					g.drawImage(image[1],this.x,this.y,null);
				else
					g.drawImage(image[8],this.x,this.y,null);
				stateLife = 0;
			}
				
		}
		//如果角色死亡播放死亡动画
		else if(this.state == DEAD) {
			if(type==1) {
				if(index>=image.length*5)
				{
					state = REMOVE;
					return ;
				}
				g.drawImage(image[(index++)/10],x,y,null);
			}
			else {
				if(index>=image.length*10)
				{
					state = REMOVE;
					return ;
				}
				g.drawImage(image[(index++)/10],x,y,null);
			}
			
		}
	}

	//键盘设置英雄移动速度
	public void setVx(int vx) {
		this.vx = vx;
	}
	public void setVy(int vy) {
		this.vy = vy;
	}
	
	//生命减少
	public void substractLife(){
		if(life > 0)
			life--;
		//生命值为零改为死亡状态
		if(life <= 0) {
			state = DEAD;
//			Music1 a =new Music1();			
//			a.Music3();
		}
	}
	//生命增加
	public void addLife(){
		if(life<5)//生命上限
			life++;
	}
	//火力增加
	public void addFire(){
		if(fire<5)//火力上限
			fire++;
	}
	
	//技能CD减少
	public void substractCD(){
		if(cd>0)
		cd--;
	}
	//技能CD开始计时
	public void CD(){
		cd = 100;
	}
}
