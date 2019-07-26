package moneytransfer.integration;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import moneytransfer.extensions.NotEnoughBalanceException;
import moneytransfer.model.Account;
import moneytransfer.model.Customer;
import moneytransfer.model.Transaction;
import moneytransfer.model.TransactionWay;
import moneytransfer.service.AccountService;
import moneytransfer.service.TransactionService;
import moneytransfer.service.TransferService;
import moneytransfer.tasks.MassTransferTask;

public class ServiceTest {
	AccountService accountService;
	Customer customerForTest;
	Customer customerForTest1;
	Account account, account2;
	
	@Before
	public void init(){
		accountService = new AccountService();
 		customerForTest = new Customer(1,"A","b","Adress");
 		customerForTest1 = new Customer(2,"Derya","Sari","Adress");
 		accountService.updateAccount(new Account(1,customerForTest,"account1",Float.valueOf(100),Float.valueOf(100),1));
 		accountService.updateAccount(new Account(2,customerForTest1,"account1",Float.valueOf(100),Float.valueOf(100),1));
 		account = accountService.findAccount(1);
 		account2 = accountService.findAccount(2);
	}
	
	@Test
 	public void testConcurrentMoneyTransferService_createsReportCorrectly(){
 		ConcurrentHashMap<TransactionWay, BigDecimal> transactionReport = new ConcurrentHashMap<>();
 		List<Transaction> transactionList = new ArrayList<>();
 		Transaction transaction = new Transaction(1,2,100);
 		Transaction transaction2 = new Transaction(1,2,100);
 		Transaction transaction3 = new Transaction(1,2,100);
 		Transaction transaction4 = new Transaction(1,2,30);
 		Transaction transaction5 = new Transaction(1,2,20);
 		Transaction transaction6 = new Transaction(1,2,100);
 		Transaction transaction7 = new Transaction(1,2,100);
 		Transaction transaction8 = new Transaction(1,2,100);
 		Transaction transaction9 = new Transaction(1,2,30);
 		Transaction transaction10 = new Transaction(1,2,20);
 		transactionList.add(transaction);
 		transactionList.add(transaction2);
 		transactionList.add(transaction3);
 		transactionList.add(transaction4);
 		transactionList.add(transaction5);
 		transactionList.add(transaction6);
 		transactionList.add(transaction7);
 		transactionList.add(transaction8);
 		transactionList.add(transaction9);
 		transactionList.add(transaction10);
 		
 		MassTransferTask massTransfer = new MassTransferTask(transactionReport, transactionList);
 		massTransfer.run();
 		
 		assertEquals(transactionReport.get(new TransactionWay(1,2)),BigDecimal.valueOf(700.0));
 	}
 	
 	@Test
 	public void testConcurrentMoneyTransferService_notGetsInDeadLock(){
 		ConcurrentHashMap<TransactionWay, BigDecimal> transactionReport = new ConcurrentHashMap<>();
 		List<Transaction> transactionList = new ArrayList<>();
 		
 		
 		assertEquals(account.getAccountBalance(), account2.getAccountBalance());
 		
 		for(int i=0;i<100;i++){
	 		transactionList.add(new Transaction(1,2,100));
	 		transactionList.add(new Transaction(2,1,100));
 		}
 		
 		MassTransferTask massTransfer = new MassTransferTask(transactionReport, transactionList);
 		massTransfer.run();
 		
 		account = accountService.findAccount(1);
 		account2 = accountService.findAccount(2);
 		assertEquals(account.getAccountBalance(), account2.getAccountBalance());
 	}
 	
 	@Test
 	public void testTransactions_runMany(){
 		ConcurrentHashMap<TransactionWay, BigDecimal> transactionReport = new ConcurrentHashMap<>();
 		List<Transaction> transactionList = new ArrayList<>();
 		
 		for(int i=0;i<1000;i++){
	 		transactionList.add(new Transaction(1,2,1));
 		}
 		
 		MassTransferTask massTransfer = new MassTransferTask(transactionReport, transactionList);
 		massTransfer.run();
 	}
 	
 	@Test(expected = NotEnoughBalanceException.class)
 	public void testConcurrentMoneyTransferService_notEnoughBalanceException(){
 		AccountService accountService = new AccountService();
 		TransactionService transactionService = new TransactionService();
 		TransferService transferService = new TransferService(accountService,transactionService);
 		Transaction transaction = new Transaction(1,2,1000000);
 		
 		transferService.transferMoneyById(transaction);
 	}
}
