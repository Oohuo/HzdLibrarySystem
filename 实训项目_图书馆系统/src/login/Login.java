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
	
	private JButton loginin,���,ע��,�˳�;
    private JLabel �û�����,�û�����;
    private JTextField �û���;
    private JPasswordField �û�����;         
    private int massage=0;
    static String login;
    public Login()
    {
    	//���ñ���  
        super("ϵͳ��½");  
        //���ô�С  
        setSize(1000, 640);  
        //����λ��  
        setLocationRelativeTo(null);                                                                     //9
        //����ͼƬ��·���������·�����߾���·��������ͼƬ����"java��Ŀ��"���ļ��£�  
        String path = "BC.jpg";  
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
        
      //�޸Ĵ������Ͻ�ͼƬ
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("src/res/hzd.jpg");
		this.setIconImage(image);
      
	 
	  �û�����=new JLabel("�û�����");
	  �û�����.setFont(new Font("��Բ", Font.BOLD, 20));
	  �û�����.setBounds(390,250,100,20);	  
      this.add(�û�����);
      
      
      �û���=new JTextField(10);
      �û���.setBounds(500,250,100,20);	 
      this.add(�û���);
      �û���.addActionListener(this);
      
      
      �û�����=new JLabel("�û�����");
      �û�����.setBounds(390,280,100,20);
      �û�����.setFont(new Font("��Բ", Font.BOLD, 20));
      this.add(�û�����);
      
      
      �û�����=new JPasswordField(10);
      
      �û�����.addActionListener(this);
      �û�����.setBounds(500,280,100,20);
      this.add(�û�����);
      
      loginin=new JButton("��¼");
      loginin.setBounds(620,250,100,20);
      this.add(loginin);
      loginin.addActionListener(this);
      
      
      ���=new JButton("���");
      this.add(���);
      ���.setBounds(620,280,100,20);
      ���.addActionListener(this);
      
      

     
      
      
      ע��=new JButton("ע��");
      ע��.setBounds(300,800,100,50);
      this.add(ע��);
      this.setBackground(Color.CYAN);
      ע��.addActionListener(this);
      this.setLocationRelativeTo(null);
      this.setResizable(false);
      this.setVisible(true);
     
      
    }
    
    
   
    
  public void actionPerformed(ActionEvent e)
    {

	if(e.getSource()==loginin||e.getSource()==�û���||e.getSource()==�û�����)
	{
		if(�û���.getText().equals("")||�û���.getText().equals(null))
		{
			JOptionPane.showMessageDialog(this,"�������˺ţ�","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);
		}
		else if(String.valueOf(�û�����.getPassword()).equals("")||String.valueOf(�û�����.getPassword()).equals(null))
		{
			JOptionPane.showMessageDialog(this,"���������룡","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);
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
				System.out.println("SQL Exception��"+f.getLocalizedMessage());
				
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
					char[] ps=�û�����.getPassword();
					String st3="";
					for(int i=0;i<ps.length;i++)
						st3+=ps[i];
					if((�û���.getText().equals(st1))&&(st3.equals(st2)))
					{
						String name= �û���.getText();
						String in  = "     ��ӭ��:    ";
						MainDialog frame = new MainDialog();
						JOptionPane.showMessageDialog(this, in+name, "ϵͳ��ʾ", JOptionPane.PLAIN_MESSAGE);
						// �������������
						frame.setVisible(true);
						massage=1;
						login = �û���.getText();
						
						this.setVisible(false);
						
						rs.close();
						stmt.close();
						con.close();
						break;
					}
				}
				if(massage==0)
				{
					JOptionPane.showMessageDialog(this,"��������ʺŻ������������������룡","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);
				}
				con.close();
			}
			  catch(SQLException f)
			  {
				System.out.println(f);  
			  }
		}
	}
	if(e.getSource()==���)
	{
	setVisible(false);
	new Login();
	}
	if(e.getSource()==�˳�)
	{
		System.exit(0);
	}
	
	if(e.getSource()==ע��)
	{
		
		this.setVisible(false);
		new Regist();
	}
    }
	

}
