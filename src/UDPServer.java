
import java.net.*;
import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
public class UDPServer extends JFrame implements Runnable //�̳нӿڣ�ʵ���̹߳���
{
    JTextArea inMessage = new JTextArea(12,20); 
    JButton b = new JButton("��������");  //���������Ͱ�ť
    
    UDPServer()
    {
    	
    	super("������"); //���ñ���
    	
    	setSize(800,600);
    	setVisible(true); //����GUI��С��ʹ��ɼ�
    	
    	Container con = getContentPane();
    	con.add(new JScrollPane(inMessage), BorderLayout.CENTER); //����������Ϊ��������ò���
    	
    	
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //���ùر�ʱ�����˳�
    	validate(); //ȷ�������Ч
    	
    	
    	Thread thread = new Thread(this);    	
    	thread.start();                    //Ϊ���ڴ����̲߳��������̸߳����������
    }
       
    public void run() //����run����
    {                     
		//��������
    	DatagramPacket pack = null; //����������ݵ����ݱ�
    	DatagramSocket mail = null; //�����������ݱ����׽���
    	byte b[] = new byte[8192];
    	try
    	{
    		// ---
    		pack = new DatagramPacket(b,b.length); //ָ��byte���飬�涨�˰��Ĵ�С
    		
    		// ---
    		mail = new DatagramSocket(9999); //ָ�����˽��ն˿�Ϊ9999
    	}
    	catch(Exception e){} 	
	    while(true)
	    {
    		try
    		{
    			mail.receive(pack); //�������ݱ�
    			
    			String message = new String(pack.getData(),0,pack.getLength()); 
    			//������ݱ��е�String�����ַ���
    			if(message.substring(0, 2).equals("A:")){ 
    				try //�����"A:"��ͷ�������ݽ�ȥ��ͷ��"A:"�����͵�B(�˿�5678),���ڷ������˵�GUI�����ʾ
    	        	{
    	        		
    	        		InetAddress address = InetAddress.getByName("127.0.0.1");
    	        		
    	        		String ss = message.substring(2,message.length());
    	        		byte text[] = ss.getBytes();
    	        		DatagramPacket data = new DatagramPacket(text,text.length,address,5678);    	        		
    	        		
    	        		DatagramSocket mail1 = new DatagramSocket();   	        		
    	        		
    	        		mail1.send(data); //ָ���˿ڷ��ʹ����������        		
    	        		inMessage.append("�����˴�A��B������\n"); //�ڷ������������ʾ
    	    			inMessage.setCaretPosition(inMessage.getText().length());
    	           }
    	           catch(Exception e){}    
    			}
    			else if(message.substring(0, 2).equals("B:")){
    				try //�����"B:"��ͷ�������ݽ�ȥ��ͷ��"B:"�����͵�A(�˿�1234),���ڷ������˵�GUI�����ʾ
    	        	{
    	        		
    	        		InetAddress address = InetAddress.getByName("127.0.0.1");
    	        		
    	        		String ss = message.substring(2,message.length());
    	        		byte text[] = ss.getBytes();
    	        		DatagramPacket data = new DatagramPacket(text,text.length,address,1234);   	        		
    	        		
    	        		DatagramSocket mail1 = new DatagramSocket();		
    	        		
    	        		mail1.send(data); //ָ���˿ڷ��ʹ����������
    	        		inMessage.append("�����˴�B��A������\n"); //�ڷ������������ʾ
    	    			inMessage.setCaretPosition(inMessage.getText().length());
    	           }
    	           catch(Exception e){}    
    			}
    			
            }
            catch(Exception e){}
    		 
       }
    }
    
    public static void main(String args[])
    {
       new UDPServer(); //ʹ������������
    }
    
}