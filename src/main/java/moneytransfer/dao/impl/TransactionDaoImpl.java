package moneytransfer.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import moneytransfer.constants.Constants;
import moneytransfer.dao.TransactionDao;
import moneytransfer.database.DataSource;
import moneytransfer.model.Transaction;

public class TransactionDaoImpl implements TransactionDao{
	Connection conn = null; 
    Statement stmt = null;
    
	public void addTransaction(Transaction transaction) {
		try{
	    	 conn = DataSource.getInstance().getConnection();
	         stmt = conn.createStatement();
	         String sql = "INSERT INTO TRANSACTION ( sender, receiver, amount, status) VALUES ("+transaction.getSender()+","+transaction.getReceiver()+", "+transaction.getAmount()+", "+Constants.TransactionStatus.READY.getValue()+")";
	         stmt.execute(sql);
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
	
	public void updateTransaction(Transaction transaction){
		try{
			conn = DataSource.getInstance().getConnection();
			stmt = conn.createStatement();
			String sql = "UPDATE TRANSACTION SET sender="+transaction.getSender()+" ,receiver= "+transaction.getReceiver()+" ,amount= "+transaction.getAmount()+",status="+transaction.getStatus().getValue()+" where id="+transaction.getId();
			stmt.execute(sql);
			conn.commit();
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(stmt != null) stmt.close();
			}catch(Exception e){
				
			}
			DataSource.closeConnection(conn);
		}
	}

	public Transaction findTransaction(int id) {
		Transaction transaction = new Transaction(); 
	      try { 
	    	 conn = DataSource.getInstance().getConnection();
	         stmt = conn.createStatement(); 
	         String sql = "SELECT id,sender,receiver,amount,status FROM TRANSACTION where id ="+id; 
	         ResultSet rs = stmt.executeQuery(sql); 
	         if(rs.next()) { 
	             int sender = rs.getInt("sender");
	             int receiver = rs.getInt("receiver"); 
	             Float amount = rs.getFloat("amount"); 
	             int status = rs.getInt("status");
	             
	             transaction.setAmount(amount);
	             transaction.setSender(sender);
	             transaction.setReceiver(receiver);
	             transaction.setId(id);
	             transaction.setStatus(Constants.TransactionStatus.values()[status]);

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
	      return transaction;
	}

	public List<Transaction> findTransactions() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
	      try { 
	    	 conn = DataSource.getInstance().getConnection();
	         stmt = conn.createStatement(); 
	         String sql = "SELECT id,sender,receiver,amount,status FROM TRANSACTION"; 
	         ResultSet rs = stmt.executeQuery(sql); 
	         while(rs.next()) { 
	        	 Transaction transaction = new Transaction();
	             int sender = rs.getInt("sender");
	             int receiver = rs.getInt("receiver"); 
	             Float amount = rs.getFloat("amount"); 
	             int id = rs.getInt("id");
	             int status = rs.getInt("status");
	             
	             transaction.setAmount(amount);
	             transaction.setSender(sender);
	             transaction.setReceiver(receiver);
	             transaction.setId(id);
	             transaction.setStatus(Constants.TransactionStatus.values()[status]);
	             transactionList.add(transaction);
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
	      return transactionList;
	}
	
	public List<Transaction> findActiveTransactions() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
	      try { 
	    	 conn = DataSource.getInstance().getConnection();
	         stmt = conn.createStatement(); 
	         String sql = "SELECT id,sender,receiver,amount,status FROM TRANSACTION WHERE status="+Constants.TransactionStatus.READY.getValue(); 
	         ResultSet rs = stmt.executeQuery(sql); 
	         while(rs.next()) { 
	        	 Transaction transaction = new Transaction();
	             int sender = rs.getInt("sender");
	             int receiver = rs.getInt("receiver"); 
	             Float amount = rs.getFloat("amount"); 
	             int id = rs.getInt("id");
	             int status = rs.getInt("status");
	             transaction.setAmount(amount);
	             transaction.setSender(sender);
	             transaction.setReceiver(receiver);
	             transaction.setId(id);
	             transaction.setStatus(Constants.TransactionStatus.READY);
	             transactionList.add(transaction);
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
	      return transactionList;
	}

}
