package moneytransfer.model;

import moneytransfer.constants.Constants;

public class Transaction {
	int sender;
	int receiver;
	int id;
	float amount;
	Constants.TransactionStatus status;
	
	
	public Transaction(int sender, int receiver, float amount){
		this.setAmount(amount);
		this.setSender(sender);
		this.setReceiver(receiver);
	}
	
	public Transaction(){
		
	}
	
	public int getSender() {
		return sender;
	}
	public void setSender(int sender) {
		this.sender = sender;
	}
	public int getReceiver() {
		return receiver;
	}
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Constants.TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(Constants.TransactionStatus status) {
		this.status = status;
	}
	
	
}
