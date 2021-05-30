package mygame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;//画笔类，画图片
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;//导入图片的类
import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;//和下面的TimerTask类是用来设置定时任务的
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;//java的类
import javax.swing.JPanel;

import entitise.User;//java的面板类

public class GameMain extends JPanel implements KeyListener{
	
		public static final int SCREEN_WIDTH = 1200;//设置屏幕的宽度
		public static final int SCREEN_HEIGHT = 700;//设置屏幕的高度
		public static final int START = 0; //设置开始状态
		public static final int CHOOSS = 1; //设置开始状态
		public static final int RUNNING = 2; //设置运行状态
		public static final int PAUSE = 3; //设置暂停状态
		public static final int OVER = 4; //设置结束状态
		public static final int VICTORY = 5; //设置胜利状态
		public static int state = START; //设置状态为开始状态
//		JFrame jf;
//		Graphics g;
		public static BufferedImage start;  //开始图标
		public static BufferedImage pause; //暂停图标
		public static BufferedImage over;   //结束图标
		public static BufferedImage victory;   //胜利图标
		/*public static BufferedImage[] heroblood = new BufferedImage[3];
		public static BufferedImage[] button =new BufferedImage[4];
		public static BufferedImage[] buttonP =new BufferedImage[4];
		public static BufferedImage startBackground;*/
		public static BufferedImage[] heroblood = new BufferedImage[3];
		public static BufferedImage[] button =new BufferedImage[2];
		public static BufferedImage[] buttonP =new BufferedImage[2];
		public static BufferedImage[] button2 =new BufferedImage[2];
		public static BufferedImage[] button2P =new BufferedImage[2];
		public static BufferedImage startBackground;
	/*	static {//静态代码块
				start=Tool.getImage("start.png");
				startBackground=Tool.getImage("start_bg.png");
				pause = Tool.getImage("pause.png");

				for(int i=0;i<heroblood.length;i++) {
					heroblood[i] = Tool.getImage("heroblood"+i+".png");
				}
				for(int i=0;i<button.length;i++) {
					button[i] = Tool.getImage("button"+i+".png");
				}
				for(int i=0;i<buttonP.length;i++) {
					buttonP[i] = Tool.getImage("button_p"+i+".png");
				}
				
		}*/
		static {//静态代码块
			start=Tool.getImage("start_bg.png");
			startBackground=Tool.getImage("start_bg.png");
			pause = Tool.getImage("pause.png");
			over = Tool.getImage("gameover.jpg");
			victory = Tool.getImage("victory.jpg");
			for(int i=0;i<heroblood.length;i++) {
				heroblood[i] = Tool.getImage("heroblood"+i+".png");
			}
			for(int i=0;i<button.length;i++) {
				button[i] = Tool.getImage("button"+i+".png");
			}
			for(int i=0;i<buttonP.length;i++) {
				buttonP[i] = Tool.getImage("button_p"+i+".png");
			}
			for(int i=0;i<button2.length;i++) {
				button2[i] = Tool.getImage("plane"+i+".png");
			}
			for(int i=0;i<button2P.length;i++) {
				button2P[i] = Tool.getImage("plane_p"+i+".png");
			}
	}
//		GameMain(JFrame jf){
//			this.jf=jf;
//			this.g=jf.getGraphics();
//		}

		
		public Background background = new Background();
		public Hero[] hero = {}; 
		public Enemy1[] enemy1 = {};
		public Enemy2[] enemy2 = {};
		public Aerolite[] aerolite= {}; 
		//public Enemy3[] enemy3 = {};
		public Boss[] boss = {};
		public HeroBullet[] herobullets ={};
		public EnemyBullet[] enemybullets ={};
		public Bee[] bee = {};
		static long score = 0L;
		public Redskill rs;//红飞机技能
		public Blueskill bs;//红飞机技能
		
	//**当前焦点在哪个菜单项*/	
	//	private int focusIndex;
		/**菜单项位置的横坐标*/
	//	private int menu_x = 500;
		/**菜单项位置的纵坐标*/
	//	private int[] menu_ys = new int[]{350, 400, 450, 500};
		
		/**当前焦点在哪个菜单项*/	
		private int focusIndexX;
		private int focusIndexY;
		/**菜单项位置的横坐标*/
		private int menu_x = 500;
		private int[] menu2_x = new int[]{250,650};
		/**菜单项位置的纵坐标*/
		private int[] menu_ys = new int[]{350, 450};
		private int menu2_ys = 300;
		public int type; 
		
		
/*		public GameMain() {
			//设置当前画布可以获得焦点。
			this.setFocusable(true);
			//设置自己为自己的按键事件监听器
			this.addKeyListener(this);
		}*/
		
