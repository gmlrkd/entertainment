package model;

public class ApplicantVO {
	private int no;
	private String job;
	private String name;
	private String age;
	private String height;
	private String weight;
	private String phoneNumber;
	private String email;
	private String specialnote;
	private String employeeNumber;
	private String date;
	private String image;

	public ApplicantVO(int no, String job, String name, String age, String height, String weight, String phoneNumber,
			String email, String specialnote, String employeeNumber, String image) {
		super();
		this.no = no;
		this.job = job;
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.specialnote = specialnote;
		this.employeeNumber = employeeNumber;
		this.image = image;
	}

	public ApplicantVO(String job, String name, String age, String height, String weight, String phoneNumber,
			String email, String specialnote, String employeeNumber, String image) {
		this.job = job;
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.specialnote = specialnote;
		this.employeeNumber = employeeNumber;
		this.image = image;
	}

	public ApplicantVO(int no, String job, String name, String age, String height, String weight, String phoneNumber,
			String email, String specialnote, String employeeNumber, String image, String date) {
		super();
		this.no = no;
		this.job = job;
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.specialnote = specialnote;
		this.employeeNumber = employeeNumber;
		this.date = date;
		this.image = image;
	}

	public String getSpecialnote() {
		return specialnote;
	}

	public void setSpecialnote(String specialnote) {
		this.specialnote = specialnote;
	}

	public ApplicantVO() {
		super();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
