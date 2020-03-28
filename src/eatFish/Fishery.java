package eatFish;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * @author 花少
 * @邮箱：2602341707@qq.com
 * @修改日期：2019.10.18
 * @描述：大鱼吃小鱼界面、移动方法、初始化数据
 *
 */
public class Fishery extends JPanel {

	// 创建集合
	ArrayList<FishData> otherFishData = new ArrayList<FishData>();
	// 创建我的大鱼
	FishData myFishData = new FishData();
	//我自己的鱼,头向左和头向右
	BufferedImage myFishImgLeft;
	BufferedImage myFishImgRight;
	//其它的鱼
	BufferedImage buleFishImgLeft;
	BufferedImage buleFishImgRight;
	//背景渔场图片
	BufferedImage fisheryImg;
	//反弹控制,默认不可反弹
	boolean rebound = true;
	boolean leftMove;
	boolean rightMove;
	//初始为temp赋值，保留移动之前的上一个位置坐标
	boolean move;
	int tempX;
	
	
	public Fishery() {
		fishData();
		//初始默认向左
		leftMove = true;
		rightMove = false;
		move = true;
		this.setBackground(Color.DARK_GRAY);
		try {
			buleFishImgLeft = ImageIO.read(getClass().getResource("buleFishLeft.png"));
			buleFishImgRight = ImageIO.read(getClass().getResource("buleFishRight.png"));
			myFishImgLeft = ImageIO.read(getClass().getResource("myFishLeft.png"));
			myFishImgRight = ImageIO.read(getClass().getResource("myFishRight.png"));
			fisheryImg = ImageIO.read(getClass().getResource("fishery.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		//渔场
		g.drawImage(fisheryImg, 0, 0, 1200, 800, null);
		for (int i = 0;i < otherFishData.size();i++) {
			//在集合中取元素放入 otherFish 
			FishData otherFish  = otherFishData.get(i);
			//其它鱼
			if (otherFish.dir == 1) {
				g.drawImage(buleFishImgLeft, otherFish.fishX, otherFish.fishY, otherFish.fishSize, otherFish.fishSize, null);
			}
			else if (otherFish.dir == 2) {
				g.drawImage(buleFishImgRight, otherFish.fishX, otherFish.fishY, otherFish.fishSize, otherFish.fishSize, null);
			}
			else {
				g.drawImage(buleFishImgRight, otherFish.fishX, otherFish.fishY, otherFish.fishSize, otherFish.fishSize, null);
			}
		}
		//我自己的鱼
		if (leftMove) {
			g.drawImage(myFishImgLeft, myFishData.fishX, myFishData.fishY, myFishData.fishSize, myFishData.fishSize, null);
		}
		if (rightMove) {
			g.drawImage(myFishImgRight, myFishData.fishX, myFishData.fishY, myFishData.fishSize, myFishData.fishSize, null);
		}
	}
	/**
	 * 
	 * 描述：初始化鱼数据
	 */
	public void fishData() {	
		//创建很多条鱼，并添加到集合中
		for (int i =0;i <=10;i++) {
			FishData otherFish = new FishData();
			otherFishData.add(otherFish);
		}
		//初始固定设置我自己鱼的大小
		myFishData.fishSize = 60;
	}
	/**
	 * 
	 * 描述：随机产生一条鱼
	 */
	public void randomCreateFish() {
		FishData randomFish = new FishData();
		randomFish.fishSize = (int)(Math.random()*200 + 30);
		if (randomFish.dir == 1) {
			randomFish.fishX = 1200;
		}
		if (randomFish.dir == 2) {
			randomFish.fishX = 0 - randomFish.fishSize;
		}
		if (randomFish.dir == 3) {
			randomFish.fishY = 800;
		}
		if (randomFish.fishY == 4) {
			randomFish.fishY = 0 - randomFish.fishSize;
		}
		
		otherFishData.add(randomFish);
	}
	/**
	 * 
	 * 描述：重新开始
	 */
	public void restart() {
		//将集合中数据全部删除
		otherFishData.clear();
		//重新产生新数据
		fishData();
	}
	public void action () {
		//鼠标移动事件
		this.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				if (move) {
					tempX = e.getX();
					move = false;
				}
				myFishData.fishX = e.getX();
				myFishData.fishY = e.getY();
				//方向向左
				if (myFishData.fishX < tempX) {
					leftMove = true;
					rightMove = false;
				}
				if (myFishData.fishX > tempX) {
					leftMove = false;
					rightMove = true;
				}
				tempX = myFishData.fishX;
				
				repaint();
			}
		});
		//若要添加键盘事件，则需获取焦点
		requestFocus();
		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				int keyCode = e.getKeyCode();
				//向左
				if (keyCode == KeyEvent.VK_LEFT) {
					myFishData.fishX -= 10;
					leftMove = true;
					rightMove = false;
				}
				//向右
				if (keyCode == KeyEvent.VK_RIGHT) {
					myFishData.fishX += 10;
					leftMove = false;
					rightMove = true;
				}
				//向上
				if (keyCode == KeyEvent.VK_UP) {
					myFishData.fishY -= 10;
				}
				//向下
				if (keyCode == KeyEvent.VK_DOWN) {
					myFishData.fishY += 10;
				}
			}
			
		});
		int times = 0;
		while (true) {
			for (int i = 0;i < otherFishData.size() ;i++) {
				FishData fish = otherFishData.get(i);
				//向左
				if (fish.dir == 1) {
					fish.fishX -= fish.fishSpeed;
					// 反弹
					if (rebound) {
						if(fish.fishX == 0) {
							fish.fishSpeed *= -1; 
						}
						if (fish.fishY == 1200-fish.fishSize) {
							fish.dir = 2;
						}
					}
					//默认穿墙
					if(fish.fishX == 0-fish.fishSize) {
						fish.fishX = 1200;
					}
				}
				//向右
				if (fish.dir == 2) {
					fish.fishX += fish.fishSpeed;
					if (rebound) {
						if (fish.fishX == 1200-fish.fishSize) {
							fish.fishSpeed *= -1; // 反弹
						}
						if(fish.fishX == 0) {
							fish.dir = 1;
						}
					}
					if (fish.fishX == 1200) {
						fish.fishX = 0 - fish.fishSize;
					}
				}
				//向上
				if (fish.dir == 3) {
					fish.fishY -= fish.fishSpeed;
					if (rebound) {
						if(fish.fishY == 0) {
							fish.fishSpeed *= -1; // 反弹
						}
						if (fish.fishY == 800-fish.fishSize) {
							fish.dir = 4;
						}
					}
					if (fish.fishY == 0 - fish.fishSize) {
						fish.fishY = 800;
					}
				}
				//向下
				if (fish.dir == 4) {
					fish.fishY += fish.fishSpeed;
					if(rebound) {
						if (fish.fishY == 800-fish.fishSize) {
							fish.fishSpeed *= -1; // 反弹
						}
						if(fish.fishY == 0) {
							fish.dir = 3;
						}
					}
					if (fish.fishY == 800) {
						fish.fishY = 0 - fish.fishSize;
					}
				}
				//向左上
				if (fish.dir == 5) {
					fish.fishX -= fish.fishSpeed;
					fish.fishY -= fish.fishSpeed;
				}
				//向右上
				if (fish.dir == 6) {
					fish.fishX += fish.fishSpeed;
					fish.fishY -= fish.fishSpeed;
				}
				//向左下
				if (fish.dir == 7) {
					fish.fishX -= fish.fishSpeed;
					fish.fishY += fish.fishSpeed;
				}
				//向右下
				if (fish.dir == 8) {
					fish.fishX += fish.fishSpeed;
					fish.fishY += fish.fishSpeed;
				}
				//自己的鱼和其它鱼相撞事件
				if (myFishData.fishX - fish.fishX >= -myFishData.fishSize +28
						&& myFishData.fishX - fish.fishX <= fish.fishSize -28
						&& myFishData.fishY - fish.fishY >= -myFishData.fishSize +28
						&& myFishData.fishY - fish.fishY <= fish.fishSize - 28) {
					
					//吃鱼，每吃一条其它的鱼，自己的鱼增大10
					if (myFishData.fishSize <= 250 && myFishData.fishSize >= fish.fishSize) {
						myFishData.fishSize += 10;
						//将被吃掉的鱼数据，从集合中删掉
						otherFishData.remove(i);
					} 
					if (myFishData.fishSize > 250) {
						int result = JOptionPane.showConfirmDialog(null, "恭喜您，游戏胜利，再来一局？", "游戏结果",
								JOptionPane.YES_NO_OPTION);
						if (result == 0) {// 再来一句，刷新数据
							restart();
							repaint();
						} else {// 退出
							System.exit(0);
						}
					}
					//我的鱼被吃，游戏失败
					if (myFishData.fishSize < fish.fishSize) {
						int result = JOptionPane.showConfirmDialog(null, "很遗憾，游戏失败，再来一局？", "游戏结果",
								JOptionPane.YES_NO_OPTION);
						if (result == 0) {// 再来一句，刷新数据
							restart();
							repaint();
						} else {// 退出
							System.exit(0);
						}
					}
					
				}
				
			}
			//每2秒产生一条鱼
			if (times % 2000 == 0 && times != 0) {
				randomCreateFish();
			}
			
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			times += 10;
		}
	}
}
