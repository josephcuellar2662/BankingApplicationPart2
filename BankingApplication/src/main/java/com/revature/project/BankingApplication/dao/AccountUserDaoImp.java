package com.revature.project.BankingApplication.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.project.BankingApplication.Account;
import com.revature.project.BankingApplication.User;
import com.revature.project.BankingApplication.util.ConnectionFactory;

public class AccountUserDaoImp implements AccountUserDao {

	public void createAccountUser(String accountname, String username) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String sql = "INSERT INTO ACCOUNTS_USERS (ACCOUNTNAME, USERNAME) VALUES (?,?)";
		try {
			PreparedStatement ps = 
					ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			ps.setString(1, accountname);
			ps.setString(2, username);
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> retrieveAllAccountsUsers(){
		ArrayList<String> allAccountsUsers = new ArrayList<String>();
		String sql = "SELECT U.USERNAME, U.FIRSTNAME, U.LASTNAME, A.ACCOUNTNAME, A.BALANCE, A.STATUS "
				+ "FROM USERS U "
				+ "JOIN ACCOUNTS_USERS AU ON U.USERNAME = AU.USERNAME "
				+ "JOIN ACCOUNTS A ON AU.ACCOUNTNAME = A.ACCOUNTNAME";
		try {
			PreparedStatement ps = 
					ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String info = "";
				String username = "Username: " + rs.getString("username");
				String lastname = "Last Name: " + rs.getString("lastname");
				String firstname = "First Name: " + rs.getString("firstname");
				String accountName = "Account Name: " + rs.getString("accountname");
				String balance = "Balance: " + rs.getDouble("balance"); 
				String status = "Status: " + rs.getInt("status");
				info = username + ", " + lastname + ", " + firstname + ", " + accountName + ", " + balance + ", " + status;
				allAccountsUsers.add(info);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allAccountsUsers;
	}
}
