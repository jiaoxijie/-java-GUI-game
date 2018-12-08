import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class chooseMap extends JPanel implements ActionListener{
	int Choice;
	JButton button1, button2;
	public chooseMap(){
		button1 = new JButton("沙漠");
		button1.setIcon(new ImageIcon("/Users/jiaoxijie/eclipse-workspace/Example1/bin/image/沙漠.jpg"));
		button1.setMnemonic(KeyEvent.VK_1);
		button1.addActionListener(this);
		button1.setPreferredSize(new Dimension(150, 75));
		this.add(button1);
		button2 = new JButton("草地");
		button2.setIcon(new ImageIcon("/Users/jiaoxijie/eclipse-workspace/Example1/bin/image/草地.png"));
		button2.setMnemonic(KeyEvent.VK_2);
		button2.addActionListener(this);
		button2.setPreferredSize(new Dimension(150, 75));
		this.add(button2);
	}
	
	public void actionPerformed(ActionEvent e) {
		int exi;
		if(button1 == e.getSource()) {
			exi = JOptionPane.showConfirmDialog (null, "你确定选择地图1吗？ ", "确认窗口", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (exi == JOptionPane.YES_OPTION)
            {
            	Choice = 1;
            	Example1 e1 = new Example1();
            	e1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            	e1.setVisible(true);
            }
            else
                return;
		}
		else if(button2 == e.getSource()) {
			exi = JOptionPane.showConfirmDialog (null, "你确定选择地图2吗？ ", "确认窗口", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (exi == JOptionPane.YES_OPTION)
            {
            	Choice = 2;
            	Example1 e1 = new Example1();
            	e1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            	e1.setVisible(true);
            }
            else
                return;
		}
	}
	
	/*private static void createAndShowGUI() {
		JFrame frame = new JFrame("Now Choose Your Hero!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chooseMap pane = new chooseMap();
		frame.add(pane);
		frame.setSize(250, 200);
		frame.setVisible(true);
	}*/
	
	/*public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				createAndShowGUI();
			}
		});
	}*/
}

