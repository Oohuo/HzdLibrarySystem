package login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SettingDialog extends JFrame {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	private static JTextField ip; // ip��ַ
	private static JTextField port; // �˿ں�
	private static JTextField user; // ���ݿ��½��
	private static JTextField pwd; // ���ݿ��½����
	private static JButton ok; // ȷ�ϰ�ť
	private static JButton exit; // ���ذ�ť

	public SettingDialog() {
		super("�޸������ļ�");

		// �޸Ĵ������Ͻ�ͼƬ
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("src/res/hzd.jpg");
		this.setIconImage(image);

		// ��ȡ��Ļ��С
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screensize.getWidth();
		int height = (int) screensize.getHeight();
		this.setBounds(width / 2 -150, height/ 2 - 60, 300, 200);

		// ���������
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		this.getContentPane().add(mainPanel);

		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(2, 2));

		mainPanel.add(textPanel, BorderLayout.CENTER);

		// �����ĸ��ı���
		JPanel userPanel = new JPanel();
		JPanel pwdPanel = new JPanel();
		JPanel ipPanel = new JPanel();
		JPanel portPanel = new JPanel();

		// ip��
		final JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);// �������ˮƽ���뷽ʽ
		label.setText("ip��ַ: ");
		ipPanel.add(label);
		ip = new JTextField(10);
		ipPanel.add(ip);
		textPanel.add(ipPanel);

		// port��
		final JLabel label_1 = new JLabel();
		label_1.setHorizontalAlignment(SwingConstants.CENTER);// �������ˮƽ���뷽ʽ
		label_1.setText("�˿ں�: ");
		portPanel.add(label_1);
		port = new JTextField(10);
		portPanel.add(port);
		textPanel.add(portPanel);

		// user��
		final JLabel label_2 = new JLabel();
		label_2.setHorizontalAlignment(SwingConstants.CENTER);// �������ˮƽ���뷽ʽ
		label_2.setText("���ݿ��½��: ");
		userPanel.add(label_2);
		user = new JTextField(10);
		userPanel.add(user);
		textPanel.add(userPanel);

		// pwd��
		final JLabel label_3 = new JLabel();
		label_3.setHorizontalAlignment(SwingConstants.CENTER);// �������ˮƽ���뷽ʽ
		label_3.setText("���ݿ�����: ");
		pwdPanel.add(label_3);
		pwd = new JTextField(10);
		pwdPanel.add(pwd);
		textPanel.add(pwdPanel);

		JPanel buttonPanel = new JPanel();
		// ����ȷ�ϰ�ť
		ok = new JButton();
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (ip.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "ip����Ϊ��");
						return;
					}
					if (port.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "�˿ںŲ���Ϊ��");
						return;
					}
					if (user.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "���ݿ��½������Ϊ��");
						return;
					}
					PrintWriter output = new PrintWriter("setting.txt");
					output.println(ip.getText().trim());
					output.println(port.getText().trim());
					output.println(user.getText().trim());
					output.println(pwd.getText().trim());
					System.out.println(ip.getText().trim());
					output.close();
					JOptionPane.showMessageDialog(null, "�޸������ļ��ɹ�");
					setVisible(false);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		ok.setText("ȷ��");
		buttonPanel.add(ok);

		// �����˳���ť
		exit = new JButton();
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		exit.setText("�˳�");
		buttonPanel.add(exit);

		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		this.setVisible(true);
	}
}
