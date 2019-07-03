package login;

import java.awt.*;
import java.awt.event.*;

import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dao.Dao;
import dao.book.Manager;
import system.CreateIcon;
import system.MyDocument;

public class LoginDialog extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	private JPasswordField password;  //���������
	private JTextField username;   //�����û���
	private JButton login;  //������½��ť
	private JButton exit;  //�����˳���ť
	private JButton change; //�޸������ļ�
	private static Manager user;  //����Ա������
	
	//��½��ť�¼�
	class BookLoginAction implements ActionListener{
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			try {
				//System.out.println(username.getText());
				//System.out.println(password.getText());
				setUser(Dao.checkLogin(username.getText(), password.getText()));
				if(user.getName() != null){ // Ϊ��˵��δ�ҵ�
					
					String name= username.getText();
					String in  = "������ҽҩ��ѧͼ��ݻ�ӭ��:     ";
					
					JOptionPane.showMessageDialog(null, in+name, "ϵͳ��ʾ",JOptionPane.PLAIN_MESSAGE);
					
					// ����������
					MainDialog frame = new MainDialog();
					// �������������
					frame.setVisible(true);
					// ���ص�½����
					LoginDialog.this.setVisible(false);
					// System.out.println("��½�ɹ���~");
				}else{
					JOptionPane.showMessageDialog(null, "������û���������������������롣");
					password.setText("");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	// �˳���ť�¼�
	private class ExitAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	// �޸������¼�
	private class ChangeSetting implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new SettingDialog();
		}
	}
	
	// �õ��û�
	public static Manager getUser(){
		return user;
	}
		
	public static void setUser(Manager user){
		LoginDialog.user = user;
	}
	
	public LoginDialog() {
		

		// ��ʼ����½�������
		super("ͼ��ݹ���ϵͳ��½");
		final BorderLayout borderLayout = new BorderLayout();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(borderLayout);// �������ݲ���
		
		//�޸Ĵ������Ͻ�ͼƬ
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Image image = toolkit.getImage("src/res/hzd.jpg");
				this.setIconImage(image);
		// ��ȡ��Ļ��С�Ծ������ڳ�ʼλ��
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screensize.getWidth();
		int height = (int)screensize.getHeight();
		this.setBounds(width / 2 -500, height/ 2 - 320, 1000, 640);
		
		// ����һ�������
		final JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(0,0,0,0)); // �����Ͻǲ���
		this.getContentPane().add(mainPanel);
		
		// ����Ϸ�����
		final JLabel backLabel = new JLabel();
		ImageIcon loginIcon = CreateIcon.add("login.jpg");
		backLabel.setIcon(loginIcon);
		backLabel.setOpaque(true); // �ڲ��������������ϻ���
		backLabel.setBackground(Color.white);
		backLabel.setPreferredSize(new Dimension(260,60)); // ��260����60
		mainPanel.add(backLabel, BorderLayout.NORTH);
		
		//������������
		Image  imagebc = new ImageIcon("back2.jpg").getImage();
		 JPanel toolspanel = new BackgroundPanel(imagebc);
		toolspanel.setLayout(null);

	
		 mainPanel.add(toolspanel, BorderLayout.CENTER); 
		
		// ����һ��3x1���񲼾���壬
		final JPanel panel_1 = new JPanel();
		final GridLayout gridLayout = new GridLayout(3,3);
		panel_1.setLayout(gridLayout);
		panel_1.setLocation(400, 200);
		panel_1.setSize(200,150);
		
		// �����û���, �������
		JPanel user = new JPanel();
		JPanel pwd = new JPanel();
		
		// �����û�label��ǩ
		final JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);//�������ˮƽ���뷽ʽ
		label.setText("��  ��  �� :    ");
		
		user.add(label);
		
		// �����û���
		username = new JTextField(10);
		username.setDocument(new MyDocument(20));
		user.add(username);
		
		panel_1.add(user);
		
		// ��������label��ǩ
		final JLabel label_1 = new JLabel();
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setText("��         �� :    ");
		pwd.add(label_1);
		
		// ���������
		password = new JPasswordField(10);
		password.setDocument(new MyDocument(15));
		password.setEchoChar('*');
		// ����enter��
		password.addKeyListener(new KeyAdapter(){
			 public void keyPressed(final KeyEvent e) {
				 if(e.getKeyChar() == '\n'){
					 login.doClick();
				 }
			 }
		});
		pwd.add(password);
		
		panel_1.add(pwd);
		
		// ������ť���
		final JPanel panel_2 = new JPanel();
		
		// ������½��ť
		login = new JButton();
		login.addActionListener(new BookLoginAction());
		login.setText("��½");
		panel_2.add(login);
		
		// �����˳���ť
		exit = new JButton();
		exit.addActionListener(new ExitAction());
		exit.setText("�˳�");
		panel_2.add(exit);
		
		// �����޸İ�ť
		change = new JButton();
		change.addActionListener(new ChangeSetting());
		change.setText("����");
		panel_2.add(change);
		
		// ���뵽���粼����
		panel_1.add(panel_2);
		toolspanel.add(panel_1);
		
		
		// ������ʾ��ʽ
		this.setVisible(true);
		this.setResizable(false);
		// ���������£���ʡ����ʱ��
		new Dao();
	}
}
