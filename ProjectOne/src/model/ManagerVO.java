package model;

public class ManagerVO {
	private int no;
	private String m_id;
	private String m_password;
	private String m_name;
	private String position;
	private String employeenumber;
	private String m_phonenumber;
	private String m_email;

	public ManagerVO() {
		super();
	}

	public ManagerVO(int no, String m_id, String m_password, String m_name, String position, String employeenumber,
			String m_phonenumber, String m_email) {
		super();
		this.no = no;
		this.m_id = m_id;
		this.m_password = m_password;
		this.m_name = m_name;
		this.position = position;
		this.employeenumber = employeenumber;
		this.m_phonenumber = m_phonenumber;
		this.m_email = m_email;
	}

	public ManagerVO(String m_id, String m_password, String m_name, String position, String employeenumber,
			String m_phonenumber, String m_email) {
		super();
		this.m_id = m_id;
		this.m_password = m_password;
		this.m_name = m_name;
		this.position = position;
		this.employeenumber = employeenumber;
		this.m_phonenumber = m_phonenumber;
		this.m_email = m_email;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_password() {
		return m_password;
	}

	public void setM_password(String m_password) {
		this.m_password = m_password;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmployeenumber() {
		return employeenumber;
	}

	public void setEmployeenumber(String employeenumber) {
		this.employeenumber = employeenumber;
	}

	public String getM_phonenumber() {
		return m_phonenumber;
	}

	public void setM_phonenumber(String m_phonenumber) {
		this.m_phonenumber = m_phonenumber;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

}
