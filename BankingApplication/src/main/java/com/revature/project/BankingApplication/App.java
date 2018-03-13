package com.revature.project.BankingApplication;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;

import com.revature.project.BankingApplication.dao.AccountDao;
import com.revature.project.BankingApplication.dao.AccountDaoImp;
import com.revature.project.BankingApplication.dao.AccountUserDao;
import com.revature.project.BankingApplication.dao.AccountUserDaoImp;
import com.revature.project.BankingApplication.dao.LoggingDao;
import com.revature.project.BankingApplication.dao.LoggingDaoImp;
import com.revature.project.BankingApplication.dao.UserDao;
import com.revature.project.BankingApplication.dao.UserDaoImp;

public class App {
	
	public static void main(String[] jc){
		BasicConfigurator.configure();
		Scanner in = new Scanner(System.in);
		
		UserDao u_dao = new UserDaoImp();
		AccountDao a_dao = new AccountDaoImp();
		AccountUserDao au_dao = new AccountUserDaoImp();
		LoggingDao l_dao = new LoggingDaoImp();
		
//		User admin = new User("admin", "Joseph", "Cuellar", "joseph", "1234");
//		u_dao.createUser(admin);
//		User employee = new User("employee", "Joshua", "Cuellar", "josh", "1234");
//		u_dao.createUser(employee);
//		User sarah = new User("customer", "Sarah", "Cuellar", "sarah", "1234");
//		u_dao.createUser(sarah);
//		User daniel = new User("customer", "Daniel", "Cuellar", "dmoney", "1234");
//		u_dao.createUser(daniel);
//		//String name, int status, double balance
//		Account acc_sarah = new Account("sarah_account", 1, 100);
//		a_dao.createAccount(acc_sarah);
//		au_dao.createAccountUser("sarah_account", "sarah");
		
		boolean banking = true;
		String state = "";
		String option = "";
		// what is the problem, how did you solve the solution, why should they use your solution
		while(banking){
			state = user_selection(in);
			if(state.equals("1")){ /*CUSTOMER*/
				option = menu_login_register(in);
				if(option.equals("1")){ /*Register*/
					String firstName = getFirstName(in);
					String lastName = getLastName(in);
					String username = getUsername(in);
					String password = getPassword(in);
					User user = new User("customer", firstName, lastName, username, password);
					u_dao.createUser(user);
					option = "2";
				} 
				if(option.equals("2")){ /*Login*/
					System.out.println("Login as Customer");
					String username = getUsername(in);
					String password = getPassword(in);
					User user = u_dao.retrieveUserByUsername(username);
					if(user!= null){
						if(password.equals(user.getPassword())){
							//main menu
							option = menu(user, in, u_dao, a_dao, au_dao, l_dao);	
						}
					}
				}
			}
			else if(state.equals("2")){ /*EMPLOYEE*/
				System.out.println("Login as Employee");
				String username = getUsername(in);
				String password = getPassword(in);
				User user = u_dao.retrieveUserByUsername(username);
				if(user.getType().equals("employee")){
					if(password.equals(user.getPassword())){
						//main menu
						option = menu(user, in, u_dao, a_dao, au_dao, l_dao);	
					} else {
						System.out.println("Invalid username or password"); System.out.println();
					}
				} else {
					System.out.println("Invalid username or password"); System.out.println();
				}
			}
			
			else if(state.equals("3")){ /*ADMIN*/
				System.out.println("Login as Admin");
				String username = getUsername(in);
				String password = getPassword(in);
				User user = u_dao.retrieveUserByUsername(username);
				if(user.getType().equals("admin")){
					if(password.equals(user.getPassword())){
						//main menu
						option = menu(user, in, u_dao, a_dao, au_dao, l_dao);	
					} else {
						System.out.println("Invalid username or password"); System.out.println();
					}
				} else {
					System.out.println("Invalid username or password"); System.out.println();
				}
			}
					
			if(state.equals("-1")){
				banking = false;
			}
		}
	}

