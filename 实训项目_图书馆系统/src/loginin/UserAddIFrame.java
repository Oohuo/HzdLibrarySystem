package loginin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dao.Dao;
import dao.book.Manager;
import system.CreateIcon;
import system.MyDocument;

public class UserAddIFrame extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField name;
	private JTextField password;

	public UserAddIFrame() {
		super();
		setIconifiable(true); // ���ô������С������������
		setClosable(true); // ���ô���ɹرգ���������
		setTitle("��ӹ���Ա"); // ���ô�����⣭��������
		setBounds(275, 75, 500, 225);

		// ������ͷͼƬ
		final JLabel logoLabel = new JLabel();
		ImageIcon userAddIcon = CreateIcon.add("tback.jpg");
		logoLabel.setIcon(userAddIcon);
		logoLabel.setOpaque(true);
		logoLabel.setBackground(Color.white);
		logoLabel.setPreferredSize(new Dimension(400, 60));
		getContentPane().add(logoLabel, BorderLayout.NORTH);

		// ����һ�������������
		final JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		getContentPane().add(panel);

		// �������������Ƕ�����1,���ڷ��÷ǰ�ť���
		final JPanel panel_1 = new JPanel();
		final GridLayout gridLayout = new GridLayout(2, 2);
		gridLayout.setVgap(20);
		panel_1.setLayout(gridLayout);
		panel.add(panel_1);

		final JLabel label_2 = new JLabel();
		label_2.setText("�û�����");
		panel_1.add(label_2);
		name = new JTextField(10);
		name.setDocument(new MyDocument(20));
		panel_1.add(name);

		final JLabel label_3 = new JLabel();
		label_3.setText("���룺");
		panel_1.add(label_3);
		password = new JTextField(10);
		password.setDocument(new MyDocument(15));
		panel_1.add(password);

		// ����������Ƕ��һ�����ڷ��Ű�ť�����
		final JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(450, 100));
		panel.add(panel_2);

		final JRadioButton radioButton1 = new JRadioButton();

		// �����������
		final JButton submit = new JButton();
		panel_2.add(submit);
		submit.setText("�ύ");
		submit.addActionListener((ActionListener) new ButtonAddListener(radioButton1));

		// �����������
		final JButton back = new JButton();
		panel_2.add(back);
		back.setText("����");
		back.addActionListener(new CloseActionListener());

		setVisible(true);
	}

	// �������水ť�����ڲ���
	class ButtonAddListener implements ActionListener {
		ButtonAddListener(JRadioButton button1) {
		}

		public void actionPerformed(final ActionEvent e) {

			Manager ma = new Manager();

			if (name.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "�û�������Ϊ��");
				return;
			}
			if (name.getText().length() > 20) {
				JOptionPane.showMessageDialog(null, "�û������ó��ȴ���20");
				name.setText("");
				return;
			}
			if (password.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
				return;
			}
			if (password.getText().length() > 15) {
				JOptionPane.showMessageDialog(null, "���볤�Ȳ��ô���15");
				password.setText("");
				return;
			}
			if (password.getText().length() < 6) {
				JOptionPane.showMessageDialog(null, "���볤�Ȳ���С��6");
				password.setText("");
				return;
			}
			ma.setName(name.getText().trim());
			ma.setPassword(password.getText().trim());

			try {
				if (Dao.insertManagetInfo(ma)) {
					JOptionPane.showMessageDialog(null, "��ӳɹ���");
					doDefaultCloseAction();
				} else {
					JOptionPane.showMessageDialog(null, "�û����Ѵ���");
					name.setText("");
				}
			} catch (NumberFormatException e1) {
				String message = e1.getMessage();
				int index = message.lastIndexOf(')');
				message = message.substring(index + 1);
				JOptionPane.showMessageDialog(null, message);
				e1.printStackTrace();
			}
		}
	}

	class CloseActionListener implements ActionListener { // ��ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}

}
