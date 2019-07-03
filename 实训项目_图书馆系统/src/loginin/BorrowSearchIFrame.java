package loginin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import dao.Dao;
import dao.book.Borrow;
import system.Item;

public class BorrowSearchIFrame extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField textField_1;

	private JTable table_1, table_2;

	private JComboBox<String> choice;

	private JScrollPane scrollPane, scrollPane_1;

	String booksearch[] = { "��������", "�黹����", "���", "ͼ����", "���߱��", "����Ա�û���" };

	private Object[][] getselect(List<?> list) {
		Object[][] s = new Object[list.size()][6];
		for (int i = 0; i < list.size(); i++) {
			Borrow borrow = (Borrow) list.get(i);
			s[i][0] = borrow.getBorrowDate();
			s[i][1] = borrow.getReturnDate();
			s[i][2] = borrow.getId();
			s[i][3] = borrow.getBid();
			s[i][4] = borrow.getRid();
			s[i][5] = borrow.getName();
		}
		return s;
	}

	public BorrowSearchIFrame() {
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("���Ĳ�ѯ");
		setBounds(275, 175, 500, 400);

		setVisible(true);

		final JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(tabbedPane);

		final JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout());
		tabbedPane.addTab("������ѯ", null, panel_1, null);

		final JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(null, "��ѡ���ѯ��Ŀ", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		panel_1_1.setPreferredSize(new Dimension(0, 50));
		panel_1.add(panel_1_1, BorderLayout.NORTH);
		choice = new JComboBox<String>();
		choice.addItem("���߱��");
		choice.addItem("���ı��");
		panel_1_1.add(choice);
		textField_1 = new JTextField();
		textField_1.setColumns(20);
		panel_1_1.add(textField_1);

		final JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "��ѯ�����ʾ", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		panel_1.add(panel);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(400, 200));
		panel.add(scrollPane_1);

		final JPanel panel_2_1 = new JPanel();
		panel_2_1.setPreferredSize(new Dimension(0, 50));
		panel_1.add(panel_2_1, BorderLayout.SOUTH);

		final JButton button = new JButton();
		button.setText("��ѯ");
		panel_2_1.add(button);

		textField_1.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyChar() == '\n') {
					button.doClick();
				}
			}
		});

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String name = (String) choice.getSelectedItem();
				if (name.equals("���߱��")) {

					Object[][] results = getselect(Dao.getBorrowInfo(new Item(null, textField_1.getText())));
					table_2 = new JTable(results, booksearch);

					scrollPane_1.setViewportView(table_2);
				} else if (name.equals("���ı��")) {

					Object[][] results = getselect(Dao.getBorrowInfo(new Item(textField_1.getText(), null)));
					table_2 = new JTable(results, booksearch);

					scrollPane_1.setViewportView(table_2);
				}
			}

		});

		final JButton button_1 = new JButton();
		button_1.setText("����");
		panel_2_1.add(button_1);
		button_1.addActionListener(new CloseActionListener());

		setVisible(true);

		final JPanel panel_2 = new JPanel();
		tabbedPane.addTab("��ʾȫ��������Ϣ", null, panel_2, null);

		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(450, 250));
		panel_2.add(scrollPane);

		Object[][] results = getselect(Dao.getAllBorrowInfo());
		String booksearch[] = { "��������", "�黹����", "���", "ͼ����", "���߱��", "����Ա�û���" };
		table_1 = new JTable(results, booksearch);

		scrollPane.setViewportView(table_1);

	}

	class CloseActionListener implements ActionListener { // ��ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
}
