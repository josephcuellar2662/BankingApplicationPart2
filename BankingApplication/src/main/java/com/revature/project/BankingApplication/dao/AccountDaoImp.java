package com.revature.project.BankingApplication.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.revature.project.BankingApplication.Account;
import com.revature.project.BankingApplication.util.ConnectionFactory;

public class AccountDaoImp implements AccountDao {

	public boolean createAccount(Account account) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO ACCOUNTS (BALANCE, STATUS, ACCOUNTNAME) VALUES (?,?,?)";
		try {
			PreparedStatement ps = 
					ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			ps.setDouble(1, account.getBalance());
			ps.setInt(2, account.getStatus());
			ps.setString(3, account.getName());
			ps.executeQuery();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<Account> retrieveAccountByUsername(String username) {
		ArrayList<Account> acc = new ArrayList<Account>();
		String sql = "SELECT A.BALANCE, A.STATUS, A.ACCOUNTNAME FROM ACCOUNTS A JOIN  ACCOUNTS_USERS AC ON AC.accountname = A.accountname JOIN USERS U ON AC.USERNAME = U.username WHERE AC.username = ?";
		
		try {
			PreparedStatement ps = 
					ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Account account = new Account();
				double balance = rs.getDouble("balance");
				account.setBalance(balance);
				int status = rs.getInt("status");
				account.setStatus(status);
				String accountName = rs.getString("accountname");
				account.setName(accountName);
				acc.add(account);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return acc;
	}
	
	public Account retrieveAccountByName(String name){
		Account taccount = new Account();

		String sql = "SELECT BALANCE, STATUS, ACCOUNTNAME FROM ACCOUNTS WHERE ACCOUNTNAME = ?";
		try{
			PreparedStatement ps = 
					ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				//Account taccount = new Account();
				taccount.setBalance(rs.getDouble("balance"));
				taccount.setStatus(rs.getInt("status"));
				taccount.setName(rs.getString("accountname"));
				return taccount;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<Account> retrieveAllAccounts() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		ArrayList<Account> acc = new ArrayList<Account>();
		
		String sql = "SELECT * FROM ACCOUNTS";
		
		try {
			PreparedStatement ps = 
					ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Account tacc = new Account();
				tacc.setBalance(rs.getDouble(2));
				tacc.setStatus(rs.getInt(3));
				acc.add(tacc);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return acc;
	}

	public void updateAccount(Account account) {
		// TODO Auto-generated method stub
		String sql = "UPDATE ACCOUNTS SET BALANCE = ?, STATUS = ? WHERE ACCOUNTNAME = ?";
		try {
			PreparedStatement ps = 
					ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			ps.setDouble(1, account.getBalance());
			ps.setInt(2, account.getStatus());
			ps.setString(3, account.getName());
			ps.executeQuery();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean deleteAccountByName(String name) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM ACCOUNTS WHERE ACCOUNTNAME = ?";
		try {
			PreparedStatement ps = 
					ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			ps.setString(1, name);
			ps.executeQuery();	
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean activateAccountByName(String name){
		// "UPDATE ACCOUNTS SET BALANCE = ?, STATUS = ? WHERE ACCOUNTNAME = ?"
		String sql = "UPDATE ACCOUNTS SET STATUS = 1 WHERE ACCOUNTNAME = ?";
		try {			
			PreparedStatement ps = 
					ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			ps.setString(1, name);
			ps.executeQuery();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public void createAccountPreparedStmt(Account account) {
		// TODO Auto-generated method stub
		
	}

	public void transferByStoredProcedure(String fromAccount, String toAccount, double amount) {
		Connection conn = null;
		// TODO Auto-generated method stub
		// "UPDATE ACCOUNTS SET BALANCE = ?, STATUS = ? WHERE ACCOUNTNAME = ?"
				try {
					Properties prop = new Properties();
					prop.load(new FileReader("src/main/resources/database.properties"));
					Class.forName(prop.getProperty("driver"));
					conn = DriverManager.getConnection(
							prop.getProperty("url"), 
							prop.getProperty("usr"), 
							prop.getProperty("psw"));
					CallableStatement stmt=conn.prepareCall("{call TRANSFER(?,?,?)}");  
					stmt.setString(1, fromAccount);
					stmt.setString(2, toAccount);
					stmt.setDouble(3, amount);
					stmt.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

}
