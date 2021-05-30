package mygame;

public class FlyingObject {
	//成员变量（飞行物属性）
	public int width;	//飞行物的宽度  
	public int height;	//飞行物的高度
	public int x;		//飞行物的x轴位置
	public int y;		//飞行物的y轴位置
	public int speed;	//飞行物的移动速度
	
	//成员状态
	public static final int LIFE = 0;	//存活
	public static final int DEAD = 1;	//死亡
	public static final int REMOVE = 2;	//移除
	public  int state = LIFE;			//默认状态是存活
	
	/** 判断当前状态是不是存活的 */
	public boolean isLife() {
		return state == LIFE;
	}

	/** 判断当前状态是不是over的 */
	public boolean isDead() {
		return state == DEAD;
	}

	/** 判断当前状态是不是删除的 */
	public boolean isRemove() {
		return state == REMOVE;
	}
	
	/** 飞行物over */
	public void goDead() {
		state = DEAD;// 将对象状态修改为DAED
	}
	
	public boolean outOfBound() {					//判断出界（+-100是为了可以让敌人飞机生成不同y轴位置）
		return 	this.x >= (GameMain.SCREEN_WIDTH +200)
			 || this.x < (-this.width -200)
			 || this.y >= (GameMain.SCREEN_HEIGHT)
			 || this.y < (-this.height -200);
	}
}
