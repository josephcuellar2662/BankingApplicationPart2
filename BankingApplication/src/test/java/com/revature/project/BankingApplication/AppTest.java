package com.revature.project.BankingApplication;

import com.revature.project.BankingApplication.dao.AccountDao;
import com.revature.project.BankingApplication.dao.AccountDaoImp;
import com.revature.project.BankingApplication.dao.UserDao;
import com.revature.project.BankingApplication.dao.UserDaoImp;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
//    public void testApp()
//    {
//        assertTrue( true );
//    }
//    
//    public void testAddUserToAccount(){
//    	User user = new User("customer", "Joseph", "Cuellar", "jecuellar", "1234");
//    	User user2 = new User("customer", "Lily", "Cuellar", "lcuellar", "1234");
//    	Account acc = new Account(user, 1);
//    	acc.addUser(user2);
//    	assertEquals("jecuellarlcuellar", acc.getUsernames());
//    } 
//    
//    public void testDepositAccount(){
//    	User user = new User("customer", "Joseph", "Cuellar", "jecuellar", "1234");
//    	Account acc = new Account(user, 1);
//    	acc.deposit(500.0);
//    	assertEquals(500.0, acc.getBalance());
//    }
//    
//    public void testWithdrawAccount(){
//    	// User(String type, String firstName, String lastName, String username, String password)
//    	User user = new User("customer", "Joseph", "Cuellar", "jecuellar", "1234");
//    	// public Account(User user, String type, double balance)
//    	Account acc = new Account(user, 1);
//    	acc.deposit(5000.0);
//    	acc.withdraw(500);
//    	assertEquals(4500.0, acc.getBalance());
//    }
//    
//    public void testGetBalance(){
//    	// User(String type, String firstName, String lastName, String username, String password)
//    	User user = new User("customer", "Joseph", "Cuellar", "jecuellar", "1234");
//    	// public Account(User user, String type, double balance)
//    	Account acc = new Account(user, 1);
//    	acc.deposit(5000.0);
//    	assertEquals(5000.0, acc.getBalance());
//    }
//    
//    //Test User Class
//    public void testGetUsername(){
//    	User user = new User("customer", "Joseph", "Cuellar", "jecuellar", "1234");
//    	assertEquals("jecuellar", user.getUsername());
//    }
//    
//    public void testGetPassword(){
//    	User user = new User("customer", "Joseph", "Cuellar", "jecuellar", "1234");
//    	assertEquals("1234", user.getPassword());
//    }
//    
//    public void testGetFirstName(){
//    	User user = new User("customer", "Joseph", "Cuellar", "jecuellar", "1234");
//    	assertEquals("Joseph", user.getFirstName());
//    }
//    
//    public void testGetLastName(){
//    	User user = new User("customer", "Joseph", "Cuellar", "jecuellar", "1234");
//    	assertEquals("Cuellar", user.getLastName());
//    }
//    
//    //Test User Login Class
//    public void testLoginTrue(){
//    	User user = new User("customer", "Joseph", "Cuellar", "jecuellar", "1234");
//    	assertTrue(user.login("1234"));
//    }
//    
//    public void testLoginFalse(){
//    	User user = new User("customer", "Joseph", "Cuellar", "jecuellar", "1234");
//    	assertFalse(user.login("124"));
//    }
//    
//    //Test inserting into database
//    public void testAddUser(){
//    	User user = new User("customer", "Alicia", "Garza", "agarza", "1234");
//    	UserDao u = new UserDaoImp();
//    	u.createUser(user);
//    	user = u.retrieveUserByUsername("lcuellar");
//    	assertEquals("lcuellar", user.getUsername());
//    }
//    public void testRetrieveUserByUsername(){
//    	UserDao u = new UserDaoImp();
//    	User tuser = u.retrieveUserByUsername("scuellar");
//    	assertEquals("scuellar", tuser.getUsername());
//    }
//    public void testDeleteUser(){
//    	UserDao u = new UserDaoImp();
//    	assertTrue(u.deleteUser(9));
//    }
//    public void testAddAccount(){
//    	AccountDao a = new AccountDaoImp();
//    	User user = new User("customer", "Ahmad", "Gonzalez", "agonzalez", "1234");
//    	Account acc = new Account(user, 1);
//    	assertTrue(a.createAccount(acc));
//    }
      public void testUpdateAccount(){
    	  AccountDao a = new AccountDaoImp();
      	  User user = new User("customer", "Billy", "Kid", "bkid", "1234");
      	  Account acc = new Account(user, 1);
      	  a.createAccount(acc);
      	  acc.deposit(3500);
    	  a.updateAccount(acc, 66);
    	  Account tacc = a.retrieveAccountById(66);
    	  assertEquals(3500.0, tacc.getBalance());
      }
      public void testRetrieveAccount(){
    	  AccountDao a = new AccountDaoImp();
    	  assertTrue(a.retrieveAccountById(41) != null);
      }
      public void testDeleteAccount(){
    	  AccountDao a = new AccountDaoImp();
    	  assertTrue(a.deleteAccount(21));
      }

}
