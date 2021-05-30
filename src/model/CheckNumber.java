package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;


public class CheckNumber {
	
	// 验证码字符集
	private static final char[] chars = ("0123456789abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ")
				.toCharArray();
	// 字符数量
	private static final int SIZE = 4;
	// 干扰线数量
	private static final int LINES = 10;
	// 宽度
	private static final int WIDTH = 80;
	// 高度
	private static final int HEIGHT = 40;
	// 字体大小
	private static final int FONT_SIZE = 30;// 30
	
	public BufferedImage checkImage;
	public String  checknumber;
	public CheckNumber() {
		super();
		createImage();
		// TODO 自动生成的构造函数存根
	}
	public CheckNumber(BufferedImage checkImage, String checknumber) {
		super();
		this.checkImage = checkImage;
		this.checknumber = checknumber;
	}
	public BufferedImage getCheckImage() {
		return checkImage;
	}
	public void setCheckImage(BufferedImage checkImage) {
		this.checkImage = checkImage;
	}
	public String getChecknumber() {
		return checknumber;
	}
	public void setChecknumber(String checknumber) {
		this.checknumber = checknumber;
	}
	
//	public static CheckNumber createImage() {
	public void createImage() {
		StringBuffer sb = new StringBuffer();
		// 1.创建空白图片
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		// 2.获取图片画笔
		Graphics graphic = image.getGraphics();
		// 3.设置画笔颜色
		graphic.setColor(Color.CYAN);
		// 4.绘制矩形背景
		graphic.fillRect(0, 0, WIDTH, HEIGHT);
		// 5.画随机字符
		Random ran = new Random();
		for (int i = 0; i < SIZE; i++) {
			// 取随机字符索引
			int n = ran.nextInt(chars.length);
			// 设置随机颜色
			graphic.setColor(getRandomColor());
			// 设置字体大小
			graphic.setFont(new Font(null, Font.BOLD + Font.ITALIC, FONT_SIZE));
			// 画字符
			graphic.drawString(chars[n] + "", i * WIDTH / SIZE, HEIGHT / 2 + 10);
			// 记录字符
			sb.append(chars[n]);
		}
		// 6.画干扰线
		for (int i = 0; i < LINES; i++) {
			// 设置随机颜色
			graphic.setColor(getRandomColor());
			// 随机画线
			graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT), ran.nextInt(WIDTH), ran.nextInt(HEIGHT));
		}
		// 7.返回验证码和图片
		// return new Object[]{sb.toString(),image};
		this.checkImage = image;
		this.checknumber = sb.toString();
//		return new CheckNumber(image, sb.toString());
	}

	/**
	 * 随机取色
	 */
	public static Color getRandomColor() {
		Random random = new Random();
		Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
		return color;
	}
}
