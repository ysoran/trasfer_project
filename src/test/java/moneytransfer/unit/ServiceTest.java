package moneytransfer.unit;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import moneytransfer.dao.impl.TransactionDaoImpl;
import moneytransfer.extensions.NotEnoughBalanceException;
import moneytransfer.model.Account;
import moneytransfer.model.Customer;
import moneytransfer.model.Transaction;
import moneytransfer.model.Transfer;
import moneytransfer.service.AccountService;
import moneytransfer.service.TransactionService;
import moneytransfer.service.TransferService;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {
	@Mock
	private TransactionDaoImpl transactionDaoImpl;
	
	@Mock
	private AccountService accountService;
	
	@Mock
	private TransactionService transactionService;
	
	@InjectMocks
	private TransferService transferService;
	
	@Spy
	private TransferService transferServiceSpy;
	
	@BeforeEach
	public void setUp(){
		
	}
	
	@Test
    public void testTransactionService_getTransactionList(){
        List<Transaction> transactionList = Arrays.asList(
                new Transaction(1,2,1000),
                new Transaction(1,2,1000)
        );

        Mockito.when(transactionService.findTransactions()).thenReturn(transactionList);
        Collection<Transaction> transactions = transactionService.findTransactions();

        Assert.assertNotNull(transactions);
        Assert.assertArrayEquals(transactionList.toArray(), transactions.toArray());
        
    }
	
	@Test
	public void testTransferService_executeTransferIsCalledAndCalledOnce(){
		Transaction transaction = new Transaction(1,2,10);
		Transfer transfer = new Transfer();
		Customer senderCustomer = new Customer(1, "yasar","soran","adres1");
		Customer receiverCustomer = new Customer(2, "yasar","soran","adres2");
		Account sender = new Account(1, senderCustomer, "test", Float.valueOf(1000), Float.valueOf(1000),1);
		Account receiver = new Account(1, receiverCustomer, "test", Float.valueOf(1000), Float.valueOf(1000),1);
		transfer.setSender(sender);
		transfer.setReceiver(receiver);
		transfer.setAmount(100);
		transfer.setId(1);
		Mockito.doNothing().when(accountService).updateAccount(Mockito.any());
		Mockito.when(transferService.executeTransfer(transfer)).thenReturn("");
		transferService.transferMoneyById(transaction);
        Mockito.verify(transferService,Mockito.times(1)).executeTransfer(Mockito.any(Transfer.class));
	}
	
	@Test(expected = NotEnoughBalanceException.class)
 	public void testConcurrentMoneyTransferService_notEnoughBalanceException(){
 		
 		Transaction transaction = new Transaction(1,2,1000000);
 		
 		transferServiceSpy.transferMoneyById(transaction);
 		
 	}
	
	
	@Test
	public void accountService_getAccount(){
		List<Account> accountList = Arrays.asList(
				new Account(10,new Customer(1,"hasan","kemal","adres1"), "account1",Float.valueOf(1000),Float.valueOf(1000),1),
				new Account(20,new Customer(2,"kaan","kemal","adres2"), "account2",Float.valueOf(1000),Float.valueOf(1000),1),
				new Account(30,new Customer(3,"talat","kemal","adres3"), "account3",Float.valueOf(1000),Float.valueOf(1000),1)
				);
		
		Mockito.when(accountService.findAccounts()).thenReturn(accountList);
		List<Account> resultList = accountService.findAccounts();
		
		Assert.assertNotNull(resultList);
		Assert.assertEquals(accountList.get(0).getAccountBalance(), resultList.get(0).getAccountBalance());
	}
	
}
