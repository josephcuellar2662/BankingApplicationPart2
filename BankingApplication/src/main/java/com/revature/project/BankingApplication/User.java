package com.revature.project.BankingApplication;

import java.io.Serializable;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private String type;
	private String firstName;
	private String lastName;
	private String username;
	private String password;

	public User(){
		this.userId=-1;
		this.type = "none";
		this.firstName = "none";
		this.lastName = "none";
		this.username = "none";
		this.password = "none";
	}
	
	public User(String type, String firstName, String lastName, String username, String password) {
		// TODO Auto-generated constructor stub
		this.userId=-1;
		this.type = type;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	
	/*SETTERS*/
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	/*GETTERS*/
	public int getUserId() {
		return userId;
	}
	
	public String getType() {
		return type;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean login(String password){
		return this.password.equals(password);
	}

	@Override
	public String toString() {
		return "User [type=" + type + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + "]";
	}
}
