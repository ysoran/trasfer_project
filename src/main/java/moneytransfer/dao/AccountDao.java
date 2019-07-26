package moneytransfer.dao;

import java.util.List;

import moneytransfer.model.Account;

public interface AccountDao {
	void updateAccount(Account account);
	void addAccount(Account account);
	Account findAccount(int id);
	List<Account> findAllAccounts();
}