		//生成奖励
		public void beego() {
			Bee b = new Bee();
			bee = Arrays.copyOf(bee,bee.length+1);
			bee[bee.length-1] = b;
		}
		//生成英雄子弹 
		public void heroshoot(){
			for(int i=0; i<hero.length;i++)
			if(hero[i].isLife()) {
			HeroBullet[] bs = hero[i].HeroShoot();
			herobullets = Arrays.copyOf(herobullets, herobullets.length + bs.length);
			System.arraycopy(bs,0,herobullets,herobullets.length-bs.length,bs.length);
			}
		}
		//生成1号敌人子弹 
		public void enemyshoot(){
			for(int i=0; i<enemy1.length;i++ ) {
				EnemyBullet[] eb = enemy1[i].Shoot();
				if(!enemy1[i].isLife())
					continue;
				enemybullets = Arrays.copyOf(enemybullets, enemybullets.length + eb.length);
				System.arraycopy(eb,0,enemybullets,enemybullets.length-eb.length,eb.length);
				}
		}
	/*	//生成金牛号敌人子弹 
		public void enemy3shoot(){
			for(int i=0; i<enemy3.length;i++ ) {
				EnemyBullet[] eb = enemy3[i].Shoot(hero);
				if(!enemy3[i].isLife())
					continue;
				enemybullets = Arrays.copyOf(enemybullets, enemybullets.length + eb.length);
				System.arraycopy(eb,0,enemybullets,enemybullets.length-eb.length,eb.length);
				}
		}*/
		//生成BOSS弹幕
		public void bossshoot(int random){
			for(int i=0; i<boss.length;i++ ) {
				EnemyBullet[] bb = boss[i].Shoot(random,hero);
				if(!boss[i].isLife())
					continue;
				enemybullets = Arrays.copyOf(enemybullets, enemybullets.length + bb.length);
				System.arraycopy(bb,0,enemybullets,enemybullets.length-bb.length,bb.length);
				}
		}
		
