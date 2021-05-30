package mygame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Background extends FlyingObject{
		
		public int y2;
		static BufferedImage image;
		static {
			image = Tool.getImage("background.png");
		}
		
		//背景的构造方法(图片宽度，高度，移动速度，图片文件名)
		public Background(){
			this.width = GameMain.SCREEN_WIDTH;
			this.height = GameMain.SCREEN_HEIGHT*3-1;
			this.speed = 1;
			y=0;
			y2 =-height;
		}
		
		//背景的移动方法
		public void Move() {				
   			y+=speed;						//第一张图片位置
			y2+=speed;						//第二张图片位置
			//两张图片移动到窗口下面会跳到窗口上边
			if(y>=height) {
				y = -height;
			}
			if(y2>=height) {
				y2 = -height;
			}
		}
		
		//画背景方法
		public void paintObject(Graphics g) {
			g.drawImage(image,0,y,null);
			g.drawImage(image,0,y2,null);
		}
}
