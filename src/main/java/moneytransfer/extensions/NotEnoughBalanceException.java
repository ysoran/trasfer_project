package moneytransfer.extensions;

public class NotEnoughBalanceException extends RuntimeException {
	private final int accountId;
	private final int transferId;
	
	public NotEnoughBalanceException(int accountId, int transferId){
		this.accountId = accountId;
		this.transferId = transferId;
	}
	
	public int getAccountId(){
		return this.accountId;
	}
	
	public int getTransferId(){
		return this.transferId;
	}
	
	@Override
	public String getMessage() {
		return "Account with number "+this.accountId+" has not enough money to complete transfer with number "+this.transferId;
	}
}
