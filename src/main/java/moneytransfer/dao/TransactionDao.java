package moneytransfer.dao;

import java.util.List;

import moneytransfer.model.Transaction;

public interface TransactionDao {
	void addTransaction(Transaction transfer);
	Transaction findTransaction(int id);
	List<Transaction> findTransactions();
}
