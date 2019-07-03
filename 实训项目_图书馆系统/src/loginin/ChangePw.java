package loginin;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.Dao;
import dao.book.Manager;
import login.LoginDialog;
import system.MyDocument;

public class ChangePw extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField oldPass;
	private JPasswordField newPass2;
	private JPasswordField newPass1;
	private JTextField username1;
	private Manager user = LoginDialog.getUser();// ��ȡ��½����Ա��Ϣ

	public ChangePw() {
		super();
		// ���ø������봰���ʼֵ
		this.setIconifiable(true);// �����ڲ����ڿ���ͼ��
		this.setTitle("��������");// ���ñ���
		this.setClosable(true);// �����Ƿ����ͨ��ĳ���û��رմ�JInternalFrame
		this.getContentPane().setLayout(new GridLayout(5, 1));// ���ò����岼��
		setBounds(300, 75, 300	, 228);

		// �����û���, ԭ���룬 �����룬ȷ������, ��ť���
		JPanel juser = new JPanel();
		JPanel orgpwd = new JPanel();
		JPanel newpwd = new JPanel();
		JPanel surepwd = new JPanel();
		JPanel bt = new JPanel();

		// ����һ����ǩ��ʾ��¼��
		final JLabel label_1 = new JLabel();
		label_1.setFont(new Font("", Font.PLAIN, 14));
		label_1.setText(" �� ¼ ��:");
		juser.add(label_1);

		// ����һ���ı������������¼��
		username1 = new JTextField(user.getName());
		username1.setEditable(false);
		username1.setSize(new Dimension(150, 25));
		username1.setBorder(null);
		juser.add(username1);
		getContentPane().add(juser);

		// ����һ���������ǩ
		final JLabel label_2 = new JLabel();
		label_2.setFont(new Font("", Font.PLAIN, 14));
		label_2.setText("�� �� ��:");
		orgpwd.add(label_2);

		// ����һ���������������������
		oldPass = new JPasswordField(10);
		oldPass.setDocument(new MyDocument(15));
		orgpwd.add(oldPass);
		getContentPane().add(orgpwd);

		// ����һ��������ı�ǩ
		final JLabel label_3 = new JLabel();
		label_3.setFont(new Font("", Font.PLAIN, 14));
		label_3.setText("�� �� ��:");
		newpwd.add(label_3);

		// ����һ������������������ı���
		newPass1 = new JPasswordField(10);
		newPass1.setDocument(new MyDocument(15));
		newPass1.setFont(new Font("", Font.PLAIN, 14));
		newpwd.add(newPass1);
		getContentPane().add(newpwd);

		// ���ڴ���һ��ȷ��������ı�ǩ
		final JLabel label_4 = new JLabel();
		label_4.setFont(new Font("", Font.PLAIN, 14));
		label_4.setText("ȷ��������:");
		surepwd.add(label_4);

		// ���ڴ���һ����������ȷ��������������
		newPass2 = new JPasswordField(10);
		newPass2.setDocument(new MyDocument(15));
		newPass2.setFont(new Font("", Font.PLAIN, 14));
		surepwd.add(newPass2);
		getContentPane().add(surepwd);

		// ����ȷ�ϰ�ť����
		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (oldPass.getText().equals(user.getPassword())) {
					if (newPass1.getText().equals(newPass2.getText())) {
						if (newPass1.getText().trim().length() < 6) {
							JOptionPane.showMessageDialog(getContentPane(), "�����볤�Ȳ���С��6");
							newPass1.setText(null);
							newPass2.setText(null);
							return;
						}
						Dao.changePwd(user.getName(), user.getPassword(), newPass1.getText());
						oldPass.setText(null);
						newPass1.setText(null);
						newPass2.setText(null);
						JOptionPane.showMessageDialog(getContentPane(), "�����޸ĳɹ�");
						doDefaultCloseAction();
					} else {
						JOptionPane.showMessageDialog(getContentPane(), "�����������벻һ�£�����������!");
						newPass1.setText("");
						newPass2.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(getContentPane(), "���������������ȷ������!");
				}
			}
		});
		button.setText("ȷ��");

		bt.add(button);

		// ������д��ť����
		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				oldPass.setText(null);
				newPass1.setText(null);
				newPass2.setText(null);
			}

		});
		button_1.setText("��д");
		bt.add(button_1);
		getContentPane().add(bt);

		setVisible(true);
	}

}
