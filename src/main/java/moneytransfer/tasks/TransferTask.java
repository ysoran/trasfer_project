package moneytransfer.tasks;

import java.util.Date;

import moneytransfer.model.Transaction;
import moneytransfer.service.AccountService;
import moneytransfer.service.TransactionService;
import moneytransfer.service.TransferService;

public class TransferTask implements Runnable{
	Transaction transaction = null;
	AccountService accountService = new AccountService();	
	TransactionService transactionService = new TransactionService();
	TransferService transferService = new TransferService(accountService,transactionService);
	TransferTask(Transaction transaction){
		this.transaction = transaction;
	}
	
	@Override
	public void run() {
		transferService.transferMoneyById(transaction);
		System.out.println("Doing transaction id of : " + transaction.getId() + " - Time - " + new Date());
	}

}
