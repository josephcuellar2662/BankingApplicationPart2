package com.revature.project.BankingApplication;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Transaction, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<User> users = new ArrayList<User>();
	//private User users[] = new User[10];
	private String name;
	private int status;
	private double balance;
	
	public Account(){
		
	}
	
	
	
	public Account(String name, int status, double balance) {
		super();
		this.name = name;
		this.status = status;
		this.balance = balance;
	}

	public Account(User user, int status, String name){
		super();
		this.name = name;
		this.users.add(user);
		this.status = status;
		this.balance = 0;
	}

	/*SETTERS*/
	public void setName(String name) {
		this.name = name;
	}
	
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/*GETTERS*/
	public String getName() {
		return name;
	}
	
	public ArrayList<User> getUsers() {
		return this.users;
	}
	
	public User getUser(int i){
		return this.users.get(i);
	}

	public int getStatus() {
		return this.status;
	}

	public double getBalance() {
		return balance;
	}
	
	/*TYPES OF TRANSACTIONS */
	public void deposit(double amount){
		this.balance += amount;
	}
	
	public boolean withdraw(double amount){
		if(this.balance > amount){
			this.balance -= amount;
			return true;
		}
		System.out.println("Insufficient funds");
		System.out.println();
		return false;
	}
	
	public boolean transfer(Account from, Account to, double amount){
		if(from.withdraw(amount)){
			to.deposit(amount);
			System.out.println("Transfer successful"); System.out.println();
			return true;
		}
		return false;
	}
	
	public void addUser(User user){
		this.users.add(user);
	}

	public String getUsernames(){
		String usernames = "";
		for(int i=0; i<this.users.size(); i++){
			usernames += users.get(i).getUsername();
		}
		return usernames;
	}

	@Override
	public String toString() {
		return "Account [users=" + users + ", name=" + name + ", status="
				+ status + ", balance=" + balance + "]";
	}
	
}
