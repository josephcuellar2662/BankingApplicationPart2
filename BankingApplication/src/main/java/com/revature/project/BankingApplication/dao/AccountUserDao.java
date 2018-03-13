package com.revature.project.BankingApplication.dao;

import java.util.ArrayList;

import com.revature.project.BankingApplication.Account;
import com.revature.project.BankingApplication.User;

public interface AccountUserDao {
	public void createAccountUser(String accountname, String username);
	public ArrayList<String> retrieveAllAccountsUsers();
}
