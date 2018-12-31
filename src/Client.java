import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


class MainFrame extends JFrame implements Runnable {
	public JButton toEnglish,toChinese,btnA,btnB,btnC,btnD,comfirm;
	public JLabel A,B,C,D,showText,true_or_false,finalText,left_to_done,right_done;
	public JTextField inputField;
	String true_answer = null;
	String[] English_words = {"oblige","compassion","vicinity","trample","vacant",
			"qualification","verge","submarine","segmentation","refine"};
	String[] Chinese_words = {"��ʹ","ͬ����","����","��̤","�յ�","�ʸ�","��Ե","Ǳͧ","�ָ�","����"};
	public String[] word = new String[4];
	int test_time=10,right=0;  // test_time���ڼ���������10�Σ� right�ж��Ե�����
	class giveWord extends Thread{
		double Random1 = Math.random()*10;
		int random1 = (int)Random1; // ���������random1�������˳���ѡ���ĵ�����
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(test_time>0){
				random1 = (random1+1)%10; // ��ѡ��λ�ò���ѡ����һ�飬ֱ��10�鵥�ʶ�ѡ��
				double Random2 = (Math.random()*10)%4;
				int random2 = (int)Random2; // ���������random2��random2��Ϊѡ����±�������ĸ�����ȷ�ĵ���				
				word[random2] = English_words[random1];
				word[(random2+1)%4] = English_words[(random1+1)%10];
				word[(random2+2)%4] = English_words[(random1+2)%10];
				word[(random2+3)%4] = English_words[(random1+3)%10];
				showText.setText(Chinese_words[random1]); // ������ʾ�����ģ�Ӣ�ģ�
				btnA.setText(word[0]);
				btnB.setText(word[1]);
				btnC.setText(word[2]);
				btnD.setText(word[3]); // ����ѡ���Ӣ�ģ����ģ�������ֻ��һ��ѡ���Ƕ�Ӧ��ȷ�ģ���random2������
				test_time -= 1; // ÿִ��һ�Σ���������һ
				try {
					Thread.sleep(5000); // �����߳�˯��ʱ��Ϊ5�룬����ÿ������ˢ�µ���
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
				left_to_done.setText("ʣ�ࣺ" + test_time);
				right_done.setText("��ȷ��" + right); // ������ʾʣ����������ȷ������right���ж�������ĳ���
			}
			finalText.setText("���Խ���!!!"); // ������10�κ�������
		}
	}
	giveWord words = new giveWord();
	class ButtonAction implements ActionListener{ // Ϊ�����ť���������������������棩
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String answer = showText.getText();
			int index = -1;
			for(int i = 0; i < 10; i++){
				if(Chinese_words[i].equals(answer)){
					index = i;
					break;
				}
			} // index���ڻ����ʾ�����ģ�Ӣ�ģ����±꣬ͨ���±�Ѱ�Ҷ�Ӧ��Ӣ�ģ����ģ���Ԥ������Ӣ��������Ĵ���һһ��Ӧ��
			true_answer = English_words[index]; // true_answer����ȷѡ��
			String my_answer = null;
			String ss = inputField.getText();	
			if(!ss.equals("")){ // ���������
				if(ss.equals("A"))             //�ύ�������ַ�ʽ��һ�������룬Ȼ����ȷ�����ڵ��ȷ��ʱ���ж���
					my_answer = word[0];       //���Ӧ��ѡ�������Ƿ���ȷ����һ����ֱ�ӵ��ѡ�ť����ȡ��ť��ֵ��
				else if(ss.equals("B"))        //���ж��Ƿ���ȷ
					my_answer = word[1];
				else if(ss.equals("C"))
					my_answer = word[2];
				else if(ss.equals("D"))
					my_answer = word[3]; // �������ѡ��Ĵ�
			}		
			else{				// ���û�����룬��ñ������ť��ֵ
				my_answer = ((JButton)(e.getSource())).getText();
			}
			// true_or_false������ʾѡ�еĵ��ʻ�����д�Ĵ���ȷ���
			if(true_answer.equals(my_answer)){ // �������ͬ��ȷ
				true_or_false.setText("��ȷ");
				right++; // ��ʾ��ȷ������ȷ����һ
			}
			else{
				true_or_false.setText("����"); // ���������Ϊ����
			}
			try {
				words.interrupt();
			} catch (Exception e2) {
				// TODO: handle exception
			}			
		}
	}
	
	@Override
	public void run() { // �����������run������������ʵ�������ӣ���Ӣ���л�
		// TODO Auto-generated method stub
		
		toEnglish = new JButton("����Ӣ");
		toChinese = new JButton("Ӣ����"); // ������ģʽ�İ�ť
		toEnglish.setBounds(0, 0, 100, 50);
		toEnglish.setFont(new Font("TimesRoman",Font.BOLD,20));
		toEnglish.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) { // ����Ӣ���������ѡ����Ӣ�ĵ���
				// TODO Auto-generated method stub
				String[] words1 = {"oblige","compassion","vicinity","trample","vacant","qualification","verge","submarine","segmentation","refine"};
				String[] words2 = {"��ʹ","ͬ����","����","��̤","�յ�","�ʸ�","��Ե","Ǳͧ","�ָ�","����"};
				System.arraycopy(words1, 0, English_words, 0, words1.length);
				System.arraycopy(words2, 0, Chinese_words, 0, words2.length); // ʹ����arraycopy�����滻
				try {
					words.interrupt();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		toChinese.setBounds(0, 60, 100, 50);
		toChinese.setFont(new Font("TimesRoman",Font.BOLD,20));
		toChinese.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) { // Ӣ���б��������ѡ��������
				// TODO Auto-generated method stub
				String[] words1 = {"oblige","compassion","vicinity","trample","vacant","qualification","verge","submarine","segmentation","refine"};
				String[] words2 = {"��ʹ","ͬ����","����","��̤","�յ�","�ʸ�","��Ե","Ǳͧ","�ָ�","����"};
				System.arraycopy(words2, 0, English_words, 0, words2.length);
				System.arraycopy(words1, 0, Chinese_words, 0, words1.length);
				try {
					words.interrupt();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		add(toEnglish);
		add(toChinese); // ������
		
		showText = new JLabel(); // showText��ʾҪ����ĵ��ʣ����
		true_or_false = new JLabel(); // ��ȷ������ʾ
		finalText = new JLabel(); // ���Խ�������ʾ
		left_to_done = new JLabel("ʣ�ࣺ" + String.valueOf(test_time)); // ʣ����
		right_done = new JLabel("��ȷ��" + String.valueOf(right)); // ��ȷ��
		A = new JLabel("A��");
		B = new JLabel("B��");
		C = new JLabel("C��");
		D = new JLabel("D��");	// ����ĸ�A��B��C��D��label
		showText.setBounds(410, 120, 250, 50); // ������Ϊ���е�JLabel��JButton������ʾλ�ã���С�����壬��������
		showText.setFont(new Font("TimesRoman",Font.BOLD,32));
		add(showText);
		true_or_false.setBounds(410, 500, 180, 50);
		true_or_false.setFont(new Font("TimesRoman",Font.BOLD,24));
		add(true_or_false);
		finalText.setBounds(320, 620, 180, 50);
		finalText.setFont(new Font("TimesRoman",Font.BOLD,32));
		add(finalText);
		left_to_done.setBounds(650, 10, 100, 50);
		left_to_done.setFont(new Font("TimesRoman",Font.BOLD,22));
		add(left_to_done);
		right_done.setBounds(650, 60, 100, 50);
		right_done.setFont(new Font("TimesRoman",Font.BOLD,22));
		add(right_done);
		A.setBounds(250, 200, 50, 50);
		A.setFont(new Font("TimesRoman",Font.BOLD,24));
		add(A);
		B.setBounds(250, 280, 50, 50);
		B.setFont(new Font("TimesRoman",Font.BOLD,24));
		add(B);
		C.setBounds(250, 360, 50, 50);
		C.setFont(new Font("TimesRoman",Font.BOLD,24));
		add(C);
		D.setBounds(250, 440, 50, 50);
		D.setFont(new Font("TimesRoman",Font.BOLD,24));
		add(D);
		
		btnA = new JButton();
		btnB = new JButton();
		btnC = new JButton();
		btnD = new JButton();
		btnA.setBounds(320, 200, 250, 50);
		btnA.setFont(new Font("TimesRoman",Font.BOLD,20));
		btnA.addActionListener(new ButtonAction());
		add(btnA);
		btnB.setBounds(320, 280, 250, 50);
		btnB.setFont(new Font("TimesRoman",Font.BOLD,20));
		btnB.addActionListener(new ButtonAction());
		add(btnB);
		btnC.setBounds(320, 360, 250, 50);
		btnC.setFont(new Font("TimesRoman",Font.BOLD,20));
		btnC.addActionListener(new ButtonAction());
		add(btnC);
		btnD.setBounds(320, 440, 250, 50);
		btnD.setFont(new Font("TimesRoman",Font.BOLD,20));
		btnD.addActionListener(new ButtonAction());
		add(btnD);
		
		inputField = new JTextField();
		inputField.setBounds(250, 550, 200, 50);
		inputField.setText(null);
		inputField.setFont(new Font("TimesRoman",Font.BOLD,20));
		add(inputField);
		
		comfirm = new JButton("ȷ��");
		comfirm.setBounds(470, 550, 100, 50);
		comfirm.setFont(new Font("TimesRoman",Font.BOLD,20));
		comfirm.addActionListener(new ButtonAction());
		add(comfirm);
		
		words.start();	// �ڴ����д������������߳�
		
		setLayout(null); // ���ò��֣�λ�ã���С�����⣬���ÿɼ���ȷ�������Ч�͵���ر�ʱ�Ĳ���
		setBounds(600, 200, 800, 800);
		setTitle("test words");
		setVisible(true);
		validate();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}


public class Client {
	public static void main(String[] args) {
		MainFrame myframe = new MainFrame();
		Thread MainThread = new Thread(myframe);
		MainThread.setName("���߳�");
		MainThread.start(); // �����߳��д�������
	}
}