	private static String menu(User user, Scanner in, UserDao u_dao, AccountDao a_dao, AccountUserDao au_dao, LoggingDao l_dao) {
		String option = "";
		String username = user.getUsername();
		ArrayList<Account> account = new ArrayList<Account>(); /* Used to retrieve all accounts for a user */
		Account acc = new Account();
		boolean loggedin = true;
		while(loggedin){
			option = main_menu(user.getType(), in);
			if(option.equals("1") && user.getType().equals("customer")){
				//Add Account
				acc = createAccount(user, in);
				a_dao.createAccount(acc);
				au_dao.createAccountUser(acc.getName(), user.getUsername());
				l_dao.addToUserLog(acc.getName());
			}
			else if(option.equals("2") && user.getType().equals("customer")){
				//Deposit
				account = a_dao.retrieveAccountByUsername(username);
				System.out.println("Choose a account to deposit to: ");
				for(int i=0; i<account.size(); i++){
					System.out.println(account.get(i).getName());
				}
				System.out.print("Enter account name: ");
				String depositTo = in.next();
				if(depositToAccount(in, a_dao, account, depositTo)){
					System.out.println("Deposit successful"); System.out.println();
				}
			}
			else if(option.equals("3") && user.getType().equals("customer") ){
				//Withdraw
				account = a_dao.retrieveAccountByUsername(username);
				System.out.println("Choose a account to withdraw from: ");
				for(int i=0; i<account.size(); i++){
					System.out.println(account.get(i).getName());
				}
				System.out.print("Enter account name: ");
				String withdrawFrom = in.next();
				withdrawFromAccount(in, a_dao, account, withdrawFrom);
			}
			else if(option.equals("4") && user.getType().equals("customer")){
				//Transfer
				account = a_dao.retrieveAccountByUsername(username);
				System.out.println("Choose a account to transfer from: ");
				for(int i=0; i<account.size(); i++){
					System.out.println(account.get(i).getName());
				}
				System.out.print("Enter account name: ");
				String user_account_name = in.next();
				System.out.print("Enter amount: ");
				double amount = in.nextDouble();
				Account transferFrom = a_dao.retrieveAccountByName(user_account_name);
				if(transferFrom != null){
					if(transferFrom.getStatus() == 1){
						transferFromAccount(in, a_dao, transferFrom, amount);
					} else {
						System.out.println("Account is inactive"); System.out.println();
					}
				} else {
					System.out.println("Invalid account name"); System.out.println();
				}
				
				
			}
			else if(option.equals("5") && user.getType().equals("customer")){
				//Add user to your account(s)
				account = a_dao.retrieveAccountByUsername(username);
				System.out.println("Choose one of the accounts below to add user to: ");
				for(int i=0; i<account.size(); i++){
					System.out.println(account.get(i).getName());
				}
				String add_user_accountname = in.next();
				if(validateAccountBelongsToUser(account, add_user_accountname)){
					addUserToAccount(in, a_dao, u_dao, au_dao, add_user_accountname);
				} else {
					System.out.println("Invalid account"); System.out.println();
				}
			}
			else if(option.equals("6") && user.getType().equals("customer")){
				//View account info
				account = a_dao.retrieveAccountByUsername(username);
				for(int i=0; i<account.size(); i++){
					System.out.println("Acount name: " + account.get(i).getName() + ", Balance: " +  account.get(i).getBalance()
							+ ", Status: " +  account.get(i).getStatus());
				}
				System.out.println();	
			}
			else if(option.equals("6") || ((user.getType().equals("employee") && (!option.equals("-1"))) || option.equalsIgnoreCase("admin"))){
				//Viewing all accounts
				System.out.println("Viewing all accounts: ");
				ArrayList<String> info = new ArrayList<String>();
				info = au_dao.retrieveAllAccountsUsers();
				for(int i=0; i<info.size(); i++){
					System.out.println(info.get(i)); 
				}
				System.out.println();
			}
			else if(option.equals("7") && user.getType().equals("admin")){
				//Delete Account
				System.out.println("Deleting accounts");
				System.out.print("Enter account name: ");
				String accountname = in.next();
				//if(a_dao.retrieveAccountByName(accountname) != null){
					a_dao.deleteAccountByName(accountname);
					System.out.println("Account deleted"); System.out.println();
				//} else {
					//System.out.println("Account does not exist"); System.out.println();
				//}
			}
			else if(option.equals("8") && user.getType().equals("admin")){
				//Approve Account
				System.out.println("Approving accounts");
				System.out.print("Enter account name: ");
				String accountname = in.next();
				//if(a_dao.retrieveAccountByName(accountname) != null){
					a_dao.activateAccountByName(accountname);
					System.out.println("Account activated"); System.out.println();
				//} else {
					//System.out.println("Account does not exist"); System.out.println();
				//}
			}
			/*else if(option.equals("8") && user.getType().equals("admin")){
				System.out.println("Approve or Disapprove User to Account: ");
			}*/
			else if(option.equals("-1")) loggedin = false;
		}
		return option;
	}
	
	public static boolean addUserToAccount(Scanner in, AccountDao a_dao, UserDao u_dao, AccountUserDao au_dao, String toAccountName){
		String user_to_add = getUsername(in);
		if(userExist(u_dao, user_to_add)){
			au_dao.createAccountUser(toAccountName, user_to_add);
			System.out.println(user_to_add + " added to account " + toAccountName); System.out.println();
			return true;
		}
		return false;
	}
	
