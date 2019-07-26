package moneytransfer.service;

import java.util.List;

import moneytransfer.dao.impl.CustomerDaoImpl;
import moneytransfer.model.Customer;


public class CustomerService {
	CustomerDaoImpl customerDaoImpl = null;

	public CustomerService(){
		this.customerDaoImpl = new CustomerDaoImpl();
	}
	public CustomerService(CustomerDaoImpl customerDaoImpl){
		this.customerDaoImpl = customerDaoImpl;
	}
	
	public Customer findCustomer(int id){
		return customerDaoImpl.findCustomer(id);
	}
	public Customer findCustomer(String name){
		return customerDaoImpl.findCustomer(name);
	}
	public List<Customer> findCustomers(){
		return customerDaoImpl.findAllCustomers();
	}
	
	public void addAccount(Customer customer){
		customerDaoImpl.addCustomer(customer);
	}
	
	public void updateAccount(Customer customer){
		customerDaoImpl.updateCustomer(customer);
	}
}
