package com.revature.project.BankingApplication.driver;

import java.util.ArrayList;

import com.revature.project.BankingApplication.Account;
import com.revature.project.BankingApplication.User;
import com.revature.project.BankingApplication.dao.AccountDao;
import com.revature.project.BankingApplication.dao.AccountDaoImp;
import com.revature.project.BankingApplication.dao.AccountUserDao;
import com.revature.project.BankingApplication.dao.AccountUserDaoImp;
import com.revature.project.BankingApplication.dao.LoggingDao;
import com.revature.project.BankingApplication.dao.LoggingDaoImp;
import com.revature.project.BankingApplication.dao.UserDao;
import com.revature.project.BankingApplication.dao.UserDaoImp;

public class UserDriver {
	public static void main(String[] jc){
		
		UserDao u = new UserDaoImp();
		AccountDao a = new AccountDaoImp();
		AccountUserDao au = new AccountUserDaoImp();
		LoggingDao l_dao = new LoggingDaoImp();
		l_dao.addToUserLog("test_accountname");
		a.transferByStoredProcedure("tneal_acc1", "agonz_acc1", 50.0);
//    	User user = new User("customer", "sarach", "cuellar", "scuellar", "1234");
//   	u.createUser(user);
//    	Account account = new Account(user, 1,"scuellar_account2");
 //   	a.createAccount(account);
//    	au.createAccountUser(account, user);
//    	ArrayList<User> users = new ArrayList<User>();
//    	users = u.retrieveUsers();
//    	for(int i=0; i<users.size(); i++){
//    		System.out.println(users.get(i));
//    	}
		
//		ArrayList<Account> acc = new ArrayList<Account>();
//		acc = a.retrieveAccountByUsername("scuellar");
		
		//System.out.println(acc);
	}
}
