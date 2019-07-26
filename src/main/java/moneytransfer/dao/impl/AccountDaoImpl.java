package moneytransfer.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import moneytransfer.dao.AccountDao;
import moneytransfer.database.DataSource;
import moneytransfer.model.Account;
import moneytransfer.model.Customer;

public class AccountDaoImpl implements AccountDao{
	Connection conn = null; 
	Statement stmt = null;

	public List<Account> findAllAccounts(){
			  
		      List<Account> accountList = new ArrayList<Account>();
		      try { 
		    	 conn = DataSource.getInstance().getConnection();
		         stmt = conn.createStatement(); 
		         String sql = "SELECT id,customer_id, name, bank_id, balance, debt_tolerance FROM ACCOUNT"; 
		         ResultSet rs = stmt.executeQuery(sql); 
		         while(rs.next()) { 
		             Account account = new Account();
		             int id = rs.getInt("id");
		             int customerId = rs.getInt("customer_id"); 
		             String name = rs.getString("name");
		             int bankId = rs.getInt("bank_id"); 
		             Float balance = rs.getFloat("balance"); 
		             Float debtTolerance = rs.getFloat("debt_tolerance");  
		             account.setAccountBalance(balance);
		             account.setName(name);
		             account.setId(id);
		             CustomerDaoImpl customerDao = new CustomerDaoImpl();
		             Customer customer = customerDao.findCustomer(customerId);
		             account.setCustomer(customer);
		             account.setDebtTolerance(debtTolerance);
		             account.setBankId(bankId);
		             accountList.add(account);
		          } 
		         rs.close(); 
		      } catch(SQLException se) { 
		         se.printStackTrace(); 
		      } catch(Exception e) { 
		         e.printStackTrace(); 
		      } finally {  
		         try { 
		            if(stmt!=null) stmt.close();  
		         } catch(SQLException se2) { 
		         } 
		         DataSource.closeConnection(conn);
		      }		      
		      return accountList;
	}
	
	public Account findAccount(int id){
	      Account account = new Account(); 
	      try { 
	    	 conn = DataSource.getInstance().getConnection();
	         stmt = conn.createStatement(); 
	         String sql = "SELECT id,customer_id, name, bank_id, balance, debt_tolerance FROM ACCOUNT where id ="+id+" FOR UPDATE"; 
	         ResultSet rs = stmt.executeQuery(sql); 
	         if(rs.next()) { 
	             int customerId = rs.getInt("customer_id"); 
	             String name = rs.getString("name");
	             int bankId = rs.getInt("bank_id"); 
	             Float balance = rs.getFloat("balance"); 
	             Float debtTolerance = rs.getFloat("debt_tolerance");  
	             account.setAccountBalance(balance);
	             account.setName(name);
	             account.setId(id);
	             CustomerDaoImpl customerDao = new CustomerDaoImpl();
	             Customer customer = customerDao.findCustomer(customerId);
	             account.setCustomer(customer);
	             account.setDebtTolerance(debtTolerance);
	             account.setBankId(bankId);

	          } 
	         rs.close(); 
	         conn.commit();
	      } catch(SQLException se) { 
	         se.printStackTrace(); 
	      } catch(Exception e) { 
	         e.printStackTrace(); 
	      } finally {  
	         try { 
	            if(stmt!=null) stmt.close();  
	         } catch(SQLException se2) { 
	         } 
	         DataSource.closeConnection(conn);
	      }		      
	      return account;
	}
	
	public void addAccount(Account account){
		try{
	    	 conn = DataSource.getInstance().getConnection();
	         stmt = conn.createStatement();  
	         String sql = "INSERT INTO ACCOUNT VALUES ("+account.getId()+","+account.getCustomer().getId()+",'"+account.getName()+"', "+account.getBankId()+", "+account.getAccountBalance()+", "+account.getDebtTolerance()+")";
	         stmt.executeUpdate(sql);
	         stmt.close(); 
	         conn.commit();
	      } catch(SQLException se) { 
	         se.printStackTrace(); 
	      } catch(Exception e) { 
	         e.printStackTrace(); 
	      } finally { 
	         try {
	            if(stmt!=null) stmt.close();  
	         } catch(SQLException se2) { 
	         } 
	         DataSource.closeConnection(conn);
	      }
	}
	
	public void updateAccount(Account account){
		Connection conn = null; 
	    Statement stmt = null;
		try{
	    	 conn = DataSource.getInstance().getConnection();
	         stmt = conn.createStatement();  
	         String sql = "UPDATE ACCOUNT SET name ='"+account.getName()+"', balance="+account.getAccountBalance()+", debt_tolerance="+account.getDebtTolerance()+" where id="+account.getId(); 
	         stmt.executeUpdate(sql); 
	         
	         stmt.close(); 
	         conn.commit();
		      } catch(SQLException se) { 
		         se.printStackTrace(); 
		      } catch(Exception e) { 
		         e.printStackTrace(); 
		      } finally { 
		         try {
		            if(stmt!=null) stmt.close();  
		         } catch(SQLException se2) { 
		         }
		         DataSource.closeConnection(conn);
	     }
	}
}
