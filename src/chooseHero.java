import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class chooseHero extends JPanel implements ActionListener{
	int Choice;
	JButton button1, button2, button3, button4, button5, button6;
	public chooseHero(){
		button1 = new JButton("hero 1");
		button1.setIcon(new ImageIcon("/Users/jiaoxijie/eclipse-workspace/Example1/bin/image/绿巨人.jpg"));
		button1.setMnemonic(KeyEvent.VK_1);
		button1.addActionListener(this);
		button1.setPreferredSize(new Dimension(150, 75));
		this.add(button1);
		button2 = new JButton("hero 2");
		button2.setIcon(new ImageIcon("/Users/jiaoxijie/eclipse-workspace/Example1/bin/image/钢铁侠.jpg"));
		button2.setMnemonic(KeyEvent.VK_2);
		button2.addActionListener(this);
		button2.setPreferredSize(new Dimension(150, 75));
		this.add(button2);
		button3 = new JButton("hero 3");
		button3.setIcon(new ImageIcon("/Users/jiaoxijie/eclipse-workspace/Example1/bin/image/鹰眼.jpg"));
		button3.setMnemonic(KeyEvent.VK_2);
		button3.addActionListener(this);
		button3.setPreferredSize(new Dimension(150, 75));
		this.add(button3);
		button4 = new JButton("hero 4");
		button4.setIcon(new ImageIcon("/Users/jiaoxijie/eclipse-workspace/Example1/bin/image/美国队长.jpg"));
		button4.setMnemonic(KeyEvent.VK_2);
		button4.addActionListener(this);
		button4.setPreferredSize(new Dimension(150, 75));
		this.add(button4);
		button5 = new JButton("hero 5");
		button5.setIcon(new ImageIcon("/Users/jiaoxijie/eclipse-workspace/Example1/bin/image/黑寡妇.jpg"));
		button5.setMnemonic(KeyEvent.VK_2);
		button5.addActionListener(this);
		button5.setPreferredSize(new Dimension(150, 75));
		this.add(button5);
		button6 = new JButton("hero 6");
		button6.setIcon(new ImageIcon("/Users/jiaoxijie/eclipse-workspace/Example1/bin/image/雷神.jpg"));
		button6.setMnemonic(KeyEvent.VK_2);
		button6.addActionListener(this);
		button6.setPreferredSize(new Dimension(150, 75));
		this.add(button6);
	}
	
	public void actionPerformed(ActionEvent e) {
		int exi;
		if(button1 == e.getSource()) {
			exi = JOptionPane.showConfirmDialog (null, "你确定选择英雄1吗？ ", "确认窗口", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (exi == JOptionPane.YES_OPTION)
            {
            	Choice = 1;
            	Example1.createAndShowMap();
            }
            else
                return;
		}
		else if(button2 == e.getSource()) {
			exi = JOptionPane.showConfirmDialog (null, "你确定选择英雄2吗？ ", "确认窗口", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (exi == JOptionPane.YES_OPTION)
            {
            	Choice = 2;
            	Example1.createAndShowMap();
            }
            else
                return;
		}
		else if(button3 == e.getSource()) {
			exi = JOptionPane.showConfirmDialog (null, "你确定选择英雄3吗？ ", "确认窗口", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (exi == JOptionPane.YES_OPTION)
            {
            	Choice = 3;
            	Example1.createAndShowMap();
            }
            else
                return;
		}
		else if(button4 == e.getSource()) {
			exi = JOptionPane.showConfirmDialog (null, "你确定选择英雄4吗？ ", "确认窗口", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (exi == JOptionPane.YES_OPTION)
            {
            	Choice = 4;
            	Example1.createAndShowMap();
            }
            else
                return;
		}
		else if(button5 == e.getSource()) {
			exi = JOptionPane.showConfirmDialog (null, "你确定选择英雄5吗？ ", "确认窗口", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (exi == JOptionPane.YES_OPTION)
            {
            	Choice = 5;
            	Example1.createAndShowMap();
            }
            else
                return;
		}
		else if(button6 == e.getSource()) {
			exi = JOptionPane.showConfirmDialog (null, "你确定选择英雄6吗？ ", "确认窗口", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (exi == JOptionPane.YES_OPTION)
            {
            	Choice = 6;
            	Example1.createAndShowMap();
            }
            else
                return;
		}
	}
	
	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Now Choose Your Hero!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chooseHero pane = new chooseHero();
		frame.add(pane);
		frame.setSize(250, 200);
		frame.setVisible(true);
	}
	
	/*public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				createAndShowGUI();
			}
		});
	}*/
}

