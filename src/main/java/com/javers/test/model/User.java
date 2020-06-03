package com.javers.test.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * It is the POJO of User Table in Database
 * 
 * @author SURAJ
 *
 */
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "address")
	private String address;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "birth_date", nullable = false)
	private Date birthDate;

	public User() {

	}

	public User(String userName, String address, String email, Date birthDate) {
		super();
		this.userName = userName;
		this.address = address;
		this.email = email;
		this.birthDate = birthDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {

		return "Details [ User Id = " + userId + " User Name = " + userName + " Address = " + address + " Email = "
				+ email + " ]";
	}

}
