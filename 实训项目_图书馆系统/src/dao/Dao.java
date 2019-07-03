package dao;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import dao.book.*;
import system.Item;

/**
 * ���ݿ�����������
 */
public class Dao {
	// �������ݿ������������
	protected static String dbClassName = "com.mysql.jdbc.Driver";
	
	//protected static String dbClassName = "oracle.jdbc.driver.OracleDriver";
	
	
	// ���������ļ�
	protected static File file;
	// ����������ݿ��URL
	protected static String dbUrl;
	// ����������ݿ���û���
	protected static String dbUser;
	// ����������ݿ������
	protected static String dbPwd;
	// �������ݿ����Ӷ���
	public static Connection conn = null;

	// ��̬��������ĿĿ¼��ȡ�ļ���
	static {
		try {
			if (conn == null) {
				file = new File("setting.txt");
				if (!file.exists()) {
					JOptionPane.showMessageDialog(null, "�����ļ������ڣ����ڵ�ǰĿ¼���������ļ�");
					System.exit(0);
				}
				// ������������
				Scanner input = new Scanner(file);
				String ip = input.next();
				String port = input.next();
				dbUser = input.next();
				dbPwd = input.next();
				dbUrl = "jdbc:mysql://" + ip + ":" + port + "/Library";
				
				
				System.out.println("connection = DriverManager.getConnection('jdbc:oracle:thin:@localhost:1521:orcl', 'scott', '990922');");
				//dburl = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "990922");
				input.close();

				Class.forName(dbClassName).newInstance();
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			} // catch ���쳣��ȡ������������ʾ
		} catch (Exception e) {
			String message = e.getMessage();
			int index = message.lastIndexOf(')');
			message = message.substring(index + 1);
			JOptionPane.showMessageDialog(null, message);
			e.printStackTrace();
		}
	}

