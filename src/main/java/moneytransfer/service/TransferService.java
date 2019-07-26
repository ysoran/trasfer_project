package moneytransfer.service;

import moneytransfer.constants.Constants;
import moneytransfer.extensions.NotEnoughBalanceException;
import moneytransfer.model.Account;
import moneytransfer.model.Transaction;
import moneytransfer.model.Transfer;

public class TransferService {
	AccountService accountService = null;	
	TransactionService transactionService = null;	
	public TransferService(){
		this.accountService = new AccountService();
		this.transactionService = new TransactionService();
	}
	public TransferService(AccountService accountService, TransactionService transactionService){
		this.accountService = accountService;
		this.transactionService = transactionService;
		if(this.accountService == null){
			this.accountService = new AccountService();
		}
		if(this.transactionService == null){
			this.transactionService = new TransactionService();
		}
	}
	public String transferMoneyById(Transaction transaction){
		String transferResultLog;

		Account senderAccount = accountService.findAccount(transaction.getSender());
		Account receiverAccount = accountService.findAccount(transaction.getReceiver());
		
		Transfer transfer = new Transfer();
		transfer.setSender(senderAccount);
		transfer.setReceiver(receiverAccount);
		transfer.setAmount(transaction.getAmount());
		transaction.setStatus(Constants.TransactionStatus.IN_PROGRESS);
		transactionService.updateTransaction(transaction);
		transferResultLog = executeTransfer(transfer);
		transaction.setStatus(Constants.TransactionStatus.COMPLETED);
		transactionService.updateTransaction(transaction);
		return transferResultLog;
	}
	
	public String executeTransfer(Transfer transfer){
		Account senderAccount = transfer.getSender();
		Account receiverAccount = transfer.getReceiver();
		Account lockObject1 = senderAccount.getId() > receiverAccount.getId() ? receiverAccount : senderAccount;
		Account lockObject2 = senderAccount.getId() > receiverAccount.getId() ? senderAccount : receiverAccount;
		synchronized (lockObject1) {
			synchronized (lockObject2) {
				if(checkFromAccountBalanceIsEnoughForTransfer(transfer)){
					float newFromBalance = senderAccount.getAccountBalance() - transfer.getAmount();
					float newToBalance = receiverAccount.getAccountBalance() + transfer.getAmount();
					senderAccount.setAccountBalance(newFromBalance);
					receiverAccount.setAccountBalance(newToBalance);
					accountService.updateAccount(senderAccount);
					accountService.updateAccount(receiverAccount);
					System.out.println(transfer.toString());
					return transfer.toString();
				}else{
					throw new NotEnoughBalanceException(senderAccount.getId(), transfer.getId());
				}
			}
		}
	}	
	
	public boolean checkFromAccountBalanceIsEnoughForTransfer(Transfer transfer){
		return transfer.getSender().getAccountBalance() >= transfer.getAmount();
	}
	
	public String checkFromAccountBalanceIsEnoughForTransaction(Transaction transaction){
		boolean balanceIsEnough = accountService.findAccount(transaction.getSender()).getAccountBalance() >= transaction.getAmount();
		if(!balanceIsEnough){
			throw new NotEnoughBalanceException(transaction.getSender(), transaction.getId());
		}
		
		return  "Balance is enough";
	}

	
}
