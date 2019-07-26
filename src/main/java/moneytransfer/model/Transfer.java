package moneytransfer.model;

import moneytransfer.constants.Constants;

public class Transfer {
	int id;
	Account sender;
	Account receiver;
	Constants.TransferType type;
	float amount;
	
	public Account getSender() {
		return sender;
	}
	public void setSender(Account sender) {
		this.sender = sender;
	}
	public Account getReceiver() {
		return receiver;
	}
	public void setReceiver(Account receiver) {
		this.receiver = receiver;
	}
	public Constants.TransferType getType() {
		return type;
	}
	public void setType(Constants.TransferType type) {
		this.type = type;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		StringBuffer transferLog = new StringBuffer();
		transferLog.append("Transfer log:");
		transferLog.append(System.getProperty("line.separator"));
		transferLog.append("---------------------------------------------");
		transferLog.append(System.getProperty("line.separator")); 
		
		transferLog.append(System.getProperty("line.separator"));
		transferLog.append("Sender account: ");
		transferLog.append(System.getProperty("line.separator"));
		transferLog.append("Account owner:	"); 
		transferLog.append(this.getSender().getCustomer().getFirstName());
		transferLog.append(" ");
		transferLog.append(this.getSender().getCustomer().getLastName());
		transferLog.append(System.getProperty("line.separator"));
		transferLog.append("Account balance:	");
		transferLog.append(this.getSender().getAccountBalance());
		transferLog.append(System.getProperty("line.separator"));
		
		transferLog.append(System.getProperty("line.separator"));
		transferLog.append("---------------------------------------------");
		transferLog.append(System.getProperty("line.separator"));
		
		transferLog.append(System.getProperty("line.separator"));
		transferLog.append("Receiver account: ");
		transferLog.append(System.getProperty("line.separator"));
		transferLog.append("Account owner:	"); 
		transferLog.append(this.getReceiver().getCustomer().getFirstName());
		transferLog.append(" ");
		transferLog.append(this.getReceiver().getCustomer().getLastName());
		transferLog.append(System.getProperty("line.separator"));
		transferLog.append("Account balance:	");
		transferLog.append(this.getReceiver().getAccountBalance());
		transferLog.append(System.getProperty("line.separator"));
		return transferLog.toString();
	}
	
}
