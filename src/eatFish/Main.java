package eatFish;

import javax.swing.JFrame;

/**
 * 
 * @author 花少
 * @邮箱：2602341707@qq.com
 * @修改日期：2019.10.18
 * @描述：大鱼吃小鱼小游戏
 *
 */
public class Main{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame jf = new JFrame();
		jf.setBounds(300, 100, 1200, 800);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Fishery mp = new Fishery();
		jf.add(mp);
		jf.setVisible(true);
		mp.action();
	}

}