package moneytransfer.tasks;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import moneytransfer.model.TransactionWay;

public class TransactionReaderAndExecuterTask implements Runnable{

	public void run() {
		ConcurrentHashMap<TransactionWay, BigDecimal> transactionReport = new ConcurrentHashMap<>();
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		MassTransferTask task = new MassTransferTask(transactionReport);
		ScheduledFuture<?> result = executor.scheduleAtFixedRate(task, 5, 10, TimeUnit.SECONDS);
		

		
	}

}
