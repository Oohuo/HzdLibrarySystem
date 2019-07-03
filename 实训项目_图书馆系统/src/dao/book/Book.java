package dao.book;

import java.sql.Date;

public class Book {
	private String author; // ͼ������
	private String id; // ͼ����
	private String sid; // ��ܱ��
	private String title; // ����
	private String press; // ������
	private Date in_date; // ���ʱ��
	private int now_amount; // ��ǰ����
	private int total; // ����
	private double price; // �۸�

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public Date getIn_date() {
		return in_date;
	}

	public void setIn_date(Date in_date) {
		this.in_date = in_date;
	}

	public int getNow_amount() {
		return now_amount;
	}

	public void setNow_amount(int now_amount) {
		this.now_amount = now_amount;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
