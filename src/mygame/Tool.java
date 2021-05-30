package mygame;

import java.awt.image.BufferedImage;		
import java.io.File;						//文件的类
import java.io.IOException;					//判断错误的类
import javax.imageio.ImageIO;				//导入图片文件的类

public class Tool {																//工具包类
	
//1,获取图片工具
		public static BufferedImage getImage(String ImageName/*图片名字*/) {
			try {								//判断导入图片是否异常
				BufferedImage image = ImageIO.read(Tool.class.getResource("/image/"+ImageName));
				return image;
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
//				e.printStackTrace();			//打印异常栈信息
				throw new RuntimeException();	//抛出异常结束程序
			}
		}
	
//2,检测碰撞
		public static boolean hit(FlyingObject one,FlyingObject other){
			int x1 = one.x - other.width;
			int x2 = one.x + one.width;
			int y1 = one.y - other.height;
			int y2 = one.y + one.height;
			int x = other.x;
			int y = other.y;
			return x >= x1 && x <= x2 && y >= y1 && y <= y2;
		}

}
