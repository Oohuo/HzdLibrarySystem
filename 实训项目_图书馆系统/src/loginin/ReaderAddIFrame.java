package loginin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
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
import dao.book.Reader;
import login.BackgroundPanel;
import system.CreateIcon;
import system.MyDocument;

public class ReaderAddIFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField address;
	private JTextField id;
	private JTextField name;
	private JTextField own;
	private JTextField phone;
	private JTextField sex;

	public ReaderAddIFrame() {
		super();
		setIconifiable(true); // ���ô������С������������
		setClosable(true); // ���ô���ɹرգ���������
		setTitle("������Ϣ���"); // ���ô�����⣭��������
		setBounds(275, 75, 500, 450);

		// ������ͷͼƬ
		final JLabel logoLabel = new JLabel();
		ImageIcon readerAddIcon = CreateIcon.add("������Ϣ���.jpg");
		logoLabel.setIcon(readerAddIcon);
		logoLabel.setOpaque(true);
		logoLabel.setBackground(Color.white);
		logoLabel.setPreferredSize(new Dimension(400, 60));
		getContentPane().add(logoLabel, BorderLayout.NORTH);
		
		//
		//������������
		Image  imagebc = new ImageIcon("inframebc.jpg").getImage();
		 JPanel bkadpanel = new BackgroundPanel(imagebc);
		bkadpanel.setLayout(null);

		getContentPane().add(bkadpanel);

		// ����һ�������������
		final JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		//getContentPane().add(panel);

		// �������������Ƕ�����1,���ڷ��÷ǰ�ť���
		final JPanel panel_1 = new JPanel();
		final GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(15);
		gridLayout.setHgap(10);
		panel_1.setLayout(gridLayout);
		panel_1.setPreferredSize(new Dimension(450, 100));
		panel.add(panel_1);

		final JLabel label_2 = new JLabel();
		label_2.setText("��ַ��");
		panel_1.add(label_2);
		address = new JTextField();
		address.setDocument(new MyDocument(256));
		panel_1.add(address);

		final JLabel label_3 = new JLabel();
		label_3.setText("��     �ţ�");
		panel_1.add(label_3);
		id = new JTextField();
		id.setDocument(new MyDocument(256));
		panel_1.add(id);

		final JLabel label_4 = new JLabel();
		label_4.setText("���� ��");
		panel_1.add(label_4);
		name = new JTextField();
		name.setDocument(new MyDocument(256));
		panel_1.add(name);

		final JLabel label_5 = new JLabel();
		label_5.setText("Ƿ�");
		panel_1.add(label_5);
		own = new JTextField();
		own.setDocument(new MyDocument(256));
		panel_1.add(own);

		final JLabel label_6 = new JLabel();
		label_6.setText("�绰��");
		panel_1.add(label_6);
		phone = new JTextField();
		phone.setDocument(new MyDocument(256));
		panel_1.add(phone);

		final JLabel label_7 = new JLabel();
		label_7.setText("�Ա�");
		panel_1.add(label_7);
		sex = new JTextField();
		sex.setDocument(new MyDocument(256));
		panel_1.add(sex);

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
		
		bkadpanel.add(label_2);
		label_2.setSize(100, 20);
		label_2.setLocation(50, 50);
		bkadpanel.add(address);
		address.setSize(100, 20);
		address.setLocation(100,50);
		//
		bkadpanel.add(label_3);
		label_3.setSize(100, 20);
		label_3.setLocation(250, 50);
		bkadpanel.add(id);
		id.setSize(100, 20);
		id.setLocation(300, 50);
	
		//
		bkadpanel.add(label_4);
		label_4.setSize(100, 20);
		label_4.setLocation(50, 80);
		bkadpanel.add(name);
		name.setSize(100, 20);
		name.setLocation(100, 80);
		//
		bkadpanel.add(label_5);
		label_5.setSize(100, 20);
		label_5.setLocation(250, 80);
		bkadpanel.add(own);
		own.setSize(100, 20);
		own.setLocation(300, 80);
		//
		bkadpanel.add(label_6);
		label_6.setSize(100, 20);
		label_6.setLocation(50, 110);
		bkadpanel.add(phone);
		phone.setSize(100, 20);
		phone.setLocation(100, 110);
		//
		bkadpanel.add(label_7);
		label_7.setSize(100, 20);
		label_7.setLocation(250, 110);
		bkadpanel.add(sex);
		sex.setSize(100, 20);
		sex.setLocation(300, 110);
		
		//��ť
		bkadpanel.add(submit);
		submit.setSize(100, 20);
		submit.setLocation(100, 200);
		bkadpanel.add(back);
		back.setSize(100, 20);
		back.setLocation(300, 200);
		
	}

	// �������水ť�����ڲ���
	class ButtonAddListener implements ActionListener {
		ButtonAddListener(JRadioButton button1) {
		}

		public void actionPerformed(final ActionEvent e) {

			Reader re = new Reader();

			if (address.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "��ַ����Ϊ��");
				return;
			}
			if (id.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "��Ų���Ϊ��");
				return;
			}
			if (name.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "���������ı��򲻿�Ϊ��");
				return;
			}
			if (own.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "Ƿ��ܿ�");
				return;
			}
			if (phone.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "�绰����Ϊ��");
				return;
			}
			if (sex.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "�Ա���Ϊ��");
				return;
			}
			re.setAddress(address.getText().trim());
			re.setId(id.getText().trim());
			re.setName(name.getText().trim());
			re.setOwn(Double.parseDouble(own.getText().trim()));
			re.setPhone(phone.getText().trim());
			re.setSex(sex.getText().trim());

			try {
				if (Dao.insertReaderInfo(re)) {
					JOptionPane.showMessageDialog(null, "��ӳɹ���");
					doDefaultCloseAction();
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
