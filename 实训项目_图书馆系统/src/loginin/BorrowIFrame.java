package loginin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
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
import login.BackgroundPanel;
import system.CreateIcon;
import system.MyDocument;

public class BorrowIFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JFormattedTextField borrowDate;
	private JTextField id;
	private JTextField bid;
	private JTextField rid;
	private JTextField name;

	public BorrowIFrame() {
		super();
		setIconifiable(true); // 设置窗体可最小化－－－必须
		setClosable(true); // 设置窗体可关闭－－－必须
		setTitle("借阅信息添加"); // 设置窗体标题－－－必须
		setBounds(275, 75, 500, 450);

		// 创建标头图片
		final JLabel logoLabel = new JLabel();
		ImageIcon readerAddIcon = CreateIcon.add("借阅信息修改.jpg");
		logoLabel.setIcon(readerAddIcon);
		logoLabel.setOpaque(true);
		logoLabel.setBackground(Color.white);
		logoLabel.setPreferredSize(new Dimension(400, 60));
		getContentPane().add(logoLabel, BorderLayout.NORTH);
		
		//
		//添加面板主背景
		Image  imagebc = new ImageIcon("inframebc.jpg").getImage();
		 JPanel bkadpanel = new BackgroundPanel(imagebc);
		bkadpanel.setLayout(null);

		getContentPane().add(bkadpanel);

		// 设置一个添加组件的面板
		final JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		

		// 在添加组件面板中嵌套面板1,用于放置非按钮组件
		final JPanel panel_1 = new JPanel();
		final GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(15);
		gridLayout.setHgap(10);
		panel_1.setLayout(gridLayout);
		panel_1.setPreferredSize(new Dimension(450, 100));
		panel.add(panel_1);

		final JLabel label_2 = new JLabel();
		label_2.setText("借书日期：");
		panel_1.add(label_2);
		borrowDate = new JFormattedTextField();
		borrowDate.setValue("XXXX-XX-XX");
		borrowDate.addKeyListener(new DateListener());
		panel_1.add(borrowDate);

		final JLabel label_3 = new JLabel();
		label_3.setText("编     号：");
		panel_1.add(label_3);
		id = new JTextField();
		id.setDocument(new MyDocument(256));
		panel_1.add(id);

		final JLabel label_4 = new JLabel();
		label_4.setText("书本编号 ：");
		panel_1.add(label_4);
		bid = new JTextField();
		bid.setDocument(new MyDocument(256));
		panel_1.add(bid);

		final JLabel label_5 = new JLabel();
		label_5.setText("读者编号：");
		panel_1.add(label_5);
		rid = new JTextField();
		rid.setDocument(new MyDocument(256));
		panel_1.add(rid);

		final JLabel label_6 = new JLabel();
		label_6.setText("管理员名字：");
		panel_1.add(label_6);
		name = new JTextField();
		name.setDocument(new MyDocument(256));
		panel_1.add(name);

		// 在组件面板中嵌套一个用于放着按钮的面板
		final JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(450, 100));
		panel.add(panel_2);
		
		bkadpanel.add(label_2);
		label_2.setSize(100, 20);
		label_2.setLocation(180, 100);
		bkadpanel.add(borrowDate);
		borrowDate.setSize(100, 20);
		borrowDate.setLocation(250, 100);
		//
		bkadpanel.add(label_3);
		label_3.setSize(100, 20);
		label_3.setLocation(180, 130);
		bkadpanel.add(id);
		id.setSize(100, 20);
		id.setLocation(250, 130);
		//
		bkadpanel.add(label_4);
		label_4.setSize(100, 20);
		label_4.setLocation(180, 160);
		bkadpanel.add(bid);
		bid.setSize(100, 20);
		bid.setLocation(250, 160);
		//
		bkadpanel.add(label_5);
		label_5.setSize(100, 20);
		label_5.setLocation(180, 190);
		bkadpanel.add(rid);
		rid.setSize(100, 20);
		rid.setLocation(250, 190);
		//
		bkadpanel.add(label_6);
		label_6.setSize(100, 20);
		label_6.setLocation(180, 220);
		bkadpanel.add(name);
		name.setSize(100, 20);
		name.setLocation(250, 220);
		//
		
		

		final JRadioButton radioButton1 = new JRadioButton();

		// 创建保存面板
		final JButton submit = new JButton();
		panel_2.add(submit);
		submit.setText("提交");
		submit.addActionListener((ActionListener) new ButtonAddListener(radioButton1));

		// 创建返回面板
		final JButton back = new JButton();
		panel_2.add(back);
		back.setText("返回");
		back.addActionListener(new CloseActionListener());

		setVisible(true);
		///
		bkadpanel.add(submit);
		submit.setSize(100, 20);
		submit.setLocation(250, 250);
		bkadpanel.add(back);
		back.setSize(100, 20);
		back.setLocation(250, 280);
		
		
	}

	// 时间格式监听
	class DateListener extends KeyAdapter {
		public void focusGained(FocusEvent e) {
			borrowDate.setText("");
		}
	}

	// 创建保存按钮监听内部类
	class ButtonAddListener implements ActionListener {
		ButtonAddListener(JRadioButton button1) {
		}

		public void actionPerformed(final ActionEvent e) {

			Borrow borrow = new Borrow();

			if (borrowDate.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "借书日期不能为空");
				return;
			}
			if (id.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "编号不能为空");
				return;
			}
			if (bid.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "图书编号不可为空");
				return;
			}
			if (rid.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "读者编号不能为空");
				return;
			}
			if (name.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "管理员不能为空");
				return;
			}
			borrow.setId(id.getText().trim());
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date = sdf.parse(borrowDate.getText().trim());
				Date sqlDate = new Date(date.getTime());
				borrow.setBorrowDate(sqlDate);
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "日期格式错误！");
				e2.printStackTrace();
			}
			borrow.setBid(bid.getText().trim());
			borrow.setRid(rid.getText().trim());
			borrow.setName(name.getText().trim());

			try {
				if (Dao.insertBorrowInfo(borrow)) {
					JOptionPane.showMessageDialog(null, "添加成功！");
					doDefaultCloseAction();
				}
				else
					JOptionPane.showMessageDialog(null, "无该书信息！");
				doDefaultCloseAction();
					
			} catch (NumberFormatException e1) {
				String message = e1.getMessage();
				int index = message.lastIndexOf(')');
				message = message.substring(index + 1);
				JOptionPane.showMessageDialog(null, message);
				e1.printStackTrace();
			}
		}
	}

	class CloseActionListener implements ActionListener { // 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
}
