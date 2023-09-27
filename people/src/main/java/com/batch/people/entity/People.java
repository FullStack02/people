package com.batch.people.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="peoplelist")
@NoArgsConstructor
@AllArgsConstructor
public class People implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1528162437120078534L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PEOPLEID")
	@NotEmpty
	private Long peopleId;
		
	@Column(name="LOGINID")
	@Size(min=2,message="login id should be min 2 characters.")
	private String loginId;
	
	@Column(name="FIRSTNAME")
	private String firstName;
	
	@Column(name="LASTNAME")
	private String lastName;
	
	@Column(name="SEX")
	private String sex;
	
	
	@Column(name="EMAIL")
	@javax.validation.constraints.Email
	private String email;
	
	@Column(name="PHONE")
	private String phone;

	@Column(name="DOB")
	private String dob;
	
	@Column(name="JOBTITLE")
	private String jobTitle;

	public Long getpeopleId() {
		return peopleId;
	}

	public void setpeopleId(Long peopleId) {
		this.peopleId = peopleId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String user) {
		this.loginId = user;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	
}