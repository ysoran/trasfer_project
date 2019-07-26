package moneytransfer.tasks;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import moneytransfer.constants.Constants;
import moneytransfer.model.Transaction;
import moneytransfer.model.TransactionWay;
import moneytransfer.service.TransactionService;
import moneytransfer.service.TransferService;

public class TransferReporter implements Runnable{
	TransactionService transactionService = new TransactionService();
	Transaction transaction = null;
	ConcurrentHashMap<TransactionWay,TransactionWay> lockMap = null;
	
	ConcurrentHashMap<TransactionWay, BigDecimal> transactionMap = null;
	TransferReporter(Transaction transaction, ConcurrentHashMap<TransactionWay, BigDecimal> map, ConcurrentHashMap<TransactionWay,TransactionWay>  lockMap){
		this.transaction = transaction;
		this.transactionMap = map;
		this.lockMap = lockMap;
	}
	public synchronized void run() {
		try{
			transaction.setStatus(Constants.TransactionStatus.IN_PROGRESS);
			transactionService.updateTransaction(transaction);
			TransactionWay currentWay = new TransactionWay();
			currentWay.setFrom(transaction.getSender());
			currentWay.setTo(transaction.getReceiver());
			if(!lockMap.contains(currentWay)){
				lockMap.put(currentWay, currentWay);
			}
			synchronized (lockMap.get(currentWay)) {
				if(transactionMap.containsKey(currentWay)){
					BigDecimal result=transactionMap.get(currentWay).add(BigDecimal.valueOf(transaction.getAmount()));
					transactionMap.put(currentWay, result);
				}else{
					transactionMap.put(currentWay, BigDecimal.valueOf(transaction.getAmount()));
				}	
			}
			transaction.setStatus(Constants.TransactionStatus.COMPLETED);
			transactionService.updateTransaction(transaction);
		}catch(Exception e){
			
		}
	}

}
