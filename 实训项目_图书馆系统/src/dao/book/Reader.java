package dao.book;

public class Reader {
	private String address; // ���ߵ�ַ
	private String id; // ���߱��
	private String name; // ��������
	private String phone; // ���ߵ绰
	private String sex; // �����Ա�
	private double own; // ����Ƿ��

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getOwn() {
		return own;
	}

	public void setOwn(double own) {
		this.own = own;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