		//检测碰撞
		public void Bang(){
			//英雄子弹与敌人的碰撞
			for(int i = 0; i < herobullets.length; i++){
			
				HeroBullet b = herobullets[i];
				for(int j=0; j<enemy1.length; j++){
					Enemy1 f = enemy1[j];
					if(Tool.hit(b, f) && f.isLife() && b.isLife())
					{
						f.substractLife();
						b.goDead();
					}
				}
				for(int j=0; j<enemy2.length; j++){
					Enemy2 f = enemy2[j];
					if(Tool.hit(b, f) && f.isLife() && b.isLife())
					{
						f.substractLife();
						b.goDead();
					}
				}
				for(int j=0; j<aerolite.length; j++){
					Aerolite f = aerolite[j];
					if(Tool.hit(b, f) && f.isLife() && b.isLife())
					{
						f.substractLife();
						b.goDead();
					}
				}
			/*	for(int j=0; j<enemy3.length; j++){
					Enemy3 f = enemy3[j];
					if(Tool.hit(b, f) && f.isLife() && b.isLife())
					{
						f.substractLife();
						b.goDead();
					}
				}*/
				for(int j=0; j<boss.length; j++){
					Boss bo = boss[j];
					if(Tool.hit(b, bo) && bo.isLife() && b.isLife())
					{
						bo.substractLife();
						b.goDead();
					}
				}
			}
			
			//红飞机技能攻击判断
			if(rs!=null && rs.isLife() && rs.hero.isLife()) {
				for(int j=0; j<enemy1.length; j++){
					Enemy1 f = enemy1[j];
					if(Tool.hit(rs, f) && f.isLife() && rs.isLife())
					{
						f.substractLife();
					}
				}
				for(int j=0; j<enemy2.length; j++){
					Enemy2 f = enemy2[j];
					if(Tool.hit(rs, f) && f.isLife() && rs.isLife())
					{
						f.substractLife();
					}
				}
				for(int j=0; j<aerolite.length; j++){
					Aerolite f = aerolite[j];
					if(Tool.hit(rs, f) && f.isLife() && rs.isLife())
					{
						f.substractLife();
						//Music1 a =new Music1();			
						//a.Music7();
					}
				}
			/*	for(int j=0; j<enemy3.length; j++){
					Enemy3 f = enemy3[j];
					if(Tool.hit(rs, f) && f.isLife() && rs.isLife())
					{
						f.substractLife();
						
					}
				}*/
				for(int j=0; j<boss.length; j++){
					Boss bo = boss[j];
					if(Tool.hit(rs, bo) && bo.isLife() && rs.isLife())
					{
						bo.substractLife();
					}
				}
			}
			//蓝飞机技能攻击判断
			if(bs!=null && bs.isLife()) {
				for(int j=0; j<enemy1.length; j++){
					Enemy1 f = enemy1[j];
					if(Tool.hit(bs, f) && f.isLife() && bs.isLife())
					{
						f.substractLife();
					}
				}
				for(int j=0; j<enemy2.length; j++){
					Enemy2 f = enemy2[j];
					if(Tool.hit(bs, f) && f.isLife() && bs.isLife())
					{
						f.substractLife();
					}
				}
				for(int j=0; j<aerolite.length; j++){
					Aerolite f = aerolite[j];
					if(Tool.hit(bs, f) && f.isLife() && bs.isLife())
					{
						f.substractLife();
						//Music1 ba =new Music1();			
					    //ba.Music7();
					}
				}
				/*for(int j=0; j<enemy3.length; j++){
					Enemy3 f = enemy3[j];
					if(Tool.hit(bs, f) && f.isLife() && bs.isLife())
					{
						f.substractLife();
						
					}
				}*/
				for(int j=0; j<boss.length; j++){
					Boss bo = boss[j];
					if(Tool.hit(bs, bo) && bo.isLife() && bs.isLife())
					{
						bo.substractLife();
					}
				}
			}
			
			for(int j=0;j<hero.length;j++) {
				Hero h = hero[j];
				//英雄与敌人碰撞
				for (int i = 0; i < enemy1.length; i++) {
					Enemy1 f = enemy1[i];
					if (h.isLife() && f.isLife() && Tool.hit(h,f)) {
						f.goDead();
						h.substractLife();//减少生命
					Music1 a =new Music1();			
					a.Music2();
					}
				}
				for (int i = 0; i < enemy2.length; i++) {
					Enemy2 f = enemy2[i];
					if (h.isLife() && f.isLife() && Tool.hit(h,f)) {
						f.goDead();
						h.substractLife();//减少生命
					Music1 a =new Music1();			
					a.Music2();
					}
				}
				for (int i = 0; i < aerolite.length; i++) {
					Aerolite f = aerolite[i];
					if (h.isLife() && f.isLife() && Tool.hit(h,f)) {
						h.substractLife();//减少生命
					Music1 a =new Music1();			
					a.Music2();
					}
				}
				/*for (int i = 0; i < enemy3.length; i++) {
					Enemy3 f = enemy3[i];
					if (h.isLife() && f.isLife() && Tool.hit(h,f)) {
						f.substractLife();
						h.substractLife();//减少生命
					Music1 a =new Music1();			
					a.Music2();
					}
				}*/
				for(int i=0; i<boss.length; i++){
					Boss bo = boss[i];
					if(bo.isLife() && h.isLife() && Tool.hit(h,bo)) {
						h.goDead();
					Music1 a =new Music1();			
					a.Music3();
					}
				}
				//英雄与敌人子弹碰撞
				for (int i = 0; i < enemybullets.length; i++) {
					EnemyBullet f = enemybullets[i];
					if (h.isLife() && f.isLife() && Tool.hit(h,f)) {
						f.goDead();
						h.substractLife();//减少生命
					}
				}
				for (int i = 0; i < bee.length; i++) {
					Bee f = bee[i];
					if (h.isLife() && f.isLife() && Tool.hit(h,f)) {
						f.goDead();
						if(f.type == 0) {
							h.addFire();
							
						}
						else {
							h.addLife();
						}
						
					}
				}
			}
			
		}
		
