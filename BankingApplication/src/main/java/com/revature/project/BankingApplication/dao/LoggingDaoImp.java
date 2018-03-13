package com.revature.project.BankingApplication.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.project.BankingApplication.util.ConnectionFactory;

public class LoggingDaoImp implements LoggingDao {

	public void addToUserLog(String accountname) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO LOG_TABLE (ACCOUNTNAME, ACCOUNT_TIME_CREATION) VALUES (?,SYSDATE)";
		try {
			PreparedStatement ps = 
					ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			ps.setString(1, accountname);
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
