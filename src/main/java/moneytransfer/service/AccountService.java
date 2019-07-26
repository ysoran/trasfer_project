package moneytransfer.service;

import java.util.List;

import moneytransfer.dao.impl.AccountDaoImpl;
import moneytransfer.model.Account;

public class AccountService {
	AccountDaoImpl accountDaoImpl = null;
	
	public AccountService(){
		this.accountDaoImpl = new AccountDaoImpl();
	}
	public AccountService(AccountDaoImpl accountDaoImpl){
		this.accountDaoImpl = accountDaoImpl;
	}
	
	public Account findAccount(int id){
		return accountDaoImpl.findAccount(id);
	}
	public List<Account> findAccounts(){
		return accountDaoImpl.findAllAccounts();
	}
	
	public void addAccount(Account account){
		accountDaoImpl.addAccount(account);
	}
	
	public void updateAccount(Account account){
		accountDaoImpl.updateAccount(account);
	}
}
