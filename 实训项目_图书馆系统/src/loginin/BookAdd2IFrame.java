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
import dao.book.Book;
import login.BackgroundPanel;
import system.CreateIcon;

public class BookAdd2IFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField id;
	private JTextField num;

	public BookAdd2IFrame() {
		super();
		setIconifiable(true); // ���ô������С������������
		setClosable(true); // ���ô���ɹرգ���������
		setTitle("����ͼ�����"); // ���ô�����⣭��������
		setBounds(275, 105, 500, 450);
		

		// ������ͷͼƬ
		final JLabel logoLabel = new JLabel();
		ImageIcon readerAddIcon = CreateIcon.add("�鼮���.jpg");
		logoLabel.setIcon(readerAddIcon);
		logoLabel.setOpaque(true);
		logoLabel.setBackground(Color.white);
		logoLabel.setPreferredSize(new Dimension(400, 60));
		getContentPane().add(logoLabel, BorderLayout.NORTH);
		
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
		final GridLayout gridLayout = new GridLayout(2, 2);
		gridLayout.setVgap(20);
		panel_1.setLayout(gridLayout);
		panel.add(panel_1);

		final JLabel label_2 = new JLabel();
		label_2.setText("ͼ���ţ�");
		panel_1.add(label_2);
		id = new JTextField(10);
		panel_1.add(id);

		final JLabel label_3 = new JLabel();
		label_3.setText("���������");
		panel_1.add(label_3);
		num = new JTextField(10);
		panel_1.add(num);

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
		
		bkadpanel.add(label_2);
		label_2.setSize(100, 20);
		label_2.setLocation(180, 100);
		bkadpanel.add(id);
		id.setSize(100, 20);
		id.setLocation(250, 100);
		//
		bkadpanel.add(label_3);
		label_3.setSize(100, 20);
		label_3.setLocation(180, 150);
		bkadpanel.add(num);
		num.setSize(100, 20);
		num.setLocation(250, 150);
		//
		bkadpanel.add(submit);
		submit.setSize(100, 20);
		submit.setLocation(250,180);
		bkadpanel.add(back);
		back.setSize(100, 20);
		back.setLocation(250,220);
		
		

		setVisible(true);
	}

	// �������水ť�����ڲ���
	class ButtonAddListener implements ActionListener {
		ButtonAddListener(JRadioButton button1) {
		}

		public void actionPerformed(final ActionEvent e) {

			Book book = new Book();

			if (id.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "ͼ���Ų���Ϊ��");
				return;
			}
			if (Integer.parseInt(num.getText().trim()) <= 0) {
				JOptionPane.showMessageDialog(null, "ͼ�������������0");
				return;
			}
			book.setId(id.getText().trim());

			try {
				if (Dao.insertBookInfo(book, Integer.parseInt(num.getText().trim()))) {
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
