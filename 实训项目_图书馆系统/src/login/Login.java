package login;


import java.sql.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener
{
	
	
    /**
	 * 
	 */
	
	private JButton loginin,清除,注册,退出;
    private JLabel 用户姓名,用户口令;
    private JTextField 用户名;
    private JPasswordField 用户密码;         
    private int massage=0;
    static String login;
    public Login()
    {
    	//设置标题  
        super("系统登陆");  
        //设置大小  
        setSize(1000, 640);  
        //设置位置  
        setLocationRelativeTo(null);                                                                     //9
        //背景图片的路径。（相对路径或者绝对路径。本例图片放于"java项目名"的文件下）  
        String path = "BC.jpg";  
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
        
      //修改窗口左上角图片
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("src/res/hzd.jpg");
		this.setIconImage(image);
      
	 
	  用户姓名=new JLabel("用户姓名");
	  用户姓名.setFont(new Font("幼圆", Font.BOLD, 20));
	  用户姓名.setBounds(390,250,100,20);	  
      this.add(用户姓名);
      
      
      用户名=new JTextField(10);
      用户名.setBounds(500,250,100,20);	 
      this.add(用户名);
      用户名.addActionListener(this);
      
      
      用户口令=new JLabel("用户密码");
      用户口令.setBounds(390,280,100,20);
      用户口令.setFont(new Font("幼圆", Font.BOLD, 20));
      this.add(用户口令);
      
      
      用户密码=new JPasswordField(10);
      
      用户密码.addActionListener(this);
      用户密码.setBounds(500,280,100,20);
      this.add(用户密码);
      
      loginin=new JButton("登录");
      loginin.setBounds(620,250,100,20);
      this.add(loginin);
      loginin.addActionListener(this);
      
      
      清除=new JButton("清除");
      this.add(清除);
      清除.setBounds(620,280,100,20);
      清除.addActionListener(this);
      
      

     
      
      
      注册=new JButton("注册");
      注册.setBounds(300,800,100,50);
      this.add(注册);
      this.setBackground(Color.CYAN);
      注册.addActionListener(this);
      this.setLocationRelativeTo(null);
      this.setResizable(false);
      this.setVisible(true);
     
      
    }
    
    
   
    
  public void actionPerformed(ActionEvent e)
    {

	if(e.getSource()==loginin||e.getSource()==用户名||e.getSource()==用户密码)
	{
		if(用户名.getText().equals("")||用户名.getText().equals(null))
		{
			JOptionPane.showMessageDialog(this,"请输入账号！","系统提示",JOptionPane.ERROR_MESSAGE);
		}
		else if(String.valueOf(用户密码.getPassword()).equals("")||String.valueOf(用户密码.getPassword()).equals(null))
		{
			JOptionPane.showMessageDialog(this,"请输入密码！","系统提示",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			Connection con=null;
			Statement stmt=null;
			ResultSet rs=null;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
			}
			catch(ClassNotFoundException f)
			{
				System.out.println("SQL Exception："+f.getLocalizedMessage());
				
			}
			try
			{
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
				stmt=con.createStatement();
				rs=stmt.executeQuery("select * from manager");
				while(rs.next())
				{
					String st1=rs.getString(1);
					String st2=rs.getString(2);
					char[] ps=用户密码.getPassword();
					String st3="";
					for(int i=0;i<ps.length;i++)
						st3+=ps[i];
					if((用户名.getText().equals(st1))&&(st3.equals(st2)))
					{
						String name= 用户名.getText();
						String in  = "     欢迎您:    ";
						MainDialog frame = new MainDialog();
						JOptionPane.showMessageDialog(this, in+name, "系统提示", JOptionPane.PLAIN_MESSAGE);
						// 设置主窗体可视
						frame.setVisible(true);
						massage=1;
						login = 用户名.getText();
						
						this.setVisible(false);
						
						rs.close();
						stmt.close();
						con.close();
						break;
					}
				}
				if(massage==0)
				{
					JOptionPane.showMessageDialog(this,"您输入的帐号或密码有误，请重新输入！","系统提示",JOptionPane.ERROR_MESSAGE);
				}
				con.close();
			}
			  catch(SQLException f)
			  {
				System.out.println(f);  
			  }
		}
	}
	if(e.getSource()==清除)
	{
	setVisible(false);
	new Login();
	}
	if(e.getSource()==退出)
	{
		System.exit(0);
	}
	
	if(e.getSource()==注册)
	{
		
		this.setVisible(false);
		new Regist();
	}
    }
	

}
