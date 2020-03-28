package eatFish;

import java.awt.Color;

/**
 * 
 * @author 花少
 * @邮箱：2602341707@qq.com
 * @修改日期：2019.10.18
 * @描述：创建一个鱼类，包含一些基本属性
 *
 */
public class FishData {
	
	int fishX;
	int fishY;
	int dir;
	int fishSize;
	Color color;
	int fishSpeed;
	
	/**
	 * 初始化小鱼的基本信息
	 */
	public FishData() {
		this.fishSize = (int)(Math.random() * 64 + 32);
		this.fishX = (int)(Math.random() * (1200 - fishSize) + 1);
		this.fishY = (int)(Math.random() * (800 - fishSize) + 1);
		this.fishSpeed = 1;
		this.dir = (int)(Math.random()*2 + 1); //1向左 2向右 3向上 4向上
		int red = (int)(Math.random()*255);
		int green = (int)(Math.random()*255);
		int blue = (int)(Math.random()*255);
		this.color = new Color(red, green, blue);
	}
}
