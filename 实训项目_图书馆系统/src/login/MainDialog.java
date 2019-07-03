package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import system.CreateIcon;
import system.MenuActions;

public class MainDialog extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();

	// ����Ӵ���
	public static void addIFrame(JInternalFrame iframe) {
		DESKTOP_PANE.add(iframe);
	}

	

	// ��ʼ��������
	public MainDialog() {
		super("ͼ��ܹ���ϵͳ");
		// ���ùرշ�ʽ
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// ���ô����´οɼ�ʱ����λ��
		setLocationByPlatform(true);

		// ��ȡ��Ļ��С�Ծ������ڳ�ʼλ��
				Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
				int width = (int)screensize.getWidth();
				int height = (int)screensize.getHeight();
				this.setBounds(width / 2 -500, height/ 2 - 320, 1000, 670);
        

		// �����˵������
		JMenuBar menuBar = this.createMenu();
		this.setJMenuBar(menuBar);

		// ���������������
		JToolBar toolBar = this.createToolBar();
		this.getContentPane().add(toolBar, BorderLayout.NORTH);

		// �޸Ĵ������Ͻ�ͼƬ
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("src/res/hzd.jpg");
		this.setIconImage(image);
		// ���崰�ڱ����ı�ǩ
		final JLabel label = new JLabel();
		label.setBounds(0, 0, 0, 0);
		label.setIcon(null);

		// ��ӱ���
		DESKTOP_PANE.addComponentListener(new ComponentAdapter() {
			public void componentResized(final ComponentEvent e) {
				Dimension size = e.getComponent().getSize();
				label.setSize(size);
				ImageIcon loginIcon = CreateIcon.add("back.jpg");
				label.setIcon(loginIcon);
				label.setOpaque(false); // �ڲ��������������ϻ���
				label.setBackground(Color.white);
			}
		});

		DESKTOP_PANE.add(label, new Integer(Integer.MIN_VALUE));
		getContentPane().add(DESKTOP_PANE);
	}

	// �����˵���
	private JMenuBar createMenu() {
		JMenuBar menuBar = new JMenuBar();

		// �����鼮����
		JMenu bookMenu = new JMenu("�鼮����(B)");
		bookMenu.setMnemonic(KeyEvent.VK_B);
		bookMenu.add(MenuActions.BOOK_ADD);
		bookMenu.add(MenuActions.BOOK_ADD2);
		bookMenu.add(MenuActions.BOOK_PRICE);
		bookMenu.add(MenuActions.BOOK_SEARCH);

		// ��������֤����
		JMenu readerMenu = new JMenu("����֤����(R)");
		readerMenu.setMnemonic(KeyEvent.VK_R);
		readerMenu.add(MenuActions.READER_ADD);
		readerMenu.add(MenuActions.READER_OWN);
		readerMenu.add(MenuActions.READER_SEARCH);

		// ������ܹ���
		JMenu bsMenu = new JMenu("��ܹ���(S)");
		bsMenu.setMnemonic(KeyEvent.VK_S);
		bsMenu.add(MenuActions.BS_ADD);
		bsMenu.add(MenuActions.BS_SEARCH);

		// ����һ��ͼ����Ĺ���Ĳ˵�����ֱ���¼�
		JMenu borrowManageMenu = new JMenu();
		borrowManageMenu.setText("���Ĺ���(O)");
		borrowManageMenu.setMnemonic(KeyEvent.VK_O);
		borrowManageMenu.add(MenuActions.BORROW);
		borrowManageMenu.add(MenuActions.RETURN);
		borrowManageMenu.add(MenuActions.BORROW_SEARCH);

		// ����һ��ϵͳ����˵��һ���û���������
		JMenu sysManageMenu = new JMenu();
		sysManageMenu.setText("ϵͳ����(M)");
		sysManageMenu.setMnemonic(KeyEvent.VK_M);

		JMenu userManageMItem = new JMenu("�û�����");
		userManageMItem.add(MenuActions.USER_ADD);
		userManageMItem.add(MenuActions.MODIFY_PASSWORD);

		sysManageMenu.add(userManageMItem);
		
		sysManageMenu.add(MenuActions.EXIT);
		sysManageMenu.add(MenuActions.Switch_user);
		/*JMenu Switchuser = new JMenu();
		sysManageMenu.setText("�л��û�(ESC)");
		sysManageMenu.setMnemonic(KeyEvent.VK_ESCAPE);*/

		// ���˵������˵���
		menuBar.add(bookMenu);
		menuBar.add(readerMenu);
		menuBar.add(borrowManageMenu);
		menuBar.add(bsMenu);
		menuBar.add(sysManageMenu);

		return menuBar;
	}

	// ����������
	private JToolBar createToolBar() {
		JToolBar toolBar = new JToolBar();
		// ���ù������Ƿ�����ƶ�
		toolBar.setFloatable(false);

		// ���� ���ͼ����Ϣ ��ť
		JButton bookAddButton = new JButton(MenuActions.BOOK_ADD);
		ImageIcon icon = CreateIcon.add("addBook.jpg");
		bookAddButton.setIcon(icon);
		bookAddButton.setHideActionText(true);
		toolBar.add(bookAddButton);

		// �ڹ���������� ͼ����Ϣ��ѯ ��ť
		JButton bookButton = new JButton(MenuActions.BOOK_SEARCH);
		ImageIcon bookIcon = CreateIcon.add("bookInfo.jpg");
		bookButton.setIcon(bookIcon);
		bookButton.setHideActionText(true);
		toolBar.add(bookButton);

		// �ڹ���������� ͼ�������Ϣ ��ť
		JButton bookBorrowButton = new JButton(MenuActions.BORROW);
		ImageIcon bookBorrowIcon = CreateIcon.add("borrowInfo.jpg");
		bookBorrowButton.setIcon(bookBorrowIcon);
		bookBorrowButton.setHideActionText(true);
		toolBar.add(bookBorrowButton);

		// �ڹ������д���������Ӱ�ť
		JButton readerAddButton = new JButton(MenuActions.READER_ADD);
		ImageIcon readerAddIcon = CreateIcon.add("addReader.jpg");
		readerAddButton.setIcon(readerAddIcon);
		readerAddButton.setHideActionText(true);
		toolBar.add(readerAddButton);

		// �ڹ������д��������Ӱ�ť
		JButton bsAddButton = new JButton(MenuActions.BS_ADD);
		ImageIcon ReaderSearchIcon = CreateIcon.add("addLibrary.jpg");
		bsAddButton.setIcon(ReaderSearchIcon);
		bsAddButton.setHideActionText(true);
		toolBar.add(bsAddButton);

		// �ڹ������и�����˳���ť
		JButton ExitButton = new JButton(MenuActions.EXIT);
		ImageIcon ExitIcon = CreateIcon.add("exit.jpg");
		ExitButton.setIcon(ExitIcon);
		ExitButton.setHideActionText(true);
		toolBar.add(ExitButton);

		return toolBar;
	}
}
