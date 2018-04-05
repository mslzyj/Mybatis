package com.zyj.mybaits01.bean;


// 使用   @Alias("emp")  注解为类起别名，在数据库配置文件中引用

public class Employee {

	private Integer id;
	private String lastName;
	private String email;
	private String gender;
	private Department dept;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Department getDepartment() {
		return dept;
	}
	public void setDepartment(Department department) {
		this.dept = department;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", email=" + email + ", gender=" + gender
				+ ", department=" + dept + "]";
	}
	public Employee(Integer id, String lastName, String email, String gender, Department dept) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.dept = dept;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

