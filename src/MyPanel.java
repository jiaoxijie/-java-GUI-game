import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

/* 
 * Example1 中自定义面板，用于描绘底层地图 。
 */ 
public class MyPanel extends JPanel implements KeyListener, MouseListener{

	private int Choice = 1;
	JLabel label2 = new JLabel("");
	static int i = 1;
	private static final long serialVersionUID = 1L;
	// 窗体的宽与高  
	  private static final int WIDTH = 480; 
	  private static final int HEIGHT = 480;
	   // 设定背景方格默认行数  
	  private static final int ROW = 15;
	   // 设定背景方格默认列数  9
	  private static final int COL = 15;
	  // 单个图像大小，我默认采用32x32 图形,可根据需要调整比例。
	  // 当时，始终应和窗体大小比例协调；比如32x32 的图片，如何 
	  //  一行设置15个，那么就是480, 也就是本例子默认的窗体大小， 
	  private static final int CS = 32;
	   // 设定地图
	  private int[][] map = {
	  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1}, 
	  {1,0,1,0,2,0,0,0,0,0,0,0,1,0,1},
	  {1,0,0,0,0,0,0,0,0,0,0,2,0,0,1}, 
	  {1,0,0,0,1,0,0,0,0,0,1,0,0,0,1},
	  {1,0,0,0,0,1,1,1,1,1,0,0,0,0,1}, 
	  {1,0,0,0,0,1,0,0,1,1,0,0,0,0,1},
	  {1,0,0,0,0,1,0,0,0,1,0,0,0,0,1}, 
	  {1,0,0,0,0,1,0,2,0,1,0,0,0,0,1},
	  {1,0,0,0,0,1,1,0,1,1,0,0,0,0,1}, 
	  {1,0,0,0,1,0,0,0,0,0,1,0,0,0,1},
	  {1,0,0,1,0,0,2,0,0,0,0,0,0,0,1}, 
	  {1,0,1,0,2,0,0,0,0,2,0,0,1,0,1},
	  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1}, 
	  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
	  
	//蜜蜂图像
	private Image beeImage;
    // 设定显示图像对象
	private Image floorImage;
	private Image wallImage;
	//新增了一个角色
	private Image roleImage;
	//角色坐标	
	private int x, y;
	//此处我们添加一组常数，用以区别左右上下按键的触发，
	//之所以采用数字进行区别，原因大家都很清楚^^，数字
	//运算效率高嘛~
	private static final int LEFT = 0;
	private static final int RIGHT = 1;
	private static final int UP = 2;
	private static final int DOWN = 3;
	//增加计步器
	private int count;
	private Thread otherHero;
	private Thread threadAnime;
	private int direction; //新增变量，用以确认角色所对方向,对应按键触发
	
	public MyPanel() {
		Random rand = new Random();
		Example1.Role[i].Hp = Example1.Role[i].Hp-10;
		// 设定初始构造时面板大小
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		// 于初始化时载入图形
		if(Choice == 0)
			loadImage();
		else
			loadImage1();
		x = 1; //角色起始坐标
		y = 1;
		direction=DOWN; 
		//默认为角色向下
		//设定焦点在本窗体并付与监听对象
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		
		//实例化内部线程uncontrol, 用来控制非玩家控制英雄。
		for(int i = 0; i < 9; i++) {
			int x = rand.nextInt(15);
			int y = rand.nextInt(15);
			otherHero = new Thread(new uncontrol(x, y));
		}
			
		//实例化内部线程AnimationThread
		for(int i = 0; i < 10; i++)
		{
			threadAnime = new Thread(new AnimationThread());
			//启动线程
			threadAnime.start();
		}
	}
	
	// 描绘窗体，此处在默认JPanel 基础上构建底层地图．
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// 画出地图
		drawMap(g);
		//画出人物
		drawRole(g);
	}
	
	private void drawRole(Graphics g) {
		//以count 作为图像的偏移数值
		g.drawImage(roleImage, x*CS, y*CS, x*CS+CS, y*CS+CS,
				count*CS, 0, CS+count*CS, CS, this);
	}

	// 载入图像
	private void loadImage() {
		// 获得当前类对应的相对位置image 文件夹下的地板图像	
		ImageIcon icon = new ImageIcon(getClass().getResource("image/草地.png"));
			//ImageIcon icon = new ImageIcon(getClass().getResource("image/000草地.png"));
		// 将地板图像实例付与floorImage
		floorImage = icon.getImage();
		// 获得当前类对应的相对位置image 文件夹下的墙体图像
		icon = new ImageIcon(getClass().getResource("image/wall.gif"));
		// 将墙体图像实例付与wallImage
		wallImage = icon.getImage();
		//导入个[英雄]来当主角
		icon = new ImageIcon(getClass().getResource("image/hero.jpg"));
		roleImage = icon.getImage();
	}
	
	private void loadImage1() {
		// 获得当前类对应的相对位置image 文件夹下的地板图像	
		ImageIcon icon = new ImageIcon(getClass().getResource("image/沙漠.jpg"));
			//ImageIcon icon = new ImageIcon(getClass().getResource("image/000草地.png"));
		// 将地板图像实例付与floorImage
		floorImage = icon.getImage();
		// 获得当前类对应的相对位置image 文件夹下的墙体图像
		icon = new ImageIcon(getClass().getResource("image/仙人掌.jpg"));
		// 将墙体图像实例付与wallImage
		wallImage = icon.getImage();
		//导入个[英雄]来当主角
		switch (Choice)
		{
		case 1:
			icon = new ImageIcon(getClass().getResource("image/绿巨人.jpg"));
			break;
		case 2:
			icon = new ImageIcon(getClass().getResource("image/钢铁侠.jpg"));
			break;
		case 3:
			icon = new ImageIcon(getClass().getResource("image/鹰眼.jpg"));
			break;
		case 4:
			icon = new ImageIcon(getClass().getResource("image/美国队长.jpg"));
			break;
		case 5:
			icon = new ImageIcon(getClass().getResource("image/黑寡妇.jpg"));
			break;
		case 6:
			icon = new ImageIcon(getClass().getResource("image/雷神.jpg"));
			break;
		}
		roleImage = icon.getImage();
		icon = new ImageIcon(getClass().getResource("image/蜜蜂.jpg"));
		beeImage = icon.getImage();
	}
  
	private void drawMap(Graphics g) {  
		// 简单的双层for 循环进行地图描绘，
		for (int x = 0; x < ROW; x++) { 
			for (int j = 0; j < COL ; j++) {  
				switch (map[x][j]) { 
				case 0 :
					// map 的标记为0时画出地板 //在指定位置[描绘]出我们所加载的图形，以下同 
					g.drawImage(floorImage, j * CS, x*CS, this) ;
					break;  
				case 1 : 
					// map 的标记为1时画出城墙
					g.drawImage(wallImage, j * CS, x*CS, this);
					break;
					// 我们可以依次类推出无数的背景组合，如定义椅子为2、宝座为3等 
					//很容易即可勾勒出一张背景地图。
				case 2 :
					g.drawImage(beeImage, j * CS, x*CS, this);
					break;
				default: // 当所有case 值皆不匹配时，将执行此操作。
					break; 
				}
			}
		}
		//g.drawImage(roleImage, 240, 240, this);
	}
	
	public void keyPressed(KeyEvent e) {
		//获得按键编号
		int keyCode = e.getKeyCode();
		//通过转换器匹配事件
		switch (keyCode) {
		//当触发Left时
		case KeyEvent.VK_LEFT :
			//进行left 操作，仅符合move()中[规范]时执行，以下相同
			move(LEFT);
			break;
			//当触发Right 时
		case KeyEvent.VK_RIGHT :
			move(RIGHT);
			break;
			//当触发Up 时
		case KeyEvent.VK_UP :
			move(UP);
			break;
			//当触发Down 时
		case KeyEvent.VK_DOWN :
			move(DOWN);
			break;
		}
		// 重新绘制窗体图像
		// PS:在此例程中，仅进行了角色的简单移动处理
		// ，关于避免闪烁及限制活动区域问题，请见后续
		// 案例。		
		//repaint();
		if(x==13 && y==13)
			JOptionPane.showMessageDialog(this, "恭喜通过一关");
	}
	
	
	
		/**
		* 用于判定是否允许移动的发生，被move()函数调用
		* @param x
		* @param y
		* @return
		*/
	private boolean isAllow(int x, int y) {
		// 以(x,y)交点进行数据判定，我们都知道，
		// 在本例中我仅以0 作为地板的参数，1 作为
		// 墙的参数，由于我们的主角是[人类]，而
		// 不是[幽灵]，所以当他要[撞墙]时，我们
		// 当然不会允许
		if (map[y][x] == 1) {
			// 不允许移动时，返回[假]
			return false;
		}
		if (map[y][x] == 2){
			Example1.Role[1].Hp -= 10;
			this.x--;
			this.y--;
			if(Example1.Role[1].Hp <= 0)
			{
				label2.setOpaque(true);
				label2.setBounds(0, 0, 200, 30);
				label2.setLocation(0, 169);
				add(label2);
				setVisible(true);
				label2.setText("您的英雄已经死亡! ");
				add(label2);
				Example1.Role[1].Hp = 100;
			}
		}
		// 允许移动时时，返回[真]
		return true;
	}


	/**
	* 判断移动事件，关联isAllow()函数
	* @param event
	*/
		private void move(int event) {
			int Step = 1;
			//以转换器判断相关事件，仅执行符合[规范]的操作。
			switch (event) {
			case LEFT:
				//依次判定事件
				if (isAllow(x-1, y)) 
					x = x - Step;
				direction = LEFT;
				break;
			case RIGHT:
				if (isAllow(x+1, y)) 
					x = x + Step;
				direction = RIGHT;
				break;
			case UP:
				if (isAllow(x, y-1)) 
					y = y - Step;
				direction = UP;
				break;
			case DOWN:
				if (isAllow(x, y+1)) 
					y = y + Step;
				direction = DOWN;
				break;
			default:
				break;
			}
		}
		
		public void keyReleased(KeyEvent e) {
		}
		
		public void keyTyped(KeyEvent e) {	
		}
		
		private class uncontrol extends Thread {
			Random rand = new Random();
			int x;
			int y;
			int n;
			uncontrol(int x, int y){
				x = x;
				y = y;
			}
			public synchronized void run() {
				while(true) {
					n = (int) rand.nextInt(4);
					switch (n) {
						case 0:
							x++;
							break;
						case 1:
							x--;
							break;
						case 2:
							y--;
							break;
						case 3: 
							y++;
							break;
					}
					try {
						Thread.sleep(100);
					}catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		private class AnimationThread extends Thread {
			public synchronized void run() {
				while (true) {
					/*if (count == 0)
						count = 1;
					else if (count == 1)
						count = 0;*/
					repaint();
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mousePressed(MouseEvent e) {
			int x, y;
			x = e.getX();
			y = e.getY();
			while(x/CS > this.x)
			{
				if (isAllow(this.x+1, this.y)) 
				{
					this.x++;
					System.out.println(this.x);
					direction = RIGHT;
					// 重绘画面。
					//repaint();
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			while(x/CS < this.x)
			{
				if (isAllow(this.x-1, this.y)) 
				{
					this.x--;
					System.out.println(this.x);
					direction = LEFT;
					// 重绘画面。
					//repaint();
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			while(y/CS > this.y)
			{
				if (isAllow(this.x, this.y+1)) 
				{
					this.y++;
					System.out.println(this.y);
					direction = UP;
					// 重绘画面。
					repaint();
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			while(y/CS < this.y)
			{
				if (isAllow(this.x, this.y-1)) 
				{
					this.y--;
					System.out.println(this.y);
					direction = DOWN;
					// 重绘画面。
					repaint();
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
  }
  

  