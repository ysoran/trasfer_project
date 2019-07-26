package moneytransfer.service;

import java.util.List;

import moneytransfer.dao.impl.TransactionDaoImpl;
import moneytransfer.extensions.NotEnoughBalanceException;
import moneytransfer.model.Transaction;

public class TransactionService {
	AccountService accountService;
	TransactionDaoImpl transactionDaoImpl = null;
	TransferService transferService = null;
	
	public TransactionService(){
		accountService = new AccountService();
		transactionDaoImpl = new TransactionDaoImpl();
		transferService = new TransferService(accountService, this);
	}
	
	public TransactionService(TransactionDaoImpl transactionDaoImp, AccountService accountService){
		this.transactionDaoImpl = transactionDaoImpl;
		this.accountService = accountService;
		transferService = new TransferService(accountService, this);
	}
	
	public void addTransaction(Transaction transaction){
		try{
			transferService.checkFromAccountBalanceIsEnoughForTransaction(transaction);
			transactionDaoImpl.addTransaction(transaction);
		}catch(NotEnoughBalanceException e){
			e.printStackTrace();
		}
		
	}
	
	public Transaction findTransaction(int id){
		return transactionDaoImpl.findTransaction(id);
	}
	
	public List<Transaction> findTransactions(){
		return transactionDaoImpl.findTransactions();
	}
	
	public List<Transaction> findActiveTransactions(){
		return transactionDaoImpl.findActiveTransactions();
	}
	
	public void updateTransaction(Transaction transaction){
		transactionDaoImpl.updateTransaction(transaction);
	}
}
