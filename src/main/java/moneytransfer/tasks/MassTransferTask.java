package moneytransfer.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import moneytransfer.model.Transaction;
import moneytransfer.model.TransactionWay;
import moneytransfer.service.TransactionService;

public class MassTransferTask implements Runnable{
	TransactionService transactionService = new TransactionService();
	ConcurrentHashMap<TransactionWay, BigDecimal> transactionReport = null;
	ConcurrentHashMap<TransactionWay,TransactionWay> lockMap = new ConcurrentHashMap<>();
	List<Transaction> transactions = null;
	public MassTransferTask(ConcurrentMap<TransactionWay, BigDecimal> transactionReport){
		this.transactionReport = (ConcurrentHashMap<TransactionWay, BigDecimal>) transactionReport;
		
	}
	public MassTransferTask(ConcurrentMap<TransactionWay, BigDecimal> transactionReport, List<Transaction> transactions){
		this.transactionReport = (ConcurrentHashMap<TransactionWay, BigDecimal>) transactionReport;
		this.setTransactions(transactions);
	}
	
	public void run() {
		
		System.out.println("taskstarted");
		try{
			if(isEmptyList(this.transactions)){
				transactions = transactionService.findActiveTransactions();
			}
			if(!transactions.isEmpty()){
				
				int ii = transactions.size()>100 ? 100 : transactions.size();
				ExecutorService executor = Executors.newFixedThreadPool(ii);				
				List<Callable<Object>> todo = new ArrayList<>(ii);

				
				for (Transaction currentTransaction: transactions) {
					todo.add(Executors.callable(new TransferReporter(currentTransaction,transactionReport,lockMap)));
				}
				
				executor.invokeAll(todo);
				
				executor = Executors.newFixedThreadPool(this.transactionReport.size());
				for (Map.Entry<TransactionWay, BigDecimal> entry : transactionReport.entrySet()) { 
					TransactionWay key = entry.getKey();
					Transaction transactionToRun = new Transaction(key.getFrom(), key.getTo(), entry.getValue().floatValue());
					TransferTask task = new TransferTask(transactionToRun);
					executor.submit(task);
				}
				
				setDefaults();
				executor.shutdown();
				
				
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void setDefaults(){
		this.transactionReport = new ConcurrentHashMap<>();
		this.setTransactions(null);
	}
	
	private void setTransactions(List<Transaction> transaction){
		this.transactions = transaction;
	}
	
	public <E> boolean isEmptyList(List<E> collection){
		return collection == null || collection.isEmpty();
	}
}