	// --------------------���ݿ����ģ��---------------------------//
	// �������ݿ��ѯ
	public static ResultSet findForResultSet(String sql) {
		if (conn == null)
			return null;

		ResultSet rs = null;
		try {
			// �ó���ָʾ�ɹ�����ͨ������result�ײ����ݸ���Ӱ���result��������ͣ����ɸ��µ�resultset���������
			Statement stmt = null;
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);

		} catch (SQLException e) {
			String message = e.getMessage();
			int index = message.lastIndexOf(')');
			message = message.substring(index + 1);
			JOptionPane.showMessageDialog(null, message);
			e.printStackTrace();
		}
		return rs;
	}

	// �������ݿ�
	public static boolean insert(String sql) {
		boolean rs = false;
		try {
			Statement stmt = conn.createStatement();
			if (stmt.executeUpdate(sql) > 0)
				rs = true;
		} catch (SQLException e) {
			e.printStackTrace();
			String message = e.getMessage();
			int index = message.lastIndexOf(')');
			message = message.substring(index + 1);
			JOptionPane.showMessageDialog(null, message);
			return false;
		}
		return rs;
	}

	// �������ݿ�
	public static boolean update(String sql) {
		return insert(sql);
	}

	// ɾ�����ݿ�
	public static boolean delete(String sql) {
		return insert(sql);
	}

	

	// ��֤�û����������Ƿ�Ϸ�
	public static Manager checkLogin(String name, String pwd) throws SQLException {
		// System.out.println(name);
		// System.out.println(pwd);
		Manager ret = new Manager();
		ResultSet rs = findForResultSet(
				"select * from Manager where Mname = '" + name + "' and Mpassword = '" + pwd + "'");
		if (rs.next()) {
			ret.setName(rs.getString("Mname").trim());// trimȥ���ַ���ǰ����ַ�
			ret.setPassword(rs.getString("Mpassword").trim());
		}
		return ret;
	}

	// ---------------------��ѯ��Ϣģ��----------------------------//
	// ��ȡ������Ϣ
	// <Reader>���� ��ȡreader���Լ�setget��������list��ʽ�洢������list
	public static List<Reader> getReaderInfo(Item item) {
		List<Reader> list = new ArrayList<Reader>();
		String where = "Rname = '" + item.getName() + "'";
		if (item.getId() != null) {
			where = "Rid = '" + item.getId() + "'";
		}
		ResultSet set = findForResultSet("select * from Reader where " + where);
		try {
			while (set.next()) {
				Reader info = new Reader();
				info.setAddress(set.getString("Raddress").trim());
				info.setId(set.getString("Rid").trim());
				info.setName(set.getString("Rname").trim());
				info.setOwn(set.getDouble("Rown"));
				info.setPhone(set.getString("Rphone").trim());
				info.setSex(set.getString("Rsex").trim());
				list.add(info);
			}
		} catch (SQLException e) {
			String message = e.getMessage();
			int index = message.lastIndexOf(')');
			message = message.substring(index + 1);
			JOptionPane.showMessageDialog(null, message);
			e.printStackTrace();
		}
		return list;
	}

	// ��ȡ���ж�����Ϣ
	public static List<Reader> getReaderInfo() {
		List<Reader> list = new ArrayList<Reader>();
		ResultSet set = findForResultSet("select * from Reader");
		try {
			while (set.next()) {
				Reader info = new Reader();
				info.setAddress(set.getString("Raddress").trim());
				info.setId(set.getString("Rid").trim());
				info.setName(set.getString("Rname").trim());
				info.setOwn(set.getDouble("Rown"));
				info.setPhone(set.getString("Rphone").trim());
				info.setSex(set.getString("Rsex").trim());
				list.add(info);
			}
		} catch (SQLException e) {
			String message = e.getMessage();
			int index = message.lastIndexOf(')');
			message = message.substring(index + 1);
			JOptionPane.showMessageDialog(null, message);
			e.printStackTrace();
		}
		return list;
	}

	// ��ȡ�鼮��Ϣ
	public static List<Book> getBookInfo(Item item) {
		List<Book> list = new ArrayList<Book>();
		String where = "Btitle = '" + item.getName() + "'";
		if (item.getId() != null) {
			where = "Bid = '" + item.getId() + "'";
		}
		ResultSet set = findForResultSet("select * from Book where " + where);
		try {
			while (set.next()) {
				Book info = new Book();
				info.setAuthor(set.getString("Bauthor").trim());
				info.setId(set.getString("Bid").trim());
				info.setIn_date(set.getDate("Bin_date"));
				info.setNow_amount(set.getInt("Bnow_amount"));
				info.setPress(set.getString("Bpress").trim());
				info.setPrice(set.getDouble("Bprice"));
				info.setSid(set.getString("BSid"));
				info.setTitle(set.getString("Btitle"));
				info.setTotal(set.getInt("Btotal"));
				list.add(info);
			}
		} catch (SQLException e) {
			String message = e.getMessage();
			int index = message.lastIndexOf(')');
			message = message.substring(index + 1);
			JOptionPane.showMessageDialog(null, message);
			e.printStackTrace();
		}
		return list;
	}

	// ��ȡ�����鼮��Ϣ
	public static List<Book> getAllBookInfo() {
		List<Book> list = new ArrayList<Book>();
		ResultSet set = findForResultSet("select * from Book");
		try {
			while (set.next()) {
				Book info = new Book();
				info.setAuthor(set.getString("Bauthor").trim());
				info.setId(set.getString("Bid").trim());
				info.setIn_date(set.getDate("Bin_date"));
				// System.out.println(set.getDate("Bin_date"));
				info.setNow_amount(set.getInt("Bnow_amount"));
				info.setPress(set.getString("Bpress").trim());
				info.setPrice(set.getDouble("Bprice"));
				info.setSid(set.getString("BSid"));
				info.setTitle(set.getString("Btitle").trim());
				info.setTotal(set.getInt("Btotal"));
				list.add(info);
				// System.out.println(set.getString("Bauthor"));
			}
		} catch (SQLException e) {
			String message = e.getMessage();
			int index = message.lastIndexOf(')');
			message = message.substring(index + 1);
			JOptionPane.showMessageDialog(null, message);
			e.printStackTrace();
		}
		return list;
	}

	// ��ѯ������Ϣ
	public static List<Borrow> getBorrowInfo(Item item) {
		List<Borrow> list = new ArrayList<Borrow>();
		String where = "Rid = '" + item.getName() + "'";
		if (item.getId() != null) {
			where = "BOid = '" + item.getId() + "'";
		}
		ResultSet set = findForResultSet("select * from Borrow where " + where);
		try {
			while (set.next()) {
				Borrow info = new Borrow();
				info.setBid(set.getString("Bid").trim());
				info.setBorrowDate(set.getDate("BorrowDate"));
				info.setId(set.getString("BOid"));
				info.setName(set.getString("Mname").trim());
				info.setReturnDate(set.getDate("ReturnDate"));
				info.setRid(set.getString("Rid"));
				list.add(info);
			}
		} catch (SQLException e) {
			String message = e.getMessage();
			int index = message.lastIndexOf(')');
			message = message.substring(index + 1);
			JOptionPane.showMessageDialog(null, message);
			e.printStackTrace();
		}
		return list;
	}

	// ��ѯ���н�����Ϣ
	public static List<Borrow> getAllBorrowInfo() {
		List<Borrow> list = new ArrayList<Borrow>();
		ResultSet set = findForResultSet("select * from Borrow");
		try {
			while (set.next()) {
				Borrow info = new Borrow();
				info.setBid(set.getString("Bid").trim());
				info.setBorrowDate(set.getDate("BorrowDate"));
				info.setId(set.getString("BOid"));
				info.setName(set.getString("Mname").trim());
				info.setReturnDate(set.getDate("ReturnDate"));
				info.setRid(set.getString("Rid"));
				list.add(info);
			}
		} catch (SQLException e) {
			String message = e.getMessage();
			int index = message.lastIndexOf(')');
			message = message.substring(index + 1);
			JOptionPane.showMessageDialog(null, message);
			e.printStackTrace();
		}
		return list;
	}

	// ��ȡ�����Ϣ
	public static List<BookShelf> getBsInfo(Item item) {
		List<BookShelf> list = new ArrayList<BookShelf>();
		String where = "BSposition = '" + item.getName() + "'";
		if (item.getId() != null) {
			where = "BSid = '" + item.getId() + "'";
		}
		ResultSet set = findForResultSet("select * from BookShelf where " + where);
		try {
			while (set.next()) {
				BookShelf info = new BookShelf();
				info.setId(set.getString("BSid").trim());
				info.setPosition(set.getString("BSposition"));
				list.add(info);
			}
		} catch (SQLException e) {
			String message = e.getMessage();
			int index = message.lastIndexOf(')');
			message = message.substring(index + 1);
			JOptionPane.showMessageDialog(null, message);
			e.printStackTrace();
		}
		return list;
	}

	// ��ȡ���������Ϣ
	public static List<BookShelf> getBsInfo() {
		List<BookShelf> list = new ArrayList<BookShelf>();
		ResultSet set = findForResultSet("select * from BookShelf");
		try {
			while (set.next()) {
				BookShelf info = new BookShelf();
				info.setId(set.getString("BSid").trim());
				info.setPosition(set.getString("BSposition"));
				list.add(info);
			}
		} catch (SQLException e) {
			String message = e.getMessage();
			int index = message.lastIndexOf(')');
			message = message.substring(index + 1);
			JOptionPane.showMessageDialog(null, message);
			e.printStackTrace();
		}
		return list;
	}

	// ��ѯ����Ա��Ϣ
	public static Manager getManager(String name) throws SQLException {
		// System.out.println(name);
		// System.out.println(pwd);
		Manager ret = new Manager();
		ResultSet rs = findForResultSet("select * from Manager where Mname = '" + name + "'");
		if (rs.next()) {
			ret.setName(rs.getString("Mname").trim());
			ret.setPassword(rs.getString("Mpassword").trim());
		}
		return ret;
	}

	// ---------------------�޸���Ϣģ��----------------------------//
	// �޸Ĺ���Ա����
	public static boolean changePwd(String name, String pwd, String newPwd) {
		String sql = "update Manager set Mpassword = '" + newPwd + "' where Mname = '" + name + "' and Mpassword = '"
				+ pwd + "'";
		if (!update(sql)) // ��ɾ�Ĳ鷽�����û���ģ�壬������sql��䣬ִ��
			return false;
		return true;
	}

	// �޸�ͼ��۸�
	public static boolean changePrice(String id, double price) {
		String sql = "update Book set Bprice = " + price + " where Bid = '" + id + "'";
		if (!update(sql))
			return false;
		return true;
	}

	// �޸�Ƿ��
	public static boolean changeOwn(String id, double own) {
		String sql = "update Reader set Rown = " + own + " where Rid = '" + id + "'";
		if (!update(sql))
			return false;
		return true;
	}

	// �޸Ľ��ı�
	public static boolean changeReturn(String id, Date date) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			String sql = "update Borrow set ReturnDate = '" + date + "' where BOid = '" + id + "'";
			if (!update(sql))
				return false;
			ResultSet rs = findForResultSet("select * from Borrow where BOid = '" + id + "'");
			if (rs.next()) {
				if (!update("update Book set Bnow_amount = Bnow_amount + 1 where Bid = '" + rs.getString("Bid").trim()
						+ "'"))
					return false;
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			String message = e.getMessage();
			int index = message.lastIndexOf(')');
			message = message.substring(index + 1);
			JOptionPane.showMessageDialog(null, message);
			e.printStackTrace();
		}
		return true;
	}

	// ---------------------�����Ϣģ��----------------------------//
	// ��Ӷ�����Ϣ
	public static boolean insertReaderInfo(Reader rd) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			String sql = "insert into Reader values('" + rd.getAddress() + "','" + rd.getId() + "','" + rd.getName()
					+ "'," + rd.getOwn() + ",'" + rd.getPhone() + "','" + rd.getSex() + "')";
			if (!insert(sql)) {
				JOptionPane.showMessageDialog(null, "���������Ϣʧ�ܣ�����Ƿ�������ظ����");
				return false;
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			String message = e.getMessage();
			int index = message.lastIndexOf(')');
			message = message.substring(index + 1);
			JOptionPane.showMessageDialog(null, message);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// ����鼮��Ϣ
	public static boolean insertBookInfo(Book book, int num) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			ResultSet rs = findForResultSet("select * from Book where Bid = '" + book.getId() + "'");
			if (rs.next()) {
				String sql = "update Book set Btotal = Btotal + " + num + " , Bnow_amount = Bnow_amount + " + num + " "
						+ "where Bid = '" + rs.getString("Bid").trim() + "'";
				if (!update(sql)) {
					JOptionPane.showMessageDialog(null, "�����鼮��Ϣʧ��");
					return false;
				}
			} else {
				if (!insert(
						"insert into Book values('" + book.getAuthor() + "','" + book.getId() + "','" + book.getSid()
								+ "','" + book.getIn_date() + "'," + book.getNow_amount() + ",'" + book.getPress()
								+ "'," + book.getPrice() + ",'" + book.getTitle() + "'," + book.getTotal() + ")")) {
					JOptionPane.showMessageDialog(null, "�����鼮��Ϣʧ��");
					return false;
				}
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			String message = e.getMessage();
			int index = message.lastIndexOf(')');
			message = message.substring(index + 1);
			JOptionPane.showMessageDialog(null, message);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// ��ӽ��ļ�¼
	public static boolean insertBorrowInfo(Borrow borrow) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			ResultSet rs = findForResultSet("select Bid, Bnow_amount from Book where Bid = '" + borrow.getBid() + "'");
			if (rs.next()) {
				if (rs.getInt("Bnow_amount") == 0) {
					JOptionPane.showMessageDialog(null, "�������޿��");
					return false;
				}
				String sql = "update Book set Bnow_amount = Bnow_amount - 1 where Bid = '" + rs.getString("Bid").trim()
						+ "'";
				if (!update(sql)) {
					return false;
				}
			} else
				return false;

			if (!insert("insert into Borrow values('" + borrow.getBorrowDate() + "', null,'" + borrow.getId() + "','"
					+ borrow.getBid() + "','" + borrow.getRid() + "','" + borrow.getName() + "')"))
				return false;
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			String message = e.getMessage();
			int index = message.lastIndexOf(')');
			message = message.substring(index + 1);
			JOptionPane.showMessageDialog(null, message);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// ��ӹ���Ա
	public static boolean insertManagetInfo(Manager ma) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			ResultSet rs = findForResultSet("select * from Manager where Mname = '" + ma.getName() + "'");
			if (rs.next()) {
				return false;
			} else {
				if (!insert("insert into Manager values('" + ma.getName() + "','" + ma.getPassword() + "')"))
					return false;
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			String message = e.getMessage();
			int index = message.lastIndexOf(')');
			message = message.substring(index + 1);
			JOptionPane.showMessageDialog(null, message);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// ������
	public static boolean insertBSInfo(BookShelf bs) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			ResultSet rs = findForResultSet("select * from BookShelf where BSid = '" + bs.getId() + "'");
			if (rs.next()) {
				return false;
			} else {
				if (!insert("insert into BookShelf values('" + bs.getId() + "','" + bs.getPosition() + "')"))
					return false;
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			String message = e.getMessage();
			int index = message.lastIndexOf(')');
			message = message.substring(index + 1);
			JOptionPane.showMessageDialog(null, message);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// ---------------------ɾ����Ϣģ��----------------------------//
	// ɾ������Ա��Ϣ
	public static boolean deleteManager(Item item) {
		String sql = "delete from Manager where Mname = '" + item.getId() + "' and Mpassword = '" + item.getName()
				+ "'";
		if (!delete(sql)) {
			return false;
		}
		return true;
	}

}
