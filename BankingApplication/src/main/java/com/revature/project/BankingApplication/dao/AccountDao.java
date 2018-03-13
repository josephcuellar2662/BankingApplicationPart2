package com.revature.project.BankingApplication.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.project.BankingApplication.Account;
import com.revature.project.BankingApplication.User;

public interface AccountDao {
	public boolean createAccount(Account account);
	
	public ArrayList<Account> retrieveAccountByUsername(String username);
	
	public Account retrieveAccountByName(String name);
	
	public ArrayList<Account> retrieveAllAccounts();
	
	public void updateAccount(Account account);
	
	public boolean deleteAccountByName(String name);
	
	public boolean activateAccountByName(String name);
		
	public void createAccountPreparedStmt(Account account);
	
	public void transferByStoredProcedure(String fromAccount, String toAccount, double amount);
}