	public static boolean userExist(UserDao u_dao, String username){
		User user = u_dao.retrieveUserByUsername(username);
		if(user != null){
			return true;
		}
		return false;
	}
	public static boolean validateAccountBelongsToUser(ArrayList<Account> user_accounts, String accountname){
		
		for(int i=0; i<user_accounts.size(); i++){
			if(user_accounts.get(i).getName().equals(accountname)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean transferFromAccount(Scanner in, AccountDao a_dao, Account from, double amount) {
		System.out.print("Enter account name to transfer to: ");
		String transferTo = in.next();
		Account account = a_dao.retrieveAccountByName(transferTo);
			if(account != null){
				if(account.getStatus() == 1){
					if(from.withdraw(amount)){
						a_dao.transferByStoredProcedure(from.getName(), account.getName(), amount);
						//account.deposit(amount);
						//a_dao.updateAccount(from);
						//a_dao.updateAccount(account);
						System.out.println("Transfer successful");
						account = a_dao.retrieveAccountByName(account.getName());
						System.out.println("Tansfer to account new balance: " + account.getBalance()); System.out.println();
						return true;
					}
				} else {
					System.out.println("Account transfering to is inactive"); System.out.println();
				}
			} else {
				System.out.println("Invalid account"); System.out.println();
			}
		return false;
	}
	
	public static boolean withdrawFromAccount(Scanner in, AccountDao a_dao,
			ArrayList<Account> account, String withdrawFrom) {
		for(int i=0; i<account.size(); i++){
			if(account.get(i).getName().equals(withdrawFrom)){
				if(account.get(i).getStatus() == 1){
					System.out.println("Withdraw from your account ");
					System.out.print("Amount: ");
					double amount = in.nextDouble();
					if(account.get(i).withdraw(amount)){
						a_dao.updateAccount(account.get(i));
						System.out.println("New balance: " + account.get(i).getBalance()); System.out.println();
						return true;
					}
				} else {
					System.out.println("Account inactive"); System.out.println();
				}
			} 
		}
		return false;
	}

	public static boolean depositToAccount(Scanner in, AccountDao a_dao,
			ArrayList<Account> account, String depositTo) {
		for(int i=0; i<account.size(); i++){
			if(account.get(i).getName().equals(depositTo)){
				if(account.get(i).getStatus() == 1){
					System.out.println("Deposit to your account ");
					System.out.print("Amount: ");
					double amount = in.nextDouble();
					account.get(i).deposit(amount);
					a_dao.updateAccount(account.get(i));
					System.out.println("New balance: " + account.get(i).getBalance()); System.out.println();
					return true;
				} else {
					System.out.println("Account inactive"); System.out.println();
				}
			} 
		}
		return false;
	}
	public static String main_menu(String userType, Scanner in){
		String input = "";
		if(userType.equals("customer") || userType.equals("admin")){
			System.out.println("Add account (1)");
			System.out.println("Deposit (2)");
			System.out.println("Withdraw (3)");
			System.out.println("Transfer (4)");
			System.out.println("Add user to your account (5)");
		}

		if(userType.equals("customer") || !userType.equals("admin") || !userType.equals("employee")){
			System.out.println("View Account Info (6)");
		}
		if(userType.equals("admin")){
			System.out.println("Delete Accounts (7)");
		}
		if(userType.equals("admin")){
			System.out.println("Approve Accounts (8)");
		}
		System.out.println("Return (-1)"); System.out.println();
		System.out.print("Select: "); System.out.println();
		input = in.next();	
		return input;
	}	
	public static String user_selection(Scanner in){
		System.out.print("Who are you? (Customer = 1, Employee = 2, Admin = 3, Exit = -1): ");
		String input = in.next();
		return input;
	}
	public static String menu_login_register(Scanner in){
		System.out.print("Register(1) Login(2) Return (-1): ");
		String input = in.next();
		return input;
	}
	public static String getUsername(Scanner in){
		System.out.print("Enter Username: ");
		String input = in.next();
		return input;
	}
	public static String getPassword(Scanner in){
		System.out.print("Enter Password: ");
		String input = in.next();
		System.out.println();
		return input;
	}
	public static String getFirstName(Scanner in){
		System.out.print("First Name: ");
		String input = in.next();
		return input;
	}
	public static String getLastName(Scanner in){
		System.out.print("Last Name: ");
		String input = in.next();
		return input;
	}
	public static Account createAccount(User user, Scanner in){
		System.out.print("Account name: ");
		String accountName = in.next();
		Account account = new Account(user, 0, accountName);
		System.out.println("Account created"); System.out.println();
		return account;
	}
}