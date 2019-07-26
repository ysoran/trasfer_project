package moneytransfer.model;

import java.util.Date;
import java.util.Objects;

public class Account {
	private int id;
	private Customer customer;
	private String name;
	private Float accountBalance;
	private Float debtTolerance;
	private Date validDate;
	private int bankId;
	
	public Account(int id,Customer customer, String name, Float accountBalance, Float debtTolerance,int bankId) {
		this.setAccountBalance(accountBalance);
		this.setBankId(bankId);
		this.setCustomer(customer);
		this.setDebtTolerance(debtTolerance);
		this.setId(id);
		this.setName(name);
	}
	
	public Account(){
		
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Float getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(Float accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	public Float getDebtTolerance() {
		return debtTolerance;
	}
	public void setDebtTolerance(Float debtTolerance) {
		this.debtTolerance = debtTolerance;
	}
	public Date getValidDate() {
		return validDate;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Account)){
			return false;
		}
		Account account = (Account) obj;
		return id == account.id && name == account.name;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
}
