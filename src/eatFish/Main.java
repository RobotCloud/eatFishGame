package eatFish;

import javax.swing.JFrame;

/**
 * 
 * @author ����
 * @���䣺2602341707@qq.com
 * @�޸����ڣ�2019.10.18
 * @�����������С��С��Ϸ
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