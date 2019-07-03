package system;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JInternalFrame;

import login.LoginDialog;
import login.MainDialog;
import loginin.*;

public class MenuActions {
	private static Map<String, JInternalFrame> frames; // �Ӵ��弯��

	// ����Ա�����
	public static PasswordModiAction MODIFY_PASSWORD; // �޸����봰�嶯��
	public static UserAddAction USER_ADD; // ��ӹ���Ա���嶯��

	// ͼ������
	public static BookSearchAction BOOK_SEARCH; // ͼ���������嶯��
	public static BookAddAction BOOK_ADD; // ͼ����Ϣ��Ӵ��嶯��
	public static BookAdd2Action BOOK_ADD2; // ����ͼ����Ϣ���
	public static BookPriceAction BOOK_PRICE; // ͼ��۸��޸�

	// ���ı����
	public static BorrowAction BORROW; // ͼ����Ĵ��嶯��
	public static ReturnAction RETURN; // ͼ��黹���嶯��
	public static BorrowSearchAction BORROW_SEARCH; // ������Ϣ��ѯ

	// ���߱����
	public static ReaderAddAction READER_ADD; // ������Ϣ��Ӵ��嶯��
	public static ReaderSearchAction READER_SEARCH; // ������Ϣ��ѯ
	public static ReaderOwnAction READER_OWN; // ����Ƿ���޸�

	// ��ܱ����
	public static BookShelfAddAction BS_ADD; // ��������Ϣ
	public static BookShelfSearchAction BS_SEARCH; // ��ѯ�����Ϣ

	

	// �����˳��¼�����
	public static ExitAction EXIT; // ϵͳ�˳�����
	
	//�����л��û�����
    public static SwitchuserAction Switch_user ;
	// ��ʼ����������
	static {
		// ��ʼ��frames
		frames = new HashMap<String, JInternalFrame>();

		// ����Ա�����
		MODIFY_PASSWORD = new PasswordModiAction(); // �޸����봰�嶯��
		USER_ADD = new UserAddAction(); // ��ӹ���Ա���嶯��

		// ͼ������
		BOOK_SEARCH = new BookSearchAction(); // ͼ���������嶯��
		BOOK_ADD = new BookAddAction(); // ͼ����Ϣ��Ӵ��嶯��
		BOOK_ADD2 = new BookAdd2Action(); // �������ͼ��
		BOOK_PRICE = new BookPriceAction(); // ͼ��۸��޸Ĵ��嶯��

		// ���ı����
		BORROW = new BorrowAction(); // ͼ����Ĵ��嶯��
		RETURN = new ReturnAction(); // ͼ��黹
		BORROW_SEARCH = new BorrowSearchAction(); // ������Ϣ��ѯ

		// ���߱����
		READER_ADD = new ReaderAddAction(); // ������Ϣ��Ӵ��嶯��
		READER_SEARCH = new ReaderSearchAction(); // ������Ϣ��ѯ
		READER_OWN = new ReaderOwnAction(); // ����Ƿ���޸�

		// ��ܱ����
		BS_ADD = new BookShelfAddAction(); // ��������Ϣ
		BS_SEARCH = new BookShelfSearchAction(); // ��ѯ�����Ϣ

		
		// �����˳��¼�����
		EXIT = new ExitAction(); // ϵͳ�˳�����
		//
		Switch_user = new SwitchuserAction();

	}

	// ---------------------����Ա��ض���---------------------------------//
	// �޸Ĺ���Ա����
	private static class PasswordModiAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		// ���췽��
		PasswordModiAction() {
			putValue(Action.NAME, "��������");
			putValue(Action.LONG_DESCRIPTION, "�޸ĵ�ǰ�û�����");
			putValue(Action.SHORT_DESCRIPTION, "��������");
		}

