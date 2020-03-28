package eatFish;

import java.awt.Color;

/**
 * 
 * @author ����
 * @���䣺2602341707@qq.com
 * @�޸����ڣ�2019.10.18
 * @����������һ�����࣬����һЩ��������
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
	 * ��ʼ��С��Ļ�����Ϣ
	 */
	public FishData() {
		this.fishSize = (int)(Math.random() * 64 + 32);
		this.fishX = (int)(Math.random() * (1200 - fishSize) + 1);
		this.fishY = (int)(Math.random() * (800 - fishSize) + 1);
		this.fishSpeed = 1;
		this.dir = (int)(Math.random()*2 + 1); //1���� 2���� 3���� 4����
		int red = (int)(Math.random()*255);
		int green = (int)(Math.random()*255);
		int blue = (int)(Math.random()*255);
		this.color = new Color(red, green, blue);
	}
}
