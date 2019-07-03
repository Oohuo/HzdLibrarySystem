package login;


import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;


@SuppressWarnings("serial")
public class Regist extends JFrame implements ActionListener,ItemListener
{
    private JTextField UserName,密码,确认密码;
    private JLabel 用户姓名,用户口令,重复密码; 
    private JButton 下一步,重新填写;
    public Regist()
    {
    	super("坦克大战注册界面");  
        //设置大小  
        setSize(1000, 500);  
        //设置位置  
        setLocation(200, 140);  
        //背景图片的路径。（相对路径或者绝对路径。本例图片放于"java项目名"的文件下）  
        String path = "jx3.jpg";  
        // 背景图片  
        ImageIcon background = new ImageIcon(path);   
        
        // 把背景图片显示在一个标签里面  
        JLabel label = new JLabel(background);  
        // 把标签的大小位置设置为图片刚好填充整个面板  
        label.setBounds(0, 0, this.getWidth(), this.getHeight());  
        // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明  
        JPanel imagePanel = (JPanel) this.getContentPane();  
        imagePanel.setOpaque(false);  
        // 把背景图片添加到分层窗格的最底层作为背景  
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));  
        //设置可见  
        setVisible(true);  
       
        //点关闭按钮时退出  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setLayout(null);
        用户姓名=new JLabel("用户ID");
  	  用户姓名.setFont(new Font("黑体", Font.BOLD, 20));
  	用户姓名.setForeground(Color.white); 
  	  用户姓名.setBounds(680,80,100,20);	  
        this.add(用户姓名);
        
        用户口令=new JLabel("用户密码");
        用户口令.setBounds(680,180,100,20);
        用户口令.setFont(new Font("黑体", Font.BOLD, 20));
        用户口令.setForeground(Color.white); 
        
        this.add(用户口令);
        
        重复密码=new JLabel("重复密码");
        重复密码.setFont(new Font("黑体", Font.BOLD, 20));
        重复密码.setForeground(Color.white); 
        重复密码.setBounds(680,280,100,20);	  
          this.add(重复密码);
        
        UserName=new JTextField(10);
        UserName.setBounds(800,80,100,20);	 
        this.add(UserName);
        UserName.addActionListener(this);
        
        
        
        
        密码=new JTextField(10);
        密码.setBounds(800,180,100,20);	 
        this.add(密码);
        密码.addActionListener(this);
        
        
        确认密码=new JTextField(10);
        确认密码.setBounds(800,280,100,20);	 
        this.add(确认密码);
        确认密码.addActionListener(this);
        
        
        下一步=new JButton("下一步");
        下一步.setBounds(680,350,100,50);
        this.add(下一步);
        this.setBackground(Color.CYAN);
        下一步.addActionListener(this);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        
        重新填写=new JButton("重新填写");
        重新填写.setBounds(840,350,100,50);
        重新填写.addActionListener(this);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        imagePanel.add(重新填写);
        
        imagePanel.add(下一步);
    //   密码=new JPasswordField(10); 
    //    确认密码=new JPasswordField(10);
        
    	/*app=new JFrame("现在注册");
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
        p2.add(new JLabel("用户账号"));
        用户名=new JTextField(10);
        p3.add(用户名);
        p2.add(new JLabel("性别"));
        ButtonGroup 性别=new ButtonGroup();
        男=new JRadioButton("男",true);
        性别.add(男);
        女=new JRadioButton("女",true);
        性别.add(女);
        JPanel p31=new JPanel();
        p31.setLayout(new GridLayout(1,2));
        p31.add(男);
        p31.add(女);
        p3.add(p31);
        p2.add(new JLabel("密码"));
        密码=new JPasswordField(10);
        p3.add(密码);
        p2.add(new JLabel("确认密码"));
        确认密码=new JPasswordField(10);
        p3.add(确认密码);
        p2.add(new JLabel("姓名："));
        姓名=new JTextField(10);
        p3.add(姓名);
       
        下一步=new JButton("下一步");
        p2.add(下一步);
        下一步.addActionListener(this);
        重新填写=new JButton("重新填写");
        p3.add(重新填写);
        重新填写.addActionListener(this);
        app.setLocationRelativeTo(null);
        app.setVisible(true);
        */
    }
    public void itemStateChanged(ItemEvent f)
    {
    	
    }
    public void actionPerformed(ActionEvent e)
    {
    	if(密码.getText().equals(确认密码.getText()))
    	{
    	  if(e.getSource()==下一步)
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
    			
    			String sqlstr="INSERT INTO manager (Mname,Mpassword)"+"VALUE('"+UserName.getText()+"','"+密码.getText()+"')";
    			stmt.executeUpdate(sqlstr);
    			stmt.close();
    			con.close();
    			JOptionPane.showMessageDialog(this,"注册成功","系统提示",JOptionPane.PLAIN_MESSAGE);
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
    	    JOptionPane.showMessageDialog(this,"对不起！两次密码输入不同，请重新输入！","系统提示",JOptionPane.INFORMATION_MESSAGE);
    	    用户姓名.setText("");
    	    用户口令.setText("");
    	    重复密码.setText("");
    	    
    	    setVisible(false);
    	    new Regist();
          }
        
    	
    	if(e.getSource()==重新填写)
    	{
    		用户姓名.setText("");
    	    用户口令.setText("");
    	    重复密码.setText("");
    	    setVisible(false);
    	    new Regist();
    		
    	}
    } 
   
}
