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
import dao.book.Book;
import login.BackgroundPanel;
import system.CreateIcon;
import system.MyDocument;

public class BookAddIFrame extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField author;
	private JTextField id;
	private JTextField sid;
	private JFormattedTextField in_date;
	private JTextField now_amount;
	private JTextField press;
	private JTextField price;
	private JTextField title;
	private JTextField total;

	public BookAddIFrame() {
		super();
		setIconifiable(true); // 设置窗体可最小化－－－必须
		setClosable(true); // 设置窗体可关闭－－－必须
		setTitle("书籍添加"); // 设置窗体标题－－－必须
		setBounds(275, 75, 500, 450);

		// 创建标头图片
		final JLabel logoLabel = new JLabel();
		ImageIcon readerAddIcon = CreateIcon.add("书籍添加.jpg");
		logoLabel.setIcon(readerAddIcon);
		logoLabel.setOpaque(true);
		logoLabel.setBackground(Color.BLACK);
		logoLabel.setPreferredSize(new Dimension(400, 60));
		getContentPane().add(logoLabel, BorderLayout.NORTH);
		
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
		panel_1.setPreferredSize(new Dimension(450, 200));
		panel.add(panel_1);

		final JLabel label_2 = new JLabel();
		label_2.setText("作     者：");
		label_2.setForeground(Color.BLACK); 
		panel_1.add(label_2);
		author = new JTextField();
		author.setDocument(new MyDocument(256));
		panel_1.add(author);

		final JLabel label_3 = new JLabel();
		label_3.setText("编     号：");
		panel_1.add(label_3);
		id = new JTextField();
		id.setDocument(new MyDocument(256));
		panel_1.add(id);

		final JLabel label_4 = new JLabel();
		label_4.setText("书架编号 ：");
		panel_1.add(label_4);
		sid = new JTextField();
		sid.setDocument(new MyDocument(256));
		panel_1.add(sid);

		final JLabel label_5 = new JLabel();
		label_5.setText("入库日期：");
		panel_1.add(label_5);
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		in_date = new JFormattedTextField();
		in_date.setValue("XXXX-XX-XX");
		in_date.addKeyListener(new DateListener());
		panel_1.add(in_date);

		final JLabel label_6 = new JLabel();
		label_6.setText("现存量：");
		panel_1.add(label_6);
		now_amount = new JTextField();
		now_amount.setDocument(new MyDocument(256));
		panel_1.add(now_amount);

		final JLabel label_7 = new JLabel();
		label_7.setText("出版社：");
		panel_1.add(label_7);
		press = new JTextField();
		press.setDocument(new MyDocument(256));
		panel_1.add(press);

		final JLabel label_8 = new JLabel();
		label_8.setText("价格：");
		panel_1.add(label_8);
		price = new JTextField();
		price.setDocument(new MyDocument(256));
		panel_1.add(price);

		final JLabel label_9 = new JLabel();
		label_9.setText("书名：");
		panel_1.add(label_9);
		title = new JTextField();
		title.setDocument(new MyDocument(256));
		panel_1.add(title);

		final JLabel label_10 = new JLabel();
		label_10.setText("总量：");
		panel_1.add(label_10);
		total = new JTextField();
		total.setDocument(new MyDocument(256));
		panel_1.add(total);

		// 在组件面板中嵌套一个用于放着按钮的面板
		final JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(450, 100));
		panel.add(panel_2);
		//panel_1.setLocation(100, 30);
		//panel_1.setSize(400,300);
		//
		bkadpanel.add(label_2);
		label_2.setSize(100, 20);
		label_2.setLocation(50, 50);
		bkadpanel.add(author);
		author.setSize(100, 20);
		author.setLocation(100,50);
		//
		bkadpanel.add(label_3);
		label_3.setSize(100, 20);
		label_3.setLocation(250, 50);
		bkadpanel.add(id);
		id.setSize(100, 20);
		id.setLocation(300, 50);
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
		bkadpanel.add(sid);
		sid.setSize(100, 20);
		sid.setLocation(100, 80);
		//
		bkadpanel.add(label_5);
		label_5.setSize(100, 20);
		label_5.setLocation(250, 80);
		bkadpanel.add(in_date);
		in_date.setSize(100, 20);
		in_date.setLocation(300, 80);
		//
		bkadpanel.add(label_6);
		label_6.setSize(100, 20);
		label_6.setLocation(50, 110);
		bkadpanel.add(now_amount);
		now_amount.setSize(100, 20);
		now_amount.setLocation(100, 110);
		//
		bkadpanel.add(label_7);
		label_7.setSize(100, 20);
		label_7.setLocation(250, 110);
		bkadpanel.add(press);
		press.setSize(100, 20);
		press.setLocation(300, 110);
		//
		bkadpanel.add(label_8);
		label_8.setSize(100, 20);
		label_8.setLocation(50, 140);
		bkadpanel.add(price);
		price.setSize(100, 20);
		price.setLocation(100, 140);
		//
		bkadpanel.add(label_9);
		label_9.setSize(100, 20);
		label_9.setLocation(250, 140);
		bkadpanel.add(title);
		title.setSize(100, 20);
		title.setLocation(300, 140);
		this.setVisible(true);
		this.setResizable(false);
		//
		bkadpanel.add(label_10);
		label_10.setSize(100, 20);
		label_10.setLocation(50, 170);
		bkadpanel.add(total);
		total.setSize(100, 20);
		total.setLocation(100, 170);
		
		this.setVisible(true);
		this.setResizable(false);

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

		//按钮
				bkadpanel.add(submit);
				submit.setSize(100, 20);
				submit.setLocation(50, 200);
				bkadpanel.add(back);
				back.setSize(100, 20);
				back.setLocation(250, 200);
				
		setVisible(true);
	}

	// 时间格式监听
	class DateListener extends KeyAdapter {
		public void focusGained(FocusEvent e) {
			in_date.setText("");
		}
	}

	// 创建保存按钮监听内部类
	class ButtonAddListener implements ActionListener {
		ButtonAddListener(JRadioButton button1) {
		}

		public void actionPerformed(final ActionEvent e) {

			Book book = new Book();

			if (author.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "作者名字不能为空");
				return;
			}
			if (id.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "图书编号不能为空");
				return;
			}
			if (sid.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "书架编号文本框不可为空");
				return;
			}
			if (in_date.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "日期不能空");
				return;
			}
			if (now_amount.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "现存量不能为空");
				return;
			}
			if (press.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "出版商不能为空");
				return;
			}
			if (price.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "单价不可为空");
				return;
			}
			if (Double.parseDouble(price.getText().trim()) < 0) {
				JOptionPane.showMessageDialog(null, "单价不可为负数");
				price.setText("");
				return;
			}
			if (title.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "书名不可为空");
				return;
			}
			if (Integer.parseInt(total.getText().trim()) <= 0) {
				JOptionPane.showMessageDialog(null, "总量应>=0，已有书籍添加总量可随意填");
				total.setText("");
				return;
			}
			book.setAuthor(author.getText().trim());
			book.setId(id.getText().trim());
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date = sdf.parse(in_date.getText().trim());
				Date sqlDate = new Date(date.getTime());
				book.setIn_date(sqlDate);
				// System.out.println(t);
			} catch (ParseException e2) {
				// System.out.println("in: " + t);
				e2.printStackTrace();
			}
			book.setNow_amount(Integer.parseInt(now_amount.getText().trim()));
			book.setPress(press.getText().trim());
			book.setPrice(Double.parseDouble(price.getText().trim()));
			book.setSid(sid.getText().trim());
			book.setTitle(title.getText().trim());
			book.setTotal(Integer.parseInt(total.getText().trim()));

			try {
				if (Dao.insertBookInfo(book, 1)) {
					JOptionPane.showMessageDialog(null, "添加成功！");
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

	class CloseActionListener implements ActionListener { // 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
}
