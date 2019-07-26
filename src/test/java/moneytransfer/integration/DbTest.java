package moneytransfer.integration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import moneytransfer.dao.impl.AccountDaoImpl;
import moneytransfer.dao.impl.CustomerDaoImpl;
import moneytransfer.database.DataSource;
import moneytransfer.database.DatabaseGenerator;
import moneytransfer.model.Account;
import moneytransfer.model.Customer;
import moneytransfer.server.ServerGenerator;
import moneytransfer.service.AccountService;

public class DbTest {
	private static Connection conn;
	private static Savepoint rollbackPoint;
	
	@BeforeClass
    public static void beforeAll()  {
        ServerGenerator.config();
        try{
        	DatabaseGenerator.create();
        	conn = DataSource.getInstance().getConnection();
        	rollbackPoint = conn.setSavepoint("MYSAVEPOINT");
        }catch (Exception e) {
        	e.printStackTrace();
		}
        
    }
	
 	@AfterClass
    public static void afterAll() {
        try {
			conn.rollback(rollbackPoint);
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
 	
 	@Test
 	public void accountService_crudWorks(){
 		Account accountForRead = null;
		AccountDaoImpl accountDao = new AccountDaoImpl();
		CustomerDaoImpl customerDao = new CustomerDaoImpl();
 		Customer customerForTest = new Customer(11,"Derya","Sari","Adress");
 		Account accountForWrite = new Account(11,customerForTest,"account1",Float.valueOf(100),Float.valueOf(100),1);
 		
 		customerDao.addCustomer(customerForTest);
 		accountDao.addAccount(accountForWrite);
 		accountForRead = accountDao.findAccount(11);
 		
 		Assert.assertNotNull(accountForRead);
 		Assert.assertEquals(accountForWrite.getName(),accountForRead.getName());
 		Assert.assertEquals(accountForWrite.getAccountBalance(),accountForRead.getAccountBalance());
 		Assert.assertEquals(accountForWrite.getCustomer().getId(),accountForRead.getCustomer().getId());
	
 	}
 	
}