		//删除越界飞行物
		public void outOfBounds(){
			int index=0;
			Enemy1[] enemy1Live = new Enemy1[enemy1.length];
			for(int i=0; i<enemy1.length; i++) {
				Enemy1 f = enemy1[i];
				if(!f.outOfBound()) {
					enemy1Live[index++]=f;
				}
			}
			enemy1 = Arrays.copyOf(enemy1Live, index);
			index=0;
			Enemy2[] enemy2Live = new Enemy2[enemy2.length];
			for(int i=0; i<enemy2.length; i++) {
				Enemy2 f = enemy2[i];
				if(!f.outOfBound()) {
					enemy2Live[index++]=f;
				}
			}
			enemy2 = Arrays.copyOf(enemy2Live, index);
			index=0;
			Aerolite[] aerolitesLive = new Aerolite[aerolite.length];
			for(int i=0; i<aerolite.length; i++) {
				Aerolite f = aerolite[i];
				if(!f.outOfBound()) {
					aerolitesLive[index++]=f;
				}
			}
			aerolite = Arrays.copyOf(aerolitesLive, index);
			index=0;
			HeroBullet[] bulletLive = new HeroBullet[herobullets.length];
			for (int i = 0; i < herobullets.length; i++) {
				HeroBullet b = herobullets[i];
				if (!b.outOfBound()) {
					bulletLive[index++] = b;
				}
			}
			
			herobullets = Arrays.copyOf(bulletLive, index);
			index=0;
			EnemyBullet[] ebulletLive = new EnemyBullet[enemybullets.length];
			for (int i = 0; i < enemybullets.length; i++) {
				EnemyBullet b = enemybullets[i];
				if (!b.outOfBound()) {
					ebulletLive[index++] = b;
				}
			}
			enemybullets = Arrays.copyOf(ebulletLive, index);
			index=0;
			Bee[] bs = new Bee[bee.length];
			for (int i = 0; i < bee.length; i++) {
				Bee b = bee[i];
				if (!b.outOfBound()) {
					bs[index++] = b;
				}
			}
			bee = Arrays.copyOf(bs, index);
		}
		
		public void herocd() {
			hero[0].substractCD();
			if(hero.length==2) {
				hero[1].substractCD();
			}
		}
		
		//判断游戏是否胜利(顺便删除死亡的BOSS类)
		public void victory() {
			int index = 0;
			int v = boss.length;
			Boss[] bos = new Boss[boss.length];
			for(int i=0; i<boss.length; i++) {
				if(!boss[i].isRemove()) {
					bos[index++]=boss[i];
				}
			}
			boss = Arrays.copyOf(bos, index);
			if(index == 0 && v > 0)
				state = VICTORY;
		}
		
		//判断游戏是否失败
				public void defeat() {
					Hero[] h = new Hero[hero.length];
					for(int i=0; i<hero.length; i++) {
						if(!hero[i].isRemove()) {
							return ;
						}
						state = OVER;
					}
				}
		
		//（全部类的移动方法）
		public void allMove() {
			for(int i=0;i<enemy1.length;i++)
				enemy1[i].Move();
			for(int i=0;i<enemy2.length;i++)
				enemy2[i].Move();
			for(int i=0;i<aerolite.length;i++)
				aerolite[i].Move();
		/*	for(int i=0;i<enemy3.length;i++)
				enemy3[i].Move();*/
			for(int i=0; i<boss.length; i++)
				boss[i].Move();
			for (int i = 0; i < herobullets.length; i++)
				herobullets[i].Move();
			if(rs!=null) {
				rs.Move();
			}
			if(bs!=null) {
				bs.Move();
			}
			for (int i = 0; i < enemybullets.length; i++)
				enemybullets[i].Move();
			for (int i = 0; i < bee.length; i++)
				bee[i].Move();
			background.Move();
		}
		
