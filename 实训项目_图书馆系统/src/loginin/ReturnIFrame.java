package loginin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dao.Dao;
import dao.book.Borrow;
import system.CreateIcon;
import system.MyDocument;

public class ReturnIFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField id;
	private JFormattedTextField return_date;

	public ReturnIFrame() {
		super();
		setIconifiable(true); // ���ô������С������������
		setClosable(true); // ���ô���ɹرգ���������
		setTitle("��ӹ黹��Ϣ"); // ���ô�����⣭��������
		setBounds(275, 175, 500, 225);

		// ������ͷͼƬ
		final JLabel logoLabel = new JLabel();
		ImageIcon readerAddIcon = CreateIcon.add("tback.jpg");
		logoLabel.setIcon(readerAddIcon);
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
		label_2.setText("���ļ�¼��ţ�");
		panel_1.add(label_2);
		id = new JTextField(10);
		id.setDocument(new MyDocument(256));
		panel_1.add(id);

		final JLabel label_3 = new JLabel();
		label_3.setText("�黹���ڣ�");
		panel_1.add(label_3);
		return_date = new JFormattedTextField();
		return_date.setValue("XXXX-XX-XX");
		return_date.addKeyListener(new DateListener());
		panel_1.add(return_date);

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

	// ʱ���ʽ����
	class DateListener extends KeyAdapter {
		public void focusGained(FocusEvent e) {
			return_date.setText("");
		}
	}

	// �������水ť�����ڲ���
	class ButtonAddListener implements ActionListener {
		ButtonAddListener(JRadioButton button1) {
		}

		public void actionPerformed(final ActionEvent e) {

			Borrow bo = new Borrow();

			if (id.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "�黹��¼��Ų���Ϊ��");
				return;
			}
			if (return_date.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "�黹���ڲ���Ϊ��");
				return;
			}
			// System.out.println(id.getText());
			bo.setId(id.getText().trim());
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date = sdf.parse(return_date.getText().trim());
				Date sqlDate = new Date(date.getTime());
				bo.setReturnDate(sqlDate);
				// System.out.println(t);
			} catch (ParseException e2) {
				// System.out.println("in: " + t);
				JOptionPane.showMessageDialog(null, "���ڸ�ʽ����");
				e2.printStackTrace();
			}

			try {
				// System.out.println(bo.getBid());
				// System.out.println(bo.getReturnDate());
				if (!Dao.changeReturn(bo.getId(), bo.getReturnDate())) {
					JOptionPane.showMessageDialog(null, "��ӳɹ���");
					doDefaultCloseAction();
				} else {
					JOptionPane.showMessageDialog(null, "���ʧ�ܣ�");
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
