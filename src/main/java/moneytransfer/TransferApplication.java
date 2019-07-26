package moneytransfer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import moneytransfer.tasks.ServerStarterTask;
import moneytransfer.tasks.TransactionReaderAndExecuterTask;

public class TransferApplication {
	private static ExecutorService executor = null;
	
    public static void main(String[] args){
    	executor = Executors.newFixedThreadPool(2);
    	ServerStarterTask serverStarter = new ServerStarterTask();
    	TransactionReaderAndExecuterTask transactionReader = new TransactionReaderAndExecuterTask();
    	executor.submit(serverStarter);
    	executor.submit(transactionReader);		
    }
}