		@Override//重写paint方法（全部类的绘图方法）
		public void paint(Graphics g) {
//			super.paint(g);
			if(state==START) {
				g.drawImage(startBackground,0,0,null);
				for(int i = 0;i < button.length;i++) {
					int x = menu_x;
					int y = menu_ys[i];
					if(focusIndexX==i)
						g.drawImage(button[i],x,y,200 ,50 , null);
					else
						g.drawImage(buttonP[i],x,y,200 ,50 , null);
					/*if(focusIndex==i)
					
						g.drawImage(button[i],x,y,200 ,50 , null);
					else
						g.drawImage(buttonP[i],x,y,200 ,50 , null);*/
				}
			}
			else if(state==CHOOSS) {
				g.drawImage(startBackground,0,0,null);
				for(int i = 0;i < button2.length;i++) {
					int x = menu2_x[i];
					int y = menu2_ys;
					if(focusIndexY==i)
						g.drawImage(button2[i],x,y,300 ,300 , null);
					else
						g.drawImage(button2P[i],x,y,300 ,300 , null);
				}
				
			}
			else if(state == RUNNING){
				background.paintObject(g);
			for (int i = 0; i < enemybullets.length; i++)
				enemybullets[i].paintObject(g);
			for(int i=0;i<enemy1.length;i++)
				enemy1[i].paintObject(g);
			for(int i=0;i<enemy2.length;i++)
				enemy2[i].paintObject(g);
			for(int i=0;i<aerolite.length;i++)
				aerolite[i].paintObject(g);
			/*for(int i=0;i<enemy3.length;i++)
				enemy3[i].paintObject(g);*/
			for(int i=0; i<boss.length; i++)
				boss[i].paintObject(g);
			for (int i = 0; i < herobullets.length; i++)
				herobullets[i].paintObject(g);
			for(int i=0;i<bee.length;i++)
				bee[i].paintObject(g);
			if(bs!=null)
				bs.paintObject(g);
			if(rs!=null)
				rs.paintObject(g);
			for(int i=0;i<hero.length;i++) {
				hero[i].paintObject(g);
			}
				g.setFont(new Font("宋体",Font.BOLD,24));
				g.setColor(new Color(225,255,255));
				g.drawString("得分："+ score, SCREEN_WIDTH/2-60 , 30 );
				g.drawImage(heroblood[1], 17, SCREEN_HEIGHT-70, 196 ,20 , null);
				if(hero[0].isLife())
				g.drawImage(heroblood[0], 20, SCREEN_HEIGHT-68, 190*hero[0].life/5 ,15 , null);
				g.drawString("CD："+ hero[0].cd/3, 10,SCREEN_HEIGHT-100 );
			if(hero.length == 2){
				g.drawImage(heroblood[2], SCREEN_WIDTH-225, SCREEN_HEIGHT-70, 196 ,20 , null);
				if(hero[1].isLife())
				g.drawImage(heroblood[0], SCREEN_WIDTH-32, SCREEN_HEIGHT-68, -190*hero[1].life/5 ,15 , null);
				g.drawString("CD："+ hero[1].cd/3,  SCREEN_WIDTH-100,SCREEN_HEIGHT-100 );
				}
			}
			else if(state == PAUSE) {
				g.drawImage(pause, 500, 200, null);
			}
			
			else if(state == OVER) {
				g.setFont(new Font("楷体",Font.BOLD,100));
				g.setColor(new Color(133,133,133));
				g.drawImage(over, 0, 0, null);
				g.drawString(""+score, 600 , 445 );
			}
			else if(state == VICTORY) {
				g.setFont(new Font("楷体",Font.BOLD,100));
				g.setColor(new Color(133,133,133));
				g.drawImage(victory, 0, 0, null);
				g.drawString(""+score,600,445 );
			}
		}
		
		
		
