package com.revature.project.BankingApplication.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.project.BankingApplication.User;
import com.revature.project.BankingApplication.util.ConnectionFactory;

public class UserDaoImp implements UserDao {

	public void createUser(User user) {
		String sql = "INSERT INTO USERS (FIRSTNAME, LASTNAME, USERNAME, PASSWORD, USER_TYPE) VALUES (?,?,?,?,?)";
		try {
			PreparedStatement ps = 
					ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getUsername());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getType());			
			ps.executeQuery();
			//return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return false;
		}
	}

	public User retrieveUserByUsername(String username) {
		User user = new User();
		
		String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
		
		try {
			PreparedStatement ps = 
					ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setUsername(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.setType(rs.getString(6));
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return user;
	}

	public ArrayList<User> retrieveUsers() {
		// TODO Auto-generated method stub
		ArrayList<User> users = new ArrayList<User>();
		
		String sql = "SELECT * FROM USERS";
		
		try {
			PreparedStatement ps = 
					ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				User tuser = new User();
				tuser.setFirstName(rs.getString(2));
				tuser.setLastName(rs.getString(3));
				tuser.setUsername(rs.getString(4));
				tuser.setPassword(rs.getString(5));
				tuser.setType(rs.getString(7));
				users.add(tuser);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM USERS WHERE USERID = ?";
		try {
			PreparedStatement ps = 
					ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			ps.setInt(1, id);
						
			ps.executeQuery();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public void createUserPreparedStmt(User flashCard) {
		// TODO Auto-generated method stub
		
	}

}
