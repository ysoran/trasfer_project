package moneytransfer.dao;

import java.util.List;

import moneytransfer.model.Customer;

public interface CustomerDao {
	void updateCustomer(Customer customer);
	void addCustomer(Customer customer);
	Customer findCustomer(int id);
	Customer findCustomer(String name);
	List<Customer> findAllCustomers();
}