		int game = 600;//游戏进度变量
		int time = 10;//定时器执行时间
		int random;//游戏变量
		//游戏定时计划
		public void action(User user) 
		{
			/*Hero h = new Hero(1);
			hero = Arrays.copyOf(hero,hero.length+1);
			hero[hero.length-1] = h;
			h = new Hero(2);
			hero = Arrays.copyOf(hero,hero.length+1);
			hero[hero.length-1] = h;*/
			
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				public void run() {
					if(state == RUNNING) {
						allMove();//自定义方法
						outOfBounds();
						Bang();
						victory();
						defeat() ;
						repaint();//重新调用paint()方法
					}
					if(state==CHOOSS) {
						repaint();
					}
				}
			}, time, time);
			
			//英雄子弹计划
			timer.schedule(new TimerTask() {
				public void run() {
					if(state == RUNNING) {
						heroshoot();
						herocd();
					}
				}
			}, time, 40*time);
			
			//游戏进度计划
			timer.schedule(new TimerTask() {
				public void run() {
					if(state == RUNNING && game<3000) {
						if(game%200==0)
							beego();
						/* if(game%1000==0) {
						    	Enemy3 em3 = new Enemy3();
								enemy3 = Arrays.copyOf(enemy3,enemy3.length+1);
								enemy3[enemy3.length-1] = em3;
								//enemy3shoot();
						    }*/
						switch (game%200) {
						case 0:
							Enemy1 enemy1_1 = new Enemy1(0,0,0);
							Enemy1 enemy1_2 = new Enemy1(1,0,0);
							enemy1 = Arrays.copyOf(enemy1,enemy1.length+2);
							enemy1[enemy1.length-2]=enemy1_1;
							enemy1[enemy1.length-1]=enemy1_2;
							break;
						case 50:
							Aerolite aerolite1_1=new Aerolite(0, (int)(Math.random()*4)*200, 0);
							Aerolite aerolite1_2=new Aerolite(1, (int)(Math.random()*4)*200, 0);
							aerolite=Arrays.copyOf(aerolite, aerolite.length+2);
							aerolite[aerolite.length-2]=aerolite1_1;
							aerolite[aerolite.length-1]=aerolite1_2;
							break;
						case 70:
							enemyshoot();
							int random = SCREEN_WIDTH-300*(1+(int)(Math.random()*4));
							Enemy2 enemy2_1 = new Enemy2(1,(random),100);
							Enemy2 enemy2_2 = new Enemy2(1,(random+55),50);
							Enemy2 enemy2_3 = new Enemy2(1,(random+110),0);
							Enemy2 enemy2_4 = new Enemy2(1,(random+165),50);
							Enemy2 enemy2_5 = new Enemy2(1,(random+220),100);
							enemy2 = Arrays.copyOf(enemy2,enemy2.length+5);
							enemy2[enemy2.length-1]=enemy2_1;
							enemy2[enemy2.length-2]=enemy2_2;
							enemy2[enemy2.length-3]=enemy2_3;
							enemy2[enemy2.length-4]=enemy2_4;
							enemy2[enemy2.length-5]=enemy2_5;
							break;
						case 100:
							enemyshoot();
							break;
						default:
							break;
						}
						game++;
					}
					else{
						if(state == RUNNING) {
							if(game%200==0)
								beego();
							if(game%100==0)
								random = (int)(Math.random()*3)+1;
								
							if(game == 3200) {
								Boss bo = new Boss();
								boss = Arrays.copyOf(boss,boss.length+1);
								boss[boss.length-1] = bo;
								
							
							}
							switch (game%100) {
							case 10:
								bossshoot(random);
								break;
							case 15:
								bossshoot(random);
								break;
							case 20:
								bossshoot(random);
								break;
							case 25:
								bossshoot(random);
								break;
								
							default:
								break;
							}
							game++;
						}
					}
				}
			}, 50*time, 5*time);
		};
		
		public void keyTyped(KeyEvent e) {}
		
		/*public void keyPressed (KeyEvent e) {
			int key = e.getKeyCode();
			switch(key) {
			case KeyEvent.VK_UP :
				if(state== RUNNING)
						hero[hero.length-1].setVy(-hero[hero.length-1].speed);
				if(state==START)
				{
				//	focusIndex = (focusIndex + button.length - 1) % button.length;
					focusIndexX = (focusIndexX + button.length - 1) % button.length;
					this.repaint();
				}
				break;
			case KeyEvent.VK_DOWN :
				if(state== RUNNING)
						hero[hero.length-1].setVy(hero[hero.length-1].speed);
				if(state==START)
				{
				//	focusIndex = (focusIndex + 1) % button.length;     
					focusIndexX = (focusIndexX + 1) % button.length;     
					this.repaint();
				}
				break;
			case KeyEvent.VK_LEFT :
				if(state== RUNNING)
						hero[hero.length-1].setVx(-hero[hero.length-1].speed);
				if(state==CHOOSS)
				{
					focusIndexY = (focusIndexY + button2.length - 1) % button2.length;
					this.repaint();
				}
				break;
			case KeyEvent.VK_RIGHT :
				if(state== RUNNING)
						hero[hero.length-1].setVx(hero[hero.length-1].speed);
				if(state==CHOOSS)
				{
					focusIndexY = (focusIndexY + 1) % button2.length;     
					this.repaint();
				}

				break;
			
			case KeyEvent.VK_K :
				if(state== RUNNING) {
					if(hero[hero.length-1].type == 2 && hero[hero.length-1].isLife()&&hero[hero.length-1].cd<=0) {
						rs = new Redskill(hero[hero.length-1]);
						hero[hero.length-1].CD();
			Music1 a =new Music1();			
				a.Music4();
						
					}
					else if(hero[hero.length-1].type == 1 && hero[hero.length-1].isLife()&&hero[hero.length-1].cd<=0){
						bs = new Blueskill(hero[hero.length-1]);
						hero[hero.length-1].CD();
					Music1 a =new Music1();			
					a.Music4();
					}
					
				}
				break;
			case KeyEvent.VK_J :
				if(state== RUNNING && hero.length == 2) {
					if(hero[0].type == 2 && hero[0].isLife()&&hero[0].cd<=0) {
						rs = new Redskill(hero[0]);
						hero[0].CD();
//						Music1 a =new Music1();			
//						a.Music5();
						
					}
					else if(hero[0].type == 1 && hero[0].isLife()&&hero[0].cd<=0){
						bs = new Blueskill(hero[0]);
						hero[0].CD();
//						Music1 a =new Music1();			
//						a.Music5();
					}
					
				}
				break;
			case KeyEvent.VK_W :
				if(state== RUNNING && hero.length == 2)
				hero[0].setVy(-hero[0].speed);
				break;
			case KeyEvent.VK_S :
				if(state== RUNNING && hero.length == 2)
				hero[0].setVy(hero[0].speed);
				break;
			case KeyEvent.VK_A :
				if(state== RUNNING && hero.length == 2)
				hero[0].setVx(-hero[0].speed);
				break;
			case KeyEvent.VK_D :
				if(state== RUNNING && hero.length == 2)
				hero[0].setVx(hero[0].speed);
				break;
			case KeyEvent.VK_SPACE :
				switch (state) {
				case START:
					switch(focusIndexX) {
					case 0 :
						state = RUNNING;
						break;
					case 1 :
						break;
					case 2 :
						break;
					case 3 :
						break;
					}
					break;
				case RUNNING :
					state = PAUSE;
					repaint();
					break;
				case PAUSE :
					state = RUNNING;
					break;
				case OVER:
					score = 0;
					background = new Background();
					hero = new Hero[0];
					enemy1 = new Enemy1[0];
					enemy2 = new Enemy2[0];
					herobullets = new HeroBullet[0];
					state = START;//修改状态为开始状态
					break;
				}
				break;
			}
		}
		*/
		public void keyPressed (KeyEvent e) {
			int key = e.getKeyCode();
			switch(key) {
			case KeyEvent.VK_UP :
				if(state== RUNNING)
						hero[hero.length-1].setVy(-hero[hero.length-1].speed);
				if(state==START)
				{
					focusIndexX = (focusIndexX + button.length - 1) % button.length;
					this.repaint();
				}
				break;
			case KeyEvent.VK_DOWN :
				if(state== RUNNING)
						hero[hero.length-1].setVy(hero[hero.length-1].speed);
				if(state==START)
				{
					focusIndexX = (focusIndexX + 1) % button.length;     
					this.repaint();
				}
				break;
			case KeyEvent.VK_LEFT :
				if(state== RUNNING)
						hero[hero.length-1].setVx(-hero[hero.length-1].speed);
				if(state==CHOOSS)
				{
					focusIndexY = (focusIndexY + button2.length - 1) % button2.length;
					this.repaint();
				}
				break;
			case KeyEvent.VK_RIGHT :
				if(state== RUNNING)
						hero[hero.length-1].setVx(hero[hero.length-1].speed);
				if(state==CHOOSS)
				{
					focusIndexY = (focusIndexY + 1) % button2.length;     
					this.repaint();
				}
				break;
			
			case KeyEvent.VK_J :
				if(state== RUNNING) {
					if(hero[0].type == 2 && hero[0].isLife()&&hero[0].cd<=0) {
						rs = new Redskill(hero[0]);
						hero[0].CD();
			            Music1 a =new Music1();			
				        a.Music4();
						
					}
					else if(hero[0].type == 1 && hero[0].isLife()&&hero[0].cd<=0){
						bs = new Blueskill(hero[0]);
						hero[0].CD();
					    Music1 a =new Music1();			
					    a.Music5();
					}
					
				}
				break;
			case KeyEvent.VK_NUMPAD0 :
				if(state== RUNNING && hero.length == 2) {
					if(hero[hero.length-1].type == 2 && hero[hero.length-1].isLife()&&hero[hero.length-1].cd<=0) {
						rs = new Redskill(hero[hero.length-1]);
						hero[hero.length-1].CD();
						Music1 a =new Music1();			
						a.Music4();
						
					}
					else if(hero[hero.length-1].type == 1 && hero[hero.length-1].isLife()&&hero[hero.length-1].cd<=0){
						bs = new Blueskill(hero[hero.length-1]);
						hero[hero.length-1].CD();
						Music1 a =new Music1();			
						a.Music5();
					}
					
				}
				break;
			case KeyEvent.VK_W :
				if(state== RUNNING && hero.length == 2)
				hero[0].setVy(-hero[0].speed);
				break;
			case KeyEvent.VK_S :
				if(state== RUNNING && hero.length == 2)
				hero[0].setVy(hero[0].speed);
				break;
			case KeyEvent.VK_A :
				if(state== RUNNING && hero.length == 2)
				hero[0].setVx(-hero[0].speed);
				break;
			case KeyEvent.VK_D :
				if(state== RUNNING && hero.length == 2)
				hero[0].setVx(hero[0].speed);
				break;
			case KeyEvent.VK_SPACE :
				switch (state) {
				case START:
					switch(focusIndexX) {
					case 0 :
						state = CHOOSS;
						Music1 a =new Music1();			
						a.Music6();
						break;
					case 1 :
							Hero h = new Hero(1);
							hero = Arrays.copyOf(hero,hero.length+1);
							hero[hero.length-1] = h;
							h = new Hero(2);
							hero = Arrays.copyOf(hero,hero.length+1);
							hero[hero.length-1] = h;
							Music1 c =new Music1();			
							c.Music6();
						state = RUNNING;
						break;
					case 2 :
						break;
					case 3 :
						break;
					}
					break;
				case CHOOSS :
					switch(focusIndexY) {
					case 0 :
							Hero h = new Hero(2);
							hero = Arrays.copyOf(hero,hero.length+1);
							hero[hero.length-1] = h;
							Music1 a =new Music1();			
							a.Music6();
						state = RUNNING;
						break;
					case 1 :
							Hero h1 = new Hero(1);
							hero = Arrays.copyOf(hero,hero.length+1);
							hero[hero.length-1] = h1;
							Music1 c =new Music1();			
							c.Music6();
							state= RUNNING;
						break;
					}
					break;
				case RUNNING :
					state = PAUSE;
					repaint();
					break;
				case PAUSE :
					state = RUNNING;
					break;
				case OVER:
					score = 0;
					background = new Background();
					hero = new Hero[0];
					enemy1 = new Enemy1[0];
					enemy2 = new Enemy2[0];
					herobullets = new HeroBullet[0];
					state = START;//修改状态为开始状态
					this.repaint();
					break;
				}
				break;
			}
		}
		/*public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			switch(key) {
			case KeyEvent.VK_UP :
				hero[hero.length-1].setVy(0);
				break;
			case KeyEvent.VK_DOWN :
				hero[hero.length-1].setVy(0);
				break;
			case KeyEvent.VK_LEFT :
				hero[hero.length-1].setVx(0);
				break;
			case KeyEvent.VK_RIGHT :
				hero[hero.length-1].setVx(0);
				break;
			case KeyEvent.VK_W :
				if(hero.length == 2)
				hero[0].setVy(0);
				break;
			case KeyEvent.VK_S :
				if(hero.length == 2)
				hero[0].setVy(0);
				break;
			case KeyEvent.VK_A :
				if(hero.length == 2)
				hero[0].setVx(0);
				break;
			case KeyEvent.VK_D :
				if(hero.length == 2)
				hero[0].setVx(0);
				break;
			}
		}*/
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			switch(key) {
			case KeyEvent.VK_UP :
				if(hero.length>0)
				hero[hero.length-1].setVy(0);
				break;
			case KeyEvent.VK_DOWN :
				if(hero.length>0)
				hero[hero.length-1].setVy(0);
				break;
			case KeyEvent.VK_LEFT :
				if(hero.length>0)
				hero[hero.length-1].setVx(0);
				break;
			case KeyEvent.VK_RIGHT :
				if(hero.length>0)
				hero[hero.length-1].setVx(0);
				break;
			case KeyEvent.VK_W :
				if(hero.length == 2)
				hero[0].setVy(0);
				break;
			case KeyEvent.VK_S :
				if(hero.length == 2)
				hero[0].setVy(0);
				break;
			case KeyEvent.VK_A :
				if(hero.length == 2)
				hero[0].setVx(0);
				break;
			case KeyEvent.VK_D :
				if(hero.length == 2)
				hero[0].setVx(0);
				break;
			}
		}
		
		