		// ��������
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("��������") || frames.get("��������").isClosed()) {
				ChangePw iframe = new ChangePw();
				frames.put("��������", iframe);
				MainDialog.addIFrame(frames.get("��������"));
			}
		}
	}

	// ��ӹ���Ա
	private static class UserAddAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		// ���췽��
		UserAddAction() {
			super("��ӹ���Ա", null);
			putValue(Action.LONG_DESCRIPTION, "����Ա��Ϣ���");
			putValue(Action.SHORT_DESCRIPTION, "��ӹ���Ա");
		}

		// ����һ������
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("����Ա��Ϣ���") || frames.get("����Ա��Ϣ���").isClosed()) {
				UserAddIFrame iframe = new UserAddIFrame();
				frames.put("����Ա��Ϣ���", iframe);
				MainDialog.addIFrame(frames.get("����Ա��Ϣ���"));
			}
		}
	}

	// ---------------------ͼ����ض���---------------------------------//
	// ��ѯͼ��
	private static class BookSearchAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		// ���캯��
		BookSearchAction() {
			super("ͼ������", null);
			putValue(Action.LONG_DESCRIPTION, "��������ͼ����Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "ͼ������");
		}

		// ��������
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ���ѯ") || frames.get("ͼ���ѯ").isClosed()) {
				BookSearchIFrame iframe = new BookSearchIFrame();
				frames.put("ͼ���ѯ", iframe);
				MainDialog.addIFrame(frames.get("ͼ���ѯ"));
			}
		}
	}

	// ���ͼ��
	private static class BookAddAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ����ӹ���") || frames.get("ͼ����ӹ���").isClosed()) {
				BookAddIFrame iframe = new BookAddIFrame();
				frames.put("ͼ����ӹ���", iframe);
				MainDialog.addIFrame(frames.get("ͼ����ӹ���"));
			}
		}

		BookAddAction() {
			super("ͼ�����", null);
			putValue(Action.LONG_DESCRIPTION, "���������ͼ��");
			putValue(Action.SHORT_DESCRIPTION, "ͼ�����");
		}
	}

	// ����ͼ����Ϣ���
	private static class BookAdd2Action extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("�������ͼ��") || frames.get("�������ͼ��").isClosed()) {
				BookAdd2IFrame iframe = new BookAdd2IFrame();
				frames.put("�������ͼ��", iframe);
				MainDialog.addIFrame(frames.get("�������ͼ��"));
			}
		}

		BookAdd2Action() {
			super("�������ͼ��", null);
			putValue(Action.LONG_DESCRIPTION, "�������ͼ��");
			putValue(Action.SHORT_DESCRIPTION, "�������ͼ��");
		}
	}

	// �޸�ͼ��۸�
	private static class BookPriceAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		BookPriceAction() {
			super("ͼ��۸��޸�", null);
			putValue(Action.LONG_DESCRIPTION, "ͼ��۸��޸�");
			putValue(Action.SHORT_DESCRIPTION, "ͼ��۸��޸�");
		}

		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("�۸��޸�") || frames.get("�۸��޸�").isClosed()) {
				BookPriceIFrame iframe = new BookPriceIFrame();
				frames.put("�۸��޸�", iframe);
				MainDialog.addIFrame(frames.get("�۸��޸�"));
			}
		}
	}

	// ---------------------������ض���---------------------------------//
	// ��ӽ�����Ϣ
	public static class BorrowAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("������Ϣ���") || frames.get("������Ϣ���").isClosed()) {
				BorrowIFrame iframe = new BorrowIFrame();
				frames.put("������Ϣ���", iframe);
				MainDialog.addIFrame(frames.get("������Ϣ���"));
			}
		}

		BorrowAction() {
			super("������Ϣ���", null);
			putValue(Action.LONG_DESCRIPTION, "����µĽ��ļ�¼");
			putValue(Action.SHORT_DESCRIPTION, "������Ϣ���");
		}
	}

	// ��ӹ黹��Ϣ
	public static class ReturnAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("�黹��Ϣ���") || frames.get("�黹��Ϣ���").isClosed()) {
				ReturnIFrame iframe = new ReturnIFrame();
				frames.put("�黹��Ϣ���", iframe);
				MainDialog.addIFrame(frames.get("�黹��Ϣ���"));
			}
		}

		ReturnAction() {
			super("�黹��Ϣ���", null);
			putValue(Action.LONG_DESCRIPTION, "����µĹ黹��¼");
			putValue(Action.SHORT_DESCRIPTION, "�黹��Ϣ���");
		}
	}

	// ��ѯ������Ϣ
	public static class BorrowSearchAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		BorrowSearchAction() {
			super("������Ϣ����", null);
			putValue(Action.LONG_DESCRIPTION, "�������߽�����Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "������Ϣ����");
		}

		// ��������
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("���Ĳ�ѯ") || frames.get("���Ĳ�ѯ").isClosed()) {
				BorrowSearchIFrame iframe = new BorrowSearchIFrame();
				frames.put("���Ĳ�ѯ", iframe);
				MainDialog.addIFrame(frames.get("���Ĳ�ѯ"));
			}
		}
	}

	// ---------------------������ض���---------------------------------//
	// ��Ӷ�����Ϣ
	public static class ReaderAddAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("������Ϣ���") || frames.get("������Ϣ���").isClosed()) {
				ReaderAddIFrame iframe = new ReaderAddIFrame();
				frames.put("������Ϣ���", iframe);
				MainDialog.addIFrame(frames.get("������Ϣ���"));
			}
		}

		ReaderAddAction() {
			super("������Ϣ���", null);
			putValue(Action.LONG_DESCRIPTION, "����µĶ�����Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "������Ϣ���");
		}
	}

	// ��ѯ������Ϣ
	public static class ReaderSearchAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		ReaderSearchAction() {
			super("������Ϣ��ѯ", null);
			putValue(Action.LONG_DESCRIPTION, "��ѯ���ߵ���Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "������Ϣ��ѯ");
		}

		// ��������
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("���߲�ѯ") || frames.get("���߲�ѯ").isClosed()) {
				ReaderSearchIFrame iframe = new ReaderSearchIFrame();
				frames.put("���߲�ѯ", iframe);
				MainDialog.addIFrame(frames.get("���߲�ѯ"));
			}
		}
	}

	// ����Ƿ���޸�
	public static class ReaderOwnAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		ReaderOwnAction() {
			super("����Ƿ���޸�", null);
			putValue(Action.LONG_DESCRIPTION, "����Ƿ���޸�");
			putValue(Action.SHORT_DESCRIPTION, "����Ƿ���޸�");
		}

		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("Ƿ���޸�") || frames.get("Ƿ���޸�").isClosed()) {
				ReaderOwnIFrame iframe = new ReaderOwnIFrame();
				frames.put("Ƿ���޸�", iframe);
				MainDialog.addIFrame(frames.get("Ƿ���޸�"));
			}
		}
	}

	// ---------------------�����ض���---------------------------------//
	// �����ܲ���
	public static class BookShelfAddAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		BookShelfAddAction() {
			super("������", null);
			putValue(Action.LONG_DESCRIPTION, "������");
			putValue(Action.SHORT_DESCRIPTION, "������");
		}

		// ��������
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("������") || frames.get("������").isClosed()) {
				BSAddIFrame iframe = new BSAddIFrame();
				frames.put("������", iframe);
				MainDialog.addIFrame(frames.get("������"));
			}
		}
	}

	// ��ѯ��ܲ���
	public static class BookShelfSearchAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		BookShelfSearchAction() {
			super("�����Ϣ��ѯ", null);
			putValue(Action.LONG_DESCRIPTION, "��ѯ��ܵ���Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "�����Ϣ��ѯ");
		}

		// ��������
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("��ܲ�ѯ") || frames.get("��ܲ�ѯ").isClosed()) {
				BSSearchIFrame iframe = new BSSearchIFrame();
				frames.put("��ܲ�ѯ", iframe);
				MainDialog.addIFrame(frames.get("��ܲ�ѯ"));
			}
		}
	}

	// ---------------------ϵͳ��ض���---------------------------------//

	

	// �˳�����
	public static class ExitAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

		// ���췽��
		public ExitAction() {
			super("�˳�ϵͳ");
			putValue(Action.LONG_DESCRIPTION, "�˳�ͼ��ݹ���ϵͳ");
			putValue(Action.SHORT_DESCRIPTION, "�˳�ϵͳ");
		}
	}
	public static class SwitchuserAction extends AbstractAction {
		
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO �Զ����ɵķ������
			
			new LoginDialog();
			System.exit(0);
		}
		
	}
}
