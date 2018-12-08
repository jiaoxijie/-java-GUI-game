import java.awt.Container;
import java.util.Random;
import javax.swing.JFrame;
import java.lang.Thread;

public class Example1 extends JFrame {
	public static Character [] Role = new Character[10];
	public static int step;
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
	setTitle("王者荣耀游戏");
	// 获得我们自定义面板[地图面板]的实例
	MyPanel panel = new MyPanel();
	Container contentPane = getContentPane();
	contentPane.add(panel);
	// 执行并构建窗体设定
	pack();
}

private static void createAndShowHero() {
	JFrame frame = new JFrame("Now Choose Your Hero!");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	chooseHero pane = new chooseHero();
	frame.add(pane);
	frame.setSize(500, 400);
	frame.setVisible(true);
}

static void createAndShowMap() {
	JFrame frame = new JFrame("Now Choose the Map!");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	chooseMap pane = new chooseMap();
	frame.add(pane);
	frame.setSize(500, 400);
	frame.setVisible(true);
}

public static void main(String[] args) throws InterruptedException{
	Random rand = new Random();
	createAndShowHero();
	/*javax.swing.SwingUtilities.invokeLater(new Runnable(){
		public void run() {
			createAndShowHero();
			createAndShowMap();
		}
	});
	javax.swing.SwingUtilities.invokeLater(new Runnable(){
		public void run() {
			createAndShowMap();
		}
	});*/
	//Example1 e1 = new Example1();
	//e1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//e1.setVisible(true);
	}
}


