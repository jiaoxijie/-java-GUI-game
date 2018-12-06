//Java RPG 游戏开发中地图的初步构建
//文件：Example1.Java

import java.awt.Container;
import java.util.Random;
import javax.swing.JFrame;

public class Example1 extends JFrame {
	public static Character [] Role = new Character[10];
	
public Example1() {
	Random rand = new Random();
	int i, k;
	for(i = 0; i < 10; i++)
	{
		k = rand.nextInt(1) + 1;
		if(k == 1)
			Role[i] = new typeone();
		else 
			Role[i] = new typetwo();
		Role[i].Name = (char)(i + 65);
	}
	// 默认的窗体名称
	setTitle("Example1[Java 游戏中地图的描绘]");
	// 获得我们自定义面板[地图面板]的实例
	MyPanel panel = new MyPanel();
	Container contentPane = getContentPane();
	contentPane.add(panel);
	// 执行并构建窗体设定
	pack();
}

public static void main(String[] args) {
	Random rand = new Random();
	//int i, k;
	Example1 e1 = new Example1();
	// 设定允许窗体关闭操作
	e1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// 显示窗体
	e1.setVisible(true);
	}
}