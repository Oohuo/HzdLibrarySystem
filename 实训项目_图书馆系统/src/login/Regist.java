package login;


import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;


@SuppressWarnings("serial")
public class Regist extends JFrame implements ActionListener,ItemListener
{
    private JTextField UserName,����,ȷ������;
    private JLabel �û�����,�û�����,�ظ�����; 
    private JButton ��һ��,������д;
    public Regist()
    {
    	super("̹�˴�սע�����");  
        //���ô�С  
        setSize(1000, 500);  
        //����λ��  
        setLocation(200, 140);  
        //����ͼƬ��·���������·�����߾���·��������ͼƬ����"java��Ŀ��"���ļ��£�  
        String path = "jx3.jpg";  
        // ����ͼƬ  
        ImageIcon background = new ImageIcon(path);   
        
        // �ѱ���ͼƬ��ʾ��һ����ǩ����  
        JLabel label = new JLabel(background);  
        // �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������  
        label.setBounds(0, 0, this.getWidth(), this.getHeight());  
        // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��  
        JPanel imagePanel = (JPanel) this.getContentPane();  
        imagePanel.setOpaque(false);  
        // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����  
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));  
        //���ÿɼ�  
        setVisible(true);  
       
        //��رհ�ťʱ�˳�  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setLayout(null);
        �û�����=new JLabel("�û�ID");
  	  �û�����.setFont(new Font("����", Font.BOLD, 20));
  	�û�����.setForeground(Color.white); 
  	  �û�����.setBounds(680,80,100,20);	  
        this.add(�û�����);
        
        �û�����=new JLabel("�û�����");
        �û�����.setBounds(680,180,100,20);
        �û�����.setFont(new Font("����", Font.BOLD, 20));
        �û�����.setForeground(Color.white); 
        
        this.add(�û�����);
        
        �ظ�����=new JLabel("�ظ�����");
        �ظ�����.setFont(new Font("����", Font.BOLD, 20));
        �ظ�����.setForeground(Color.white); 
        �ظ�����.setBounds(680,280,100,20);	  
          this.add(�ظ�����);
        
        UserName=new JTextField(10);
        UserName.setBounds(800,80,100,20);	 
        this.add(UserName);
        UserName.addActionListener(this);
        
        
        
        
        ����=new JTextField(10);
        ����.setBounds(800,180,100,20);	 
        this.add(����);
        ����.addActionListener(this);
        
        
        ȷ������=new JTextField(10);
        ȷ������.setBounds(800,280,100,20);	 
        this.add(ȷ������);
        ȷ������.addActionListener(this);
        
        
        ��һ��=new JButton("��һ��");
        ��һ��.setBounds(680,350,100,50);
        this.add(��һ��);
        this.setBackground(Color.CYAN);
        ��һ��.addActionListener(this);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        
        ������д=new JButton("������д");
        ������д.setBounds(840,350,100,50);
        ������д.addActionListener(this);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        imagePanel.add(������д);
        
        imagePanel.add(��һ��);
    //   ����=new JPasswordField(10); 
    //    ȷ������=new JPasswordField(10);
        
    	/*app=new JFrame("����ע��");
    	app.setSize(1000, 500);
    	app.setLocation(200,140);
    	app.setDefaultCloseOperation(app.EXIT_ON_CLOSE);*/
        
        
        
        
        
        
        
        /*
    	Container c=app.getContentPane();
    	c.setLayout(new GridLayout(1,3));
    	JPanel p1=new JPanel();
    	
        JPanel p2=new JPanel();
        JPanel p3=new JPanel();
        c.add(p2);
        c.add(p3);
        p2.setLayout(new GridLayout(9,1,0,10));
        p3.setLayout(new GridLayout(9,1,0,10));
        p2.setBackground(Color.CYAN);
        p2.add(new JLabel("�û��˺�"));
        �û���=new JTextField(10);
        p3.add(�û���);
        p2.add(new JLabel("�Ա�"));
        ButtonGroup �Ա�=new ButtonGroup();
        ��=new JRadioButton("��",true);
        �Ա�.add(��);
        Ů=new JRadioButton("Ů",true);
        �Ա�.add(Ů);
        JPanel p31=new JPanel();
        p31.setLayout(new GridLayout(1,2));
        p31.add(��);
        p31.add(Ů);
        p3.add(p31);
        p2.add(new JLabel("����"));
        ����=new JPasswordField(10);
        p3.add(����);
        p2.add(new JLabel("ȷ������"));
        ȷ������=new JPasswordField(10);
        p3.add(ȷ������);
        p2.add(new JLabel("������"));
        ����=new JTextField(10);
        p3.add(����);
       
        ��һ��=new JButton("��һ��");
        p2.add(��һ��);
        ��һ��.addActionListener(this);
        ������д=new JButton("������д");
        p3.add(������д);
        ������д.addActionListener(this);
        app.setLocationRelativeTo(null);
        app.setVisible(true);
        */
    }
    public void itemStateChanged(ItemEvent f)
    {
    	
    }
    public void actionPerformed(ActionEvent e)
    {
    	if(����.getText().equals(ȷ������.getText()))
    	{
    	  if(e.getSource()==��һ��)
    	 {
    		Connection con;
    		Statement stmt;
    		
    		try
    		{
    			Class.forName("com.mysql.jdbc.Driver");
    		}
    		catch(ClassNotFoundException ce)
    		{
    			System.out.println("SQL Exception:"+ce.getLocalizedMessage());
    		}
    		try
    		{
    			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&characterEncoding=UTF8","root","");
    			stmt=con.createStatement();
    			
    			String sqlstr="INSERT INTO manager (Mname,Mpassword)"+"VALUE('"+UserName.getText()+"','"+����.getText()+"')";
    			stmt.executeUpdate(sqlstr);
    			stmt.close();
    			con.close();
    			JOptionPane.showMessageDialog(this,"ע��ɹ�","ϵͳ��ʾ",JOptionPane.PLAIN_MESSAGE);
    			new Login();
    			setVisible(false);
    			
    		}
    		catch(SQLException f)
  		  {
  			System.out.println("SQLException:"+f.getMessage());  
  		  }	
    	 }
       }
    
        else 
			
		
          {
    	    JOptionPane.showMessageDialog(this,"�Բ��������������벻ͬ�����������룡","ϵͳ��ʾ",JOptionPane.INFORMATION_MESSAGE);
    	    �û�����.setText("");
    	    �û�����.setText("");
    	    �ظ�����.setText("");
    	    
    	    setVisible(false);
    	    new Regist();
          }
        
    	
    	if(e.getSource()==������д)
    	{
    		�û�����.setText("");
    	    �û�����.setText("");
    	    �ظ�����.setText("");
    	    setVisible(false);
    	    new Regist();
    		
    	}
    } 
   
}
