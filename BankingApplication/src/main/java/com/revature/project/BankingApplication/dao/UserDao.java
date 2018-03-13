package com.revature.project.BankingApplication.dao;

import java.util.ArrayList;

import com.revature.project.BankingApplication.User;

public interface UserDao {
	public void createUser(User user);
	
	public User retrieveUserByUsername(String username);
	
	public ArrayList<User> retrieveUsers();
	
	public boolean deleteUser(int id);
	
	public void createUserPreparedStmt(User flashCard);
}
