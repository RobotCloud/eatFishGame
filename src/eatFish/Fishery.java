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
 * @author ����
 * @���䣺2602341707@qq.com
 * @�޸����ڣ�2019.10.18
 * @�����������С����桢�ƶ���������ʼ������
 *
 */
public class Fishery extends JPanel {

	// ��������
	ArrayList<FishData> otherFishData = new ArrayList<FishData>();
	// �����ҵĴ���
	FishData myFishData = new FishData();
	//���Լ�����,ͷ�����ͷ����
	BufferedImage myFishImgLeft;
	BufferedImage myFishImgRight;
	//��������
	BufferedImage buleFishImgLeft;
	BufferedImage buleFishImgRight;
	//�����泡ͼƬ
	BufferedImage fisheryImg;
	//��������,Ĭ�ϲ��ɷ���
	boolean rebound = true;
	boolean leftMove;
	boolean rightMove;
	//��ʼΪtemp��ֵ�������ƶ�֮ǰ����һ��λ������
	boolean move;
	int tempX;
	
	
	public Fishery() {
		fishData();
		//��ʼĬ������
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
		//�泡
		g.drawImage(fisheryImg, 0, 0, 1200, 800, null);
		for (int i = 0;i < otherFishData.size();i++) {
			//�ڼ�����ȡԪ�ط��� otherFish 
			FishData otherFish  = otherFishData.get(i);
			//������
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
		//���Լ�����
		if (leftMove) {
			g.drawImage(myFishImgLeft, myFishData.fishX, myFishData.fishY, myFishData.fishSize, myFishData.fishSize, null);
		}
		if (rightMove) {
			g.drawImage(myFishImgRight, myFishData.fishX, myFishData.fishY, myFishData.fishSize, myFishData.fishSize, null);
		}
	}
	/**
	 * 
	 * ��������ʼ��������
	 */
	public void fishData() {	
		//�����ܶ����㣬����ӵ�������
		for (int i =0;i <=10;i++) {
			FishData otherFish = new FishData();
			otherFishData.add(otherFish);
		}
		//��ʼ�̶��������Լ���Ĵ�С
		myFishData.fishSize = 60;
	}
	/**
	 * 
	 * �������������һ����
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
	 * ���������¿�ʼ
	 */
	public void restart() {
		//������������ȫ��ɾ��
		otherFishData.clear();
		//���²���������
		fishData();
	}
	public void action () {
		//����ƶ��¼�
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
				//��������
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
		//��Ҫ��Ӽ����¼��������ȡ����
		requestFocus();
		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				int keyCode = e.getKeyCode();
				//����
				if (keyCode == KeyEvent.VK_LEFT) {
					myFishData.fishX -= 10;
					leftMove = true;
					rightMove = false;
				}
				//����
				if (keyCode == KeyEvent.VK_RIGHT) {
					myFishData.fishX += 10;
					leftMove = false;
					rightMove = true;
				}
				//����
				if (keyCode == KeyEvent.VK_UP) {
					myFishData.fishY -= 10;
				}
				//����
				if (keyCode == KeyEvent.VK_DOWN) {
					myFishData.fishY += 10;
				}
			}
			
		});
		int times = 0;
		while (true) {
			for (int i = 0;i < otherFishData.size() ;i++) {
				FishData fish = otherFishData.get(i);
				//����
				if (fish.dir == 1) {
					fish.fishX -= fish.fishSpeed;
					// ����
					if (rebound) {
						if(fish.fishX == 0) {
							fish.fishSpeed *= -1; 
						}
						if (fish.fishY == 1200-fish.fishSize) {
							fish.dir = 2;
						}
					}
					//Ĭ�ϴ�ǽ
					if(fish.fishX == 0-fish.fishSize) {
						fish.fishX = 1200;
					}
				}
				//����
				if (fish.dir == 2) {
					fish.fishX += fish.fishSpeed;
					if (rebound) {
						if (fish.fishX == 1200-fish.fishSize) {
							fish.fishSpeed *= -1; // ����
						}
						if(fish.fishX == 0) {
							fish.dir = 1;
						}
					}
					if (fish.fishX == 1200) {
						fish.fishX = 0 - fish.fishSize;
					}
				}
				//����
				if (fish.dir == 3) {
					fish.fishY -= fish.fishSpeed;
					if (rebound) {
						if(fish.fishY == 0) {
							fish.fishSpeed *= -1; // ����
						}
						if (fish.fishY == 800-fish.fishSize) {
							fish.dir = 4;
						}
					}
					if (fish.fishY == 0 - fish.fishSize) {
						fish.fishY = 800;
					}
				}
				//����
				if (fish.dir == 4) {
					fish.fishY += fish.fishSpeed;
					if(rebound) {
						if (fish.fishY == 800-fish.fishSize) {
							fish.fishSpeed *= -1; // ����
						}
						if(fish.fishY == 0) {
							fish.dir = 3;
						}
					}
					if (fish.fishY == 800) {
						fish.fishY = 0 - fish.fishSize;
					}
				}
				//������
				if (fish.dir == 5) {
					fish.fishX -= fish.fishSpeed;
					fish.fishY -= fish.fishSpeed;
				}
				//������
				if (fish.dir == 6) {
					fish.fishX += fish.fishSpeed;
					fish.fishY -= fish.fishSpeed;
				}
				//������
				if (fish.dir == 7) {
					fish.fishX -= fish.fishSpeed;
					fish.fishY += fish.fishSpeed;
				}
				//������
				if (fish.dir == 8) {
					fish.fishX += fish.fishSpeed;
					fish.fishY += fish.fishSpeed;
				}
				//�Լ��������������ײ�¼�
				if (myFishData.fishX - fish.fishX >= -myFishData.fishSize +28
						&& myFishData.fishX - fish.fishX <= fish.fishSize -28
						&& myFishData.fishY - fish.fishY >= -myFishData.fishSize +28
						&& myFishData.fishY - fish.fishY <= fish.fishSize - 28) {
					
					//���㣬ÿ��һ���������㣬�Լ���������10
					if (myFishData.fishSize <= 250 && myFishData.fishSize >= fish.fishSize) {
						myFishData.fishSize += 10;
						//�����Ե��������ݣ��Ӽ�����ɾ��
						otherFishData.remove(i);
					} 
					if (myFishData.fishSize > 250) {
						int result = JOptionPane.showConfirmDialog(null, "��ϲ������Ϸʤ��������һ�֣�", "��Ϸ���",
								JOptionPane.YES_NO_OPTION);
						if (result == 0) {// ����һ�䣬ˢ������
							restart();
							repaint();
						} else {// �˳�
							System.exit(0);
						}
					}
					//�ҵ��㱻�ԣ���Ϸʧ��
					if (myFishData.fishSize < fish.fishSize) {
						int result = JOptionPane.showConfirmDialog(null, "���ź�����Ϸʧ�ܣ�����һ�֣�", "��Ϸ���",
								JOptionPane.YES_NO_OPTION);
						if (result == 0) {// ����һ�䣬ˢ������
							restart();
							repaint();
						} else {// �˳�
							System.exit(0);
						}
					}
					
				}
				
			}
			//ÿ2�����һ����
			if (times % 2000 == 0 && times != 0) {
				randomCreateFish();
			}
			
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			times += 10;
		}
	}
}
