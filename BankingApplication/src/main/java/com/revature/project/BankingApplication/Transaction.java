package com.revature.project.BankingApplication;


public interface Transaction {
	
	public void deposit(double amount);
	public boolean withdraw(double amount);
	public boolean transfer(Account from, Account to, double amount);
}
