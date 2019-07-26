package moneytransfer.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.glassfish.jersey.server.ManagedAsync;

import moneytransfer.model.Transaction;
import moneytransfer.service.AccountService;
import moneytransfer.service.TransactionService;
import moneytransfer.service.TransferService;

@Path("transfer")
public class Routes {
	AccountService accountService = new AccountService();	
	TransactionService transactionService = new TransactionService();
	TransferService transferService = new TransferService(accountService,transactionService);
	
	@GET
	@Path("byid/{sender}/{receiver}/{amount}")
	@Produces("text/plain")
	@ManagedAsync
	public String makeTransaction(@PathParam("sender") int sender,@PathParam("receiver") int receiver,@PathParam("amount") float amount) {
		Transaction transaction = new Transaction(sender, receiver, amount);
		transactionService.addTransaction(transaction);
		return "Your transfer is successfull";
	}
	
}