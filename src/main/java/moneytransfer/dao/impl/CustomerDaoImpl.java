package moneytransfer.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import moneytransfer.dao.CustomerDao;
import moneytransfer.database.DataSource;
import moneytransfer.model.Customer;

public class CustomerDaoImpl implements CustomerDao{
	Connection conn = null; 
    Statement stmt = null;
    
	public Customer findCustomer(int id){
	      Customer customer = new Customer();
	      try { 
	    	 conn = DataSource.getInstance().getConnection();
	         stmt = conn.createStatement(); 
	         String sql = "SELECT id, first_name, last_name, address FROM CUSTOMER where id ="+id; 
	         ResultSet rs = stmt.executeQuery(sql); 
	         if(rs.next()) { 
	             String firstName = rs.getString("first_name"); 
	             String lastName = rs.getString("last_name"); 
	             String address = rs.getString("address");  
	                
                 customer.setAddress(address);
                 customer.setFirstName(firstName);
                 customer.setId(id);
                 customer.setLastName(lastName);
                 
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
	      return customer;
	}
	
	public Customer findCustomer(String name){
	      Customer customer = new Customer();
	      try { 
	    	 conn = DataSource.getInstance().getConnection();
	         stmt = conn.createStatement(); 
	         String sql = "SELECT id, first_name, last_name, address FROM CUSTOMER where first_name ='"+name+"'"; 
	         ResultSet rs = stmt.executeQuery(sql); 
	         if(rs.next()) { 
	             String firstName = rs.getString("first_name"); 
	             String lastName = rs.getString("last_name"); 
	             String address = rs.getString("address"); 
	             int id = rs.getInt("id");
	                
               customer.setAddress(address);
               customer.setFirstName(firstName);
               customer.setId(id);
               customer.setLastName(lastName);
               
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
	      return customer;
	}
	
	public List<Customer> findAllCustomers(){
	      List<Customer> customerList = new ArrayList<Customer>();
	      try { 
	    	 conn = DataSource.getInstance().getConnection();
	         stmt = conn.createStatement(); 
	         String sql =  "SELECT id, first_name, last_name, address FROM CUSTOMER"; 
	         ResultSet rs = stmt.executeQuery(sql); 
	         while(rs.next()) { 
	             Customer customer = new Customer();
	             int id = rs.getInt("id"); 
	             String firstName = rs.getString("first_name"); 
	             String lastName = rs.getString("last_name"); 
	             String address = rs.getString("address");  
	             customer.setAddress(address);
                 customer.setFirstName(firstName);
                 customer.setId(id);
                 customer.setLastName(lastName);
                 customerList.add(customer);
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
	      return customerList;
	}
	
	public void addCustomer(Customer customer){
		try{
	    	 conn = DataSource.getInstance().getConnection();
	         stmt = conn.createStatement();  
	         String sql = "INSERT INTO CUSTOMER " + "VALUES ("+customer.getId()+", '"+customer.getFirstName()+"', '"+customer.getLastName()+"', '"+customer.getAddress()+"')";
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
	
	public void updateCustomer(Customer customer){
		try{
	    	 conn = DataSource.getInstance().getConnection();
	         stmt = conn.createStatement();  
	         String sql = "UPDATE CUSTOMER SET fist_name='"+customer.getFirstName()+"', last_name='"+customer.getLastName()+"', address='"+customer.getAddress()+" where id="+customer.getId(); 
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