public GameMain(User user) {
		//GameMain Game = new GameMain();             //（父类为JPanel）创建面板
		JFrame Frame = new JFrame(user.getUsername());				//创建图形窗口
		Frame.add(this);  							//将画布添加到窗口里
		Frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);	//设置窗口的宽度高度
		
		new Thread(new Runnable() {   //

			  public void run() {

			   while (true) {

			     Music.playMusic();

			   }

			  }

			  }).start();
		
		
		Frame.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
		//点击窗口右上角关闭，四种关闭方式： 
		//1.this.setDefaultCloseOperation(0);// DO_NOTHING_ON_CLOSE，不执行任何操作。 
		//2.this.setDefaultCloseOperation(1);//HIDE_ON_CLOSE，只隐藏界面，setVisible(false)。 
		//3.this.setDefaultCloseOperation(2);//DISPOSE_ON_CLOSE,隐藏并释放窗体，dispose()，当最后一个窗口被释放后，则程序也随之运行结束。 
		//4.this.setDefaultCloseOperation(3);//EXIT_ON_CLOSE,直接关闭应用程序，System.exit(0)。一个main函数对应一整个程序。
		
		try {
			// ImageIO.read(this.getClass().getResource("/image/schoolbadge.jpg"));
			Image img = ImageIO.read(this.getClass().getResource("/image/schoolbadge.jpg"));
			Frame.setIconImage(img);

		} catch (IOException e) {

			e.printStackTrace();
		}
		Frame.setLocationRelativeTo(null);  //设置窗口居中
		Frame.setVisible(true);    
		//setVisible(boolean)方法是用来显示/隐藏一个GUI组件的。要把setVisible方法放到最后面。
		Frame.addKeyListener(this);
		this.action(user);
	}
}